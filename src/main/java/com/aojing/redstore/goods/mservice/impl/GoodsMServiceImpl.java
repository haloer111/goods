package com.aojing.redstore.goods.mservice.impl;

import com.aojing.redstore.goods.convert.RestTemplate2ResultConvert;
import com.aojing.redstore.goods.enums.ExceptionEnum;
import com.aojing.redstore.goods.enums.FileTypeEnum;
import com.aojing.redstore.goods.exception.RedStoreException;
import com.aojing.redstore.goods.output.MediaOutput;
import com.aojing.redstore.goods.service.GoodsInfoService;

import com.aojing.redstore.goods.common.Result;
import com.aojing.redstore.goods.dto.GoodsDto;
import com.aojing.redstore.goods.mservice.GoodsMService;
import com.aojing.redstore.goods.service.GoodsTypeService;
import com.aojing.redstore.goods.util.KeyUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author gexiao
 * @date 2018/12/09 下午 04:56
 */
@Service
@Slf4j
public class GoodsMServiceImpl implements GoodsMService {

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    GoodsInfoService goodsInfoService;
    @Autowired
    GoodsTypeService goodsTypeService;

    @Override
    public Result addOrUpdateGoods(GoodsDto goodsDto, HttpServletRequest request) {

        if (StringUtils.isNotBlank(goodsDto.getId())) {
            //更新goods表
            Result updateResult = goodsInfoService.updateGoods(goodsDto);
            if (!updateResult.isSuccess()) {
                throw new RedStoreException(ExceptionEnum.UPDATE_GOODS_FAIL);
            }
            //更新商品和类型关系表
            //更新media表
            return Result.createBySuccessMessage("更新商品成功");
        } else {
            //新增
            String goodsId = KeyUtil.get32UUID();
            List<MediaOutput> mediaOutputList = new ArrayList<>();
            //3.调用media服务,上传附件
            if (goodsDto.getImgFileList() != null && !goodsDto.getImgFileList().isEmpty()) {
                MultiValueMap<String, Object> param = new LinkedMultiValueMap<>();
               // MultipartFile file = goodsDto.getImgFileList().get(0);
                List<MultipartFile> files = goodsDto.getImgFileList();
                for (MultipartFile file : files) {
                    String path = request.getSession().getServletContext().getRealPath(file.getOriginalFilename());
                    File file1 = new File(path);

                    try {
                        file.transferTo(file1);
                        FileSystemResource resource = new FileSystemResource(file1);
                        param.add("uploadFile", resource);
                        String uploadResultStr = restTemplate.postForObject("http://MEDIA/media/upload", param, String.class);
                        Result uploadResult = RestTemplate2ResultConvert.convert(uploadResultStr);
                        if (!uploadResult.isSuccess()) {
                            throw new RedStoreException(ExceptionEnum.UPLOAD_FAIL);
                        }
                        List<String> fileNameList = (List) uploadResult.getData();
                        for (String fileName : fileNameList) {
                            MediaOutput output = new MediaOutput();
                            output.setGoodsId(goodsId);
                            output.setNocount("");
                            output.setType(FileTypeEnum.IMG.getCode());
                            output.setAbsolutePath(fileName);
                            mediaOutputList.add(output);
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
            if (! CollectionUtils.isEmpty(goodsDto.getVideoFileList())) {
                MultiValueMap<String, Object> param = new LinkedMultiValueMap<>();
                List<MultipartFile> files =  goodsDto.getVideoFileList();
                for (MultipartFile file : files) {
                    String path = request.getSession().getServletContext().getRealPath(file.getOriginalFilename());
                    File file1 = new File(path);

                    try {
                        file.transferTo(file1);
                        FileSystemResource resource = new FileSystemResource(file1);
                        param.add("uploadFile", resource);
                        String uploadResultStr = restTemplate.postForObject("http://MEDIA/media/upload", param, String.class);
                        Result uploadResult = RestTemplate2ResultConvert.convert(uploadResultStr);
                        if (!uploadResult.isSuccess()) {
                            throw new RedStoreException(ExceptionEnum.UPLOAD_FAIL);
                        }
                        List<String> fileNameList = (List) uploadResult.getData();
                        for (String fileName : fileNameList) {
                            MediaOutput output = new MediaOutput();
                            output.setGoodsId(goodsId);
                            output.setNocount("");
                            output.setType(FileTypeEnum.VIDEO.getCode());
                            output.setAbsolutePath(fileName);
                            mediaOutputList.add(output);
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

            }

            //4.调用media服务,新增附件表信息
            if (!mediaOutputList.isEmpty()) {
                String  addResultStr = restTemplate.postForObject("http://MEDIA/media/addList", mediaOutputList,
                        String.class);
                Result addResult = RestTemplate2ResultConvert.convert(addResultStr);
                if (!addResult.isSuccess()) {
                    throw new RedStoreException(ExceptionEnum.ADD_MEDIA_FAIL);
                }
            }

            //5.调用goods_type_Service,增加商品和类型关系
            Result addTypeResult = goodsTypeService.addType(goodsDto);
            if (!addTypeResult.isSuccess()) {
                throw new RedStoreException(ExceptionEnum.ADD_GOODS_FAIL);
            }
            //6.调用goodsService,增加商品
            goodsDto.setId(goodsId);
            Result addGoodsResult = goodsInfoService.addGoods(goodsDto);
            if (!addGoodsResult.isSuccess()) {
                throw new RedStoreException(ExceptionEnum.ADD_GOODS_FAIL);
            }
            return Result.createBySuccessMessage("新增商品成功");

        }

    }



    //    delFile[删除商品宣传短视频]  DelPicture[删除商品图片]
    public Result delFile(Integer mediaId, String userId) {
        if (mediaId==null && StringUtils.isBlank(userId)) {
            return Result.createByErrorMessage("商品删除文件,参数错误");
        }
        try {
            //调用媒体服务删除,会根据userId查询是否属于该用户
            String strDelResult = restTemplate.getForObject("http://MEDIA/media/delete?mediaId={1}&userId={2}",
                    String.class,
                    mediaId, userId);
            Result delResult = RestTemplate2ResultConvert.convert(strDelResult);
            log.info("删除商品文件,查询附件表,result:{}", delResult.getMsg());
            if (!delResult.isSuccess()){
                return Result.createByErrorMessage("删除失败");
            }
            return Result.createBySuccess(delResult);

        } catch (RestClientException e) {
            //做处理
            log.info("删除商品文件,mediaId={},userId={}", mediaId,userId);
            throw new RedStoreException(ExceptionEnum.DELETE_GOODS_FAIL.getCode(),e.getMessage());
        }
    }



}
