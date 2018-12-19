package com.aojing.redstore.goods.mservice.impl;

import com.aojing.redstore.goods.common.Result;
import com.aojing.redstore.goods.common.SearchHistoryAndAutoMatchs;
import com.aojing.redstore.goods.dao.GoodsInfoMapper;
import com.aojing.redstore.goods.dto.GoodsDto;
import com.aojing.redstore.goods.enums.ExceptionEnum;
import com.aojing.redstore.goods.enums.FileTypeEnum;
import com.aojing.redstore.goods.exception.RedStoreException;
import com.aojing.redstore.goods.form.FileTypeForm;
import com.aojing.redstore.goods.mservice.GoodsMService;
import com.aojing.redstore.goods.pojo.GoodsInfo;
import com.aojing.redstore.goods.service.GoodsInfoService;
import com.aojing.redstore.goods.service.GoodsLikeInfoService;
import com.aojing.redstore.goods.service.GoodsTypeService;
import com.aojing.redstore.goods.util.KeyUtil;
import com.aojing.redstore.goods.common.GoodsInfoVo;
import com.aojing.redstore.goods.vo.GoodsSearchVo;
import com.aojing.redstore.media.client.MediaClient;
import com.aojing.redstore.media.common.ImgInput;
import com.aojing.redstore.media.common.MediaOutput;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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
    private MediaClient mediaClient;

    @Autowired
    GoodsInfoService goodsInfoService;
    @Autowired
    GoodsTypeService goodsTypeService;
    @Autowired
    GoodsLikeInfoService goodsLikeInfoService;

    @Autowired
    GoodsInfoMapper goodsInfoMapper;
    @Autowired
    private SearchHistoryAndAutoMatchs searchService;

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

   /*  ======================== posttemplate的实现文件上传==========================
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

          ================================posttemplate的实现文件上传=======================*/

            /*====================================feign实现多文件上传start==============================*/
            if (CollectionUtils.isEmpty(goodsDto.getFileTypeFormList())) {
                return Result.createByErrorMessage("新增商品失败,需要传图片");

            }
            for (FileTypeForm fileTypeForm : goodsDto.getFileTypeFormList()) {
                // 放入对应的file_type中
                FileTypeEnum fileTypeEnum = FileTypeEnum.codeOf(fileTypeForm.getType());
                List<String> fileNameList = null;
                try {
                    fileNameList = mediaClient.upload(fileTypeForm.getFileList());
                } catch (Exception e) {
                    throw new RedStoreException(ExceptionEnum.UPLOAD_FAIL);
                }
                for (String fileName : fileNameList) {
                    MediaOutput output = new MediaOutput();
                    output.setGoodsId(goodsId);
                    output.setNocount("");
                    output.setType(fileTypeEnum.getCode());
                    output.setAbsolutePath(fileName);
                    mediaOutputList.add(output);
                }
            }
            /*====================================feign实现多文件上传end==============================*/


            //4.调用media服务,新增附件表信息
            if (!CollectionUtils.isEmpty(mediaOutputList)) {
                boolean result = mediaClient.addList(mediaOutputList);
                if (!result) {
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
            boolean result = mediaClient.delete(mediaId, userId);
//            boolean result = false;

            if (!result) {
                return Result.createByErrorMessage("删除失败");
            }
            return Result.createBySuccessMessage("删除成功");

        } catch (RestClientException e) {
            //做处理
            log.info("删除商品文件,mediaId={},userId={}", mediaId, userId);
            throw new RedStoreException(ExceptionEnum.DELETE_GOODS_FAIL);
        }
    }

    @Override
    public Result<PageInfo> queryGoodsList(String categoryId, int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        //1.查询商品类目关系表
        Result<List<String>> goodsIdResult = goodsTypeService.queryGoodsIdByTypeId(categoryId);
        List<String> goodsIdList = goodsIdResult.getData();

        // 组装goodsVoList
        List<GoodsInfoVo> goodsInfoVoList = assembleGoodsVoList(goodsIdList);

        PageInfo pageInfo = new PageInfo(goodsIdList);
        pageInfo.setList(goodsInfoVoList);

        return Result.createBySuccess(pageInfo);
    }


    /**
     * 组装goodsVoList
     *
     * @param goodsIdList 商品id集合
     * @return
     */
    public List<GoodsInfoVo> assembleGoodsVoList(List<String> goodsIdList) {
        List<GoodsInfoVo> goodsInfoVoList = new ArrayList<>();
        //2.查询背景图片[媒体服务]
//        List<ImgInput> bgImgList = mediaClient.getImgByType(goodsIdList, FileTypeEnum.BG_IMG.getCode());
        List<ImgInput> imgAllByType = mediaClient.getImgAllByType(goodsIdList);


        if (CollectionUtils.isEmpty(imgAllByType)) {
            throw new RedStoreException(ExceptionEnum.QUERY_MEDIA_FAIL);
        }
        //点赞数的查询
        Result<List<Map<String, Object>>> likeCountList =
                goodsLikeInfoService.queryLikeInfoCount(goodsIdList);

        //商品查询
        List<GoodsInfo> goodsList = goodsInfoService.queryByIdList(goodsIdList);
        if (CollectionUtils.isEmpty(goodsList)) {
            log.error("{},goodsIdList={}", ExceptionEnum.PRODUCT_NOT_EXIST.getMessage(), goodsIdList);
            throw new RedStoreException(ExceptionEnum.PRODUCT_NOT_EXIST);
        }
        //组装id,bgimg和其他图片,视频信息
        for (ImgInput input : imgAllByType) {
            GoodsInfoVo goodsInfoVo = new GoodsInfoVo();
            BeanUtils.copyProperties(input, goodsInfoVo);
            goodsInfoVo.setGoodsId(input.getGoodsId());
            goodsInfoVoList.add(goodsInfoVo);

            //组装点赞数
            for (Map<String, Object> item : likeCountList.getData()) {
                if (goodsInfoVo.getGoodsId().equals(item.get("goodsId"))) {
                    goodsInfoVo.setLikeCount(Integer.parseInt(String.valueOf(item.get("likeCount"))));
                }
            }

            //组装商品名,内容,用户id
            for (GoodsInfo goods : goodsList) {
                if (goodsInfoVo.getGoodsId().equals(goods.getId())) {
                    goodsInfoVo.setGoodsName(goods.getName());
                    goodsInfoVo.setPrice(goods.getPrice().doubleValue());
                    //不清楚用那个字段
                    goodsInfoVo.setContent(goods.getDetail());
                    goodsInfoVo.setUserId(goods.getSeller());
                }
            }

            //todo 根据卖家id查询 icon,username
        }

        return goodsInfoVoList;
    }

    @Override
    public Result<List<GoodsSearchVo>> serachBykeyword(String keyword) {
        keyword = new StringBuilder().append("%").append(keyword.trim()).append("%").toString();
        List<GoodsInfo> goodsInfoList = goodsInfoMapper.serachBykeyword(keyword);
        List<GoodsSearchVo> searchVoList = goodsInfoList.stream().map(e -> new GoodsSearchVo(e.getId(), e.getName(),
                e.getStoreName())).collect(Collectors.toList());
        //todo 商家图片无法处理
        //更新redis
        searchService.updateList("user1", keyword);

        return Result.createBySuccess(searchVoList);
    }


}
