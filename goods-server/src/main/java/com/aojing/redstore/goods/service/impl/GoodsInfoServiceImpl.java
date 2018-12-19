package com.aojing.redstore.goods.service.impl;

import com.aojing.redstore.goods.common.DecreaseStockInput;
import com.aojing.redstore.goods.common.Result;
import com.aojing.redstore.goods.dao.GoodsInfoMapper;
import com.aojing.redstore.goods.dto.GoodsDto;
import com.aojing.redstore.goods.enums.ExceptionEnum;
import com.aojing.redstore.goods.enums.GoodsStatusEnum;
import com.aojing.redstore.goods.enums.PromotionEnum;
import com.aojing.redstore.goods.exception.RedStoreException;
import com.aojing.redstore.goods.pojo.GoodsInfo;
import com.aojing.redstore.goods.service.GoodsInfoService;
import com.aojing.redstore.goods.util.KeyUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    //    Query[根据商品类型查询商品信息]
    public Result<List<GoodsInfo>> queryBySelective(GoodsInfo goodsInfo) {
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
//        if (goodsDto != null) {
//            if (goodsDto.getId() != null) {
//                //todo
//              /*  int rowCount = goodsInfoMapper.updateByPrimaryKey();
//                if (rowCount > 0) {
//                    return Result.createBySuccess("更新商品成功");
//                }
//                return Result.createBySuccess("更新商品失败");*/
//            } else {
//                // 1. uuid生成goodsid,
//                String goodsId = KeyUtil.get32UUID();
//                String goodscode = KeyUtil.getGoodCode();
//                //todo 商品id,media_id应该用String来存储
//
//                GoodsInfo goodsInfo = new GoodsInfo();
//                BeanUtils.copyProperties(goodsDto, goodsInfo);
//                goodsInfo.setId(goodsId);
//                goodsInfo.setGoodsCode(goodscode);
//                goodsInfo.setSalesPromotion(PromotionEnum.UN_Promotion.getCode());
//                goodsInfo.setSellStatus(GoodsStatusEnum.ON_SALE.getCode());
//                goodsInfo.setCreateTime(new Date());
//                goodsInfo.setUpdateTime(new Date());
//                goodsInfo.setPrice(new BigDecimal("0"));
////                goodsInfo.setMediaId(Integer.parse(goodsId));
//                // 2. 判断是否有图片附件列表
//                if (!goodsDto.getImgFileList().isEmpty()) {
//                    //  2.1上传附件
//                    for (MultipartFile img : goodsDto.getImgFileList()) {
//                        //todo 目前采取用pojo类来传递,后期改进
//                        MediaOutput mediaOutPut = new MediaOutput();
//                        mediaOutPut.setGoodsId(goodsId);
//                        //todo 卖家/门户Id,使用user服务的id,目前写死
//                        mediaOutPut.setEntityId("1232");
//                        mediaOutPut.setType(10);
//                        mediaOutPut.setImgFile(img);
//                        // 2.2 调用商品服务
//                        String data = restTemplate.postForObject("http://MEDIA/media/add", mediaOutPut, String.class);
//                        Result convertResult = RestTemplate2ResultConvert.convert(data);
//                        if (!convertResult.isSuccess()) {
//                            throw new RedStoreException(ExceptionEnum.UPLOAD_FAIL);
//                        }
//                    }
//                }
//                if (!goodsDto.getVideoFileList().isEmpty()) {
//                    //  2.1上传附件
//                    for (MultipartFile video : goodsDto.getImgFileList()) {
//                        //todo 目前采取用pojo类来传递,后期改进
//                        MediaOutput mediaOutPut = new MediaOutput();
//                        mediaOutPut.setGoodsId(goodsId);
//                        //todo 卖家/门户Id,使用user服务的id,目前写死
//                        mediaOutPut.setEntityId("1232");
//                        mediaOutPut.setType(20);
//                        mediaOutPut.setVideoFile(video);
//                        // 2.2 调用商品服务
//                        String data = restTemplate.postForObject("http://MEDIA/media/add", mediaOutPut, String.class);
//                        Result convertResult = RestTemplate2ResultConvert.convert(data);
//                        if (!convertResult.isSuccess()) {
//                            throw new RedStoreException(ExceptionEnum.UPLOAD_FAIL);
//                        }
//                    }
//                }
//
//                int rowCount = goodsInfoMapper.insert(goodsInfo);
//                if (rowCount > 0) {
//                    return Result.createBySuccess("新增商品成功");
//                }
//                return Result.createBySuccess("新增商品失败");
//            }
//        }
        return Result.createByErrorMessage("新增或更新商品参数不正确");

    }


    public Result updateGoods(GoodsDto goodsDto) {
        if (goodsDto != null) {
            GoodsInfo goods = new GoodsInfo();
            if (goodsDto.getId() != null) {
                //更新
                BeanUtils.copyProperties(goodsDto, goods);
                goods.setUpdateTime(new Date());
                int rowCount = goodsInfoMapper.updateByPrimaryKey(goods);
                if (rowCount > 0) {
                    return Result.createBySuccess("更新商品成功");
                }
                return Result.createBySuccess("更新商品失败");
            }
        }
        return Result.createByErrorMessage("新增或更新商品参数不正确");

    }

    public Result addGoods(GoodsDto goodsDto) {
        if (goodsDto != null) {
            GoodsInfo goods = new GoodsInfo();
            // 新增
            // 1. uuid生成goodsid,
            String goodscode = KeyUtil.getGoodCode();

            BeanUtils.copyProperties(goodsDto, goods);
            goods.setGoodsCode(goodscode);
            goods.setSalesPromotion(PromotionEnum.UN_PROMOTION.getCode());
            goods.setSellStatus(GoodsStatusEnum.ON_SALE.getCode());
            goods.setCreateTime(new Date());
            goods.setUpdateTime(new Date());
            goods.setPrice(new BigDecimal("0"));

            int rowCount = goodsInfoMapper.insert(goods);
            if (rowCount > 0) {
                return Result.createBySuccess("新增商品成功");
            }
            return Result.createBySuccess("新增商品失败");
        }
        return Result.createByErrorMessage("新增或更新商品参数不正确");

    }

    @Override
    public List<GoodsInfo> queryByIdList(List<String> goodsIdList) {
        List<GoodsInfo> result = goodsInfoMapper.queryByIdList(goodsIdList);
        if (!CollectionUtils.isEmpty(result)) {
            return result;
        }
        return null;

    }

    //    Delete[删除商品]
    public Result delete(String goodsId) {
        if (goodsId == null) {
            return Result.createByErrorMessage("删除商品,参数不正确");
        }
        int result = goodsInfoMapper.deleteByPrimaryKey(goodsId);
        if (result > 0) {
            return Result.createBySuccessMessage("删除商品,成功");
        }
        return Result.createByErrorMessage("删除商品,失败");
    }

    public void decreaseStock(List<DecreaseStockInput> decreaseStockInputList) {
        for (DecreaseStockInput decreaseStockInput : decreaseStockInputList) {
            // 1.检查数量是否大于库存数
            GoodsInfo goodsInfo = new GoodsInfo();
            goodsInfo.setId(decreaseStockInput.getGoodsId());
            List<GoodsInfo> goodsInfoList = goodsInfoMapper.queryByGoodsInfo(goodsInfo);
            if (CollectionUtils.isEmpty(goodsInfoList)) {
                throw new RedStoreException(ExceptionEnum.PRODUCT_NOT_EXIST);
            }
            Integer stock = goodsInfoList.get(0).getStock();
            if (stock < decreaseStockInput.getGoodsCount()) {
                throw new RedStoreException(ExceptionEnum.PRODUCT_UNDERSTOCK);
            }

            // 2.减库存
            stock = stock - decreaseStockInput.getGoodsCount();

            // 3.更新数据
            goodsInfo.setStock(stock);
            goodsInfoMapper.updateByPrimaryKeySelective(goodsInfo);
        }

    }
}
