package com.aojing.redstore.goods.mservice.impl;

import com.aojing.redstore.goods.convert.RestTemplate2ResultConvert;
import com.aojing.redstore.goods.enums.ExceptionEnum;
import com.aojing.redstore.goods.enums.FileTypeEnum;
import com.aojing.redstore.goods.exception.RedStoreException;
import com.aojing.redstore.goods.form.FileTypeForm;
import com.aojing.redstore.goods.output.MediaOutput;
import com.aojing.redstore.goods.pojo.GoodsInfo;
import com.aojing.redstore.goods.service.GoodsInfoService;

import com.aojing.redstore.goods.common.Result;
import com.aojing.redstore.goods.dto.GoodsDto;
import com.aojing.redstore.goods.mservice.GoodsMService;
import com.aojing.redstore.goods.service.GoodsLikeInfoService;
import com.aojing.redstore.goods.service.GoodsTypeService;
import com.aojing.redstore.goods.util.KeyUtil;
import com.aojing.redstore.goods.vo.GoodsInfoVo;
import com.aojing.redstore.goods.vo.ImgVo;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    @Autowired
    GoodsLikeInfoService goodsLikeInfoService;

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
   /*         if (goodsDto.getImgFileList() != null && !goodsDto.getImgFileList().isEmpty()) {
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
                        String uploadResultStr = restTemplate.postForObject("http://MEDIA/media/upload", param,
                                String.class);
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
            }*/
            if (CollectionUtils.isEmpty(goodsDto.getFileTypeFormList())) {
                return Result.createByErrorMessage("新增商品失败,需要传图片");

            }
            for (FileTypeForm fileTypeForm : goodsDto.getFileTypeFormList()) {
                // 放入对应的file_type中
                FileTypeEnum fileTypeEnum = FileTypeEnum.codeOf(fileTypeForm.getType());
                MultiValueMap<String, Object> param = new LinkedMultiValueMap<>();
                for (MultipartFile file : fileTypeForm.getFileList()) {
                    String path = request.getSession().getServletContext().getRealPath(file.getOriginalFilename());
                    File file1 = new File(path);
                    try {
                        file.transferTo(file1);
                        FileSystemResource resource = new FileSystemResource(file1);
                        param.add("uploadFile", resource);
                        String uploadResultStr = restTemplate.postForObject("http://MEDIA/media/upload", param,
                                String.class);
                        Result uploadResult = RestTemplate2ResultConvert.convert(uploadResultStr);
                        if (!uploadResult.isSuccess()) {
                            throw new RedStoreException(ExceptionEnum.UPLOAD_FAIL);
                        }
                        List<String> fileNameList = (List) uploadResult.getData();
                        for (String fileName : fileNameList) {
                            MediaOutput output = new MediaOutput();
                            output.setGoodsId(goodsId);
                            output.setNocount("");
                            output.setType(fileTypeEnum.getCode());
                            output.setAbsolutePath(fileName);
                            mediaOutputList.add(output);
                        }
                        //删除临时文件
                        file1.delete();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

            }

            //4.调用media服务,新增附件表信息
            if (!mediaOutputList.isEmpty()) {
                String addResultStr = restTemplate.postForObject("http://MEDIA/media/addList", mediaOutputList,
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
        if (mediaId == null && StringUtils.isBlank(userId)) {
            return Result.createByErrorMessage("商品删除文件,参数错误");
        }
        try {
            //调用媒体服务删除,会根据userId查询是否属于该用户
            String strDelResult = restTemplate.getForObject("http://MEDIA/media/delete?mediaId={1}&userId={2}",
                    String.class,
                    mediaId, userId);
            Result delResult = RestTemplate2ResultConvert.convert(strDelResult);
            log.info("删除商品文件,查询附件表,result:{}", delResult.getMsg());
            if (!delResult.isSuccess()) {
                return Result.createByErrorMessage("删除失败");
            }
            return Result.createBySuccess(delResult);

        } catch (RestClientException e) {
            //做处理
            log.info("删除商品文件,mediaId={},userId={}", mediaId, userId);
            throw new RedStoreException(ExceptionEnum.DELETE_GOODS_FAIL.getCode(), e.getMessage());
        }
    }

    @Override
    public Result<PageInfo> queryGoodsList(String categoryId, int pageNum, int pageSize) {
        List<GoodsInfoVo> goodsInfoVoList = new ArrayList<>();
        //1.查询商品类目关系表
        Result<List<String>> goodsIdList = goodsTypeService.queryGoodsIdByTypeId(categoryId);

        //2.查询背景图片[媒体服务]
        Map<String, Object> map = new HashMap<>();
        map.put("goodsIdList", goodsIdList.getData());
        map.put("type", FileTypeEnum.BG_IMG.getCode());
        Result<List<ImgVo>> addResult = restTemplate.postForObject("http://MEDIA/media/getBgImg", map,
                Result.class);
//        Result<List<ImgVo>> addResult = RestTemplate2ResultConvert.convert(addResultStr);
        if (!addResult.isSuccess()) {
            throw new RedStoreException(ExceptionEnum.ADD_MEDIA_FAIL);
        }
        List<ImgVo> imgVoList =  addResult.getData();

        //组装id,bgimg
        for (Object obj : imgVoList) {
            Map map1 = (Map) obj;
            GoodsInfoVo goodsInfoVo = new GoodsInfoVo();
            goodsInfoVo.Map2GoodsInfo(map1);
           /* String goodsId= (String) map1.get("goodsId");
            String bgImg= (String) map1.get("absolutePath");
            //BeanUtils.copyProperties(imgVo, goodsInfoVo);
            goodsInfoVo.setGoodsId(goodsId);
            goodsInfoVo.setBgImg(bgImg);*/
            goodsInfoVoList.add(goodsInfoVo);
        }

        GoodsInfo goodsInfo;
        //组装点赞数
        for (GoodsInfoVo goodsInfoVo : goodsInfoVoList) {
            String goodsId = goodsInfoVo.getGoodsId();
            goodsInfoVo.setLikeCount(goodsLikeInfoService.queryLikeInfoCount(goodsId).getData());
            goodsInfo = new GoodsInfo();
            goodsInfo.setId(goodsId);
            List<GoodsInfo> listResult = goodsInfoService.queryBySelective(goodsInfo).getData();
            //组装商品名,内容,用户id
            for (GoodsInfo item : listResult) {
                if (goodsId.endsWith(item.getId())) {
                    goodsInfoVo.setGoodsName(item.getName());
                    //不清楚用那个字段
                    goodsInfoVo.setContent(item.getDetail());
                    goodsInfoVo.setUserId(item.getSeller());
                }
            }
            //todo 已有卖家id,可以查询 icon,username
        }
        PageHelper.startPage(pageNum, pageSize);
        PageInfo pageInfo = new PageInfo(goodsInfoVoList);
        pageInfo.setList(goodsInfoVoList);

        return Result.createBySuccess(pageInfo);
    }


}
