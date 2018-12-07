package com.aojing.redstore.goods.service.impl;

import java.util.Date;

import com.aojing.redstore.goods.common.Result;
import com.aojing.redstore.goods.convert.RestTemplate2ResultConvert;
import com.aojing.redstore.goods.dao.GoodsInfoMapper;
import com.aojing.redstore.goods.dto.GoodsDto;
import com.aojing.redstore.goods.enums.ExceptionEnum;
import com.aojing.redstore.goods.enums.GoodsStatusEnum;
import com.aojing.redstore.goods.enums.PromotionEnum;
import com.aojing.redstore.goods.exception.RedStoreException;
import com.aojing.redstore.goods.output.MediaOutPut;
import com.aojing.redstore.goods.pojo.GoodsInfo;
import com.aojing.redstore.goods.service.GoodsInfoService;
import com.aojing.redstore.goods.util.KeyUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.math.BigDecimal;
import java.util.*;

/**
 * @author gexiao
 * @date 2018/12/5 15:10
 */
@Service
@Slf4j
public class GoodsInfoServiceImpl implements GoodsInfoService {

    @Autowired
    private GoodsInfoMapper goodsInfoMapper;

    @Autowired
    RestTemplate restTemplate;

    //    UploadMovie[上传视频] UploadPicture[上传商品图片]
    public Result UploadMovie(MultipartFile file, String path) {
        if (file == null || path == null) {
            return Result.createByErrorMessage("商品上传,参数错误");
        }
        Map map = new HashMap();
        map.put("file", file);
        map.put("path", path);
        try {
            Result o = restTemplate.postForObject("http://media/upload", map, Result.class);
        } catch (RestClientException e) {
            //做处理
            throw new RedStoreException(-1, "未找到数据");
        }
        return Result.createBySuccess();
    }

    //    delMovie[删除商品宣传短视频]  DelPicture[删除商品图片]
    public Result delMovie(String fileName, String mediaId) {
        if (StringUtils.isBlank(fileName) && StringUtils.isBlank(mediaId)) {
            return Result.createByErrorMessage("商品删除文件,参数错误");
        }
        try {
            //getForObject,请求参数的用法
            //ResponseEntity<String> responseEntity = restTemplate.getForEntity
            // ("http://HELLO-SERVICE/sayhello?name={1}", String.class, "张三");
//            ResponseEntity<String> responseEntity = restTemplate.getForEntity
//            ("http://HELLO-SERVICE/sayhello?name={name}", String.class, map);

            //根据主键查询附件表.得到文件名
            String data = restTemplate.getForObject("http://MEDIA/media/query?id={1}", String.class, "1");
            Result convertResult = RestTemplate2ResultConvert.convert(data);
            String fileName2 = (String) convertResult.getData();
            // log.info("==============data:{}",result.getData());
            //根据文件名来删除
            String delResult = restTemplate.getForObject("http://MEDIA/media/delete?mediaId={1}", String.class,
                    fileName2);

            return Result.createBySuccess(RestTemplate2ResultConvert.convert(delResult).getData());

        } catch (RestClientException e) {
            //做处理
            throw new RedStoreException(-1, "未找到数据");
        }
    }


    //    Query[根据商品类型查询商品信息]
    Result Query(GoodsInfo goodsInfo) {
        if (goodsInfo == null) {
            return Result.createByErrorMessage("查询商品,参数不正确");
        }
        List<GoodsInfo> goodsInfoList = goodsInfoMapper.queryByGoodsInfo(goodsInfo);
        if (!goodsInfoList.isEmpty()) {
            return Result.createBySuccess("查询商品,成功", goodsInfoList);

        }
        return Result.createByErrorMessage("查询商品,失败");

    }

    //    Add[添加商品（新建商品）] or Updata[修改商品信息]  ChangeStatus[修改商品状态]
    Result AddOrUpdate(GoodsDto goodsDto) {
        if (goodsDto != null) {
            if (goodsDto.getId() != null) {
                //todo
              /*  int rowCount = goodsInfoMapper.updateByPrimaryKey();
                if (rowCount > 0) {
                    return Result.createBySuccess("更新商品成功");
                }
                return Result.createBySuccess("更新商品失败");*/
            } else {
                // 1. uuid生成goodsid,
                String goodsId = KeyUtil.get32UUID();
                String goodscode = KeyUtil.getGoodCode();
                //todo 商品id,media_id应该用String来存储

                GoodsInfo goodsInfo = new GoodsInfo();
                BeanUtils.copyProperties(goodsDto, goodsInfo);
                goodsInfo.setId(goodsId);
                goodsInfo.setGoodsCode(goodscode);
                goodsInfo.setSalesPromotion(PromotionEnum.UN_Promotion.getCode());
                goodsInfo.setSellStatus(GoodsStatusEnum.ON_SALE.getCode());
                goodsInfo.setCreateTime(new Date());
                goodsInfo.setUpdateTime(new Date());
                goodsInfo.setPrice(new BigDecimal("0"));
//                goodsInfo.setMediaId(Integer.parse(goodsId));
                // 2. 判断是否有图片附件列表
                if (!goodsDto.getImgFileList().isEmpty()) {
                    //  2.1上传附件
                    for (MultipartFile img : goodsDto.getImgFileList()) {
                        //todo 目前采取用pojo类来传递,后期改进
                        MediaOutPut mediaOutPut = new MediaOutPut();
                        mediaOutPut.setGoodsId(goodsId);
                        //todo 卖家/门户Id,使用user服务的id,目前写死
                        mediaOutPut.setEntityId("1232");
                        mediaOutPut.setType(10);
                        mediaOutPut.setImgFile(img);
                        // 2.2 调用商品服务
                        String data = restTemplate.postForObject("http://MEDIA/media/add", mediaOutPut, String.class);
                        Result convertResult = RestTemplate2ResultConvert.convert(data);
                        if (!convertResult.isSuccess()) {
                            throw new RedStoreException(ExceptionEnum.UPLOAD_FAIL);
                        }
                    }
                }
                if (!goodsDto.getVideoFileList().isEmpty()) {
                    //  2.1上传附件
                    for (MultipartFile video : goodsDto.getImgFileList()) {
                        //todo 目前采取用pojo类来传递,后期改进
                        MediaOutPut mediaOutPut = new MediaOutPut();
                        mediaOutPut.setGoodsId(goodsId);
                        //todo 卖家/门户Id,使用user服务的id,目前写死
                        mediaOutPut.setEntityId("1232");
                        mediaOutPut.setType(20);
                        mediaOutPut.setVideoFile(video);
                        // 2.2 调用商品服务
                        String data = restTemplate.postForObject("http://MEDIA/media/add", mediaOutPut, String.class);
                        Result convertResult = RestTemplate2ResultConvert.convert(data);
                        if (!convertResult.isSuccess()) {
                            throw new RedStoreException(ExceptionEnum.UPLOAD_FAIL);
                        }
                    }
                }

                int rowCount = goodsInfoMapper.insert(goodsInfo);
                if (rowCount > 0) {
                    return Result.createBySuccess("新增商品成功");
                }
                return Result.createBySuccess("新增商品失败");
            }
        }
        return Result.createByErrorMessage("新增或更新商品参数不正确");

    }

    //    Delete[删除商品]
    Result Delete(String goodsId) {
        if (goodsId == null) {
            return Result.createByErrorMessage("删除商品,参数不正确");
        }
        int result = goodsInfoMapper.deleteByPrimaryKey(goodsId);
        if (result > 0) {
            return Result.createBySuccessMessage("删除商品,成功");
        }
        return Result.createByErrorMessage("删除商品,失败");
    }


}
