package com.aojing.redstore.goods.mservice.impl;

import com.aojing.redstore.goods.enums.ExceptionEnum;
import com.aojing.redstore.goods.enums.FileTypeEnum;
import com.aojing.redstore.goods.exception.RedStoreException;
import com.aojing.redstore.goods.pojo.GoodsType;
import com.aojing.redstore.goods.service.GoodsInfoService;

import com.aojing.redstore.goods.output.MediaOutput;

import com.aojing.redstore.goods.common.Result;
import com.aojing.redstore.goods.dto.GoodsDto;
import com.aojing.redstore.goods.mservice.GoodsMService;
import com.aojing.redstore.goods.service.GoodsTypeService;
import com.aojing.redstore.goods.util.KeyUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * @author gexiao
 * @date 2018/12/09 下午 04:56
 */
public class GoodsMServiceImpl implements GoodsMService {

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    GoodsInfoService goodsInfoService;
    @Autowired
    GoodsTypeService goodsTypeService;

    @Override
    public Result addGoods(GoodsDto goodsDto) {
        String goodsId = KeyUtil.get32UUID();
        List<MediaOutput> mediaOutputList = new ArrayList<>();
//        3.调用media服务,上传附件
        if (!goodsDto.getImgFileList().isEmpty()) {
            File file = goodsDto.getImgFileList().get(0);
            FileSystemResource resource = new FileSystemResource(file);
            MultiValueMap<String, Object> param = new LinkedMultiValueMap<>();
            param.add("uploadFile", resource);
            Result uploadResult = restTemplate.postForObject("http://MEDIA/media/upload", param, Result.class);
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

        }
        if (!goodsDto.getVideoFileList().isEmpty()) {
            File file = goodsDto.getVideoFileList().get(0);
            FileSystemResource resource = new FileSystemResource(file);
            MultiValueMap<String, Object> param = new LinkedMultiValueMap<>();
            param.add("uploadFile", resource);
            Result uploadResult = restTemplate.postForObject("http://MEDIA/media/upload", param, Result.class);
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

        }

        //4.调用media服务,新增附件表信息
        if (!mediaOutputList.isEmpty()) {
            Result addResult = restTemplate.postForObject("http://MEDIA/media/addList", mediaOutputList, Result.class);
            if (!addResult.isSuccess()) {
                throw new RedStoreException(ExceptionEnum.ADD_MEDIA_FAIL);
            }
        }

//        5.调用goods_type_Service,增加商品和类型关系
        Result addTypeResult = goodsTypeService.addType(goodsDto);
        if (!addTypeResult.isSuccess()) {
            throw new RedStoreException(ExceptionEnum.ADD_GOODS_FAIL);
        }
//        6.调用goodsService,增加商品
        Result addGoodsResult = goodsInfoService.addOrUpdateNew(goodsDto);
        if (!addGoodsResult.isSuccess()) {
            throw new RedStoreException(ExceptionEnum.ADD_GOODS_FAIL);
        }
        return Result.createBySuccessMessage("新增商品成功");
    }
}
