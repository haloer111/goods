package com.aojing.redstore.goods.service.impl;

import com.aojing.redstore.goods.common.Result;
import com.aojing.redstore.goods.dao.GoodsLikeInfoMapper;
import com.aojing.redstore.goods.dto.QueryCountDto;
import com.aojing.redstore.goods.pojo.GoodsLikeInfo;
import com.aojing.redstore.goods.service.GoodsLikeInfoService;
import com.aojing.redstore.goods.util.KeyUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author gexiao
 * @date 2018/12/6 10:21
 */
@Service
public class GoodsLikeInfoServiceImpl implements GoodsLikeInfoService {

    @Autowired
    GoodsLikeInfoMapper goodsLikeInfoMapper;

    //    giveLike[点赞]
    public Result giveLike(String likerId, String goodsId) {

        if (StringUtils.isNotBlank(goodsId) && StringUtils.isNotBlank(likerId)) {
            return Result.createByErrorMessage("点赞,参数不正确");
        }
        // 1.查询是否点过赞
        Result<GoodsLikeInfo> queryResult = this.queryLikeInfo(likerId, goodsId);
        if (queryResult.isSuccess()) {
            String id = queryResult.getData().getId();
            //如果已经点过赞则触发删除
            int deleteResult = goodsLikeInfoMapper.deleteByPrimaryKey(id);
            if (deleteResult > 0) {
                return Result.createBySuccess();
            }
            return Result.createByError();
        } else {
            //否则添加一条数据
            GoodsLikeInfo likeInfo = new GoodsLikeInfo();
            likeInfo.setId(KeyUtil.getkey());
            likeInfo.setLikerId(likerId);
            likeInfo.setGoodsId(goodsId);
            likeInfo.setCreateTime(new Date());

            int result = goodsLikeInfoMapper.insertSelective(likeInfo);
            if (result > 0) {
                return Result.createBySuccess();
            }
            return Result.createByError();
        }
    }

    Result<GoodsLikeInfo> queryLikeInfo(String likerId, String goodsId) {
        if (StringUtils.isBlank(goodsId) && StringUtils.isBlank(likerId)) {
            return Result.createByErrorMessage("查询,参数不正确");
        }
        GoodsLikeInfo likeInfoList = goodsLikeInfoMapper.queryByLikerIdAndGoodsId(likerId, goodsId);
        if (likeInfoList != null) {
            return Result.createBySuccess(likeInfoList);
        }
        return Result.createByError();
    }

    public Result<Integer> queryLikeInfoCount(String goodsId) {
        if (StringUtils.isBlank(goodsId)) {
            return Result.createByErrorMessage("查询,参数不正确");
        }
        Integer count = goodsLikeInfoMapper.queryLikeInfoCount(goodsId);
        return Result.createBySuccess(count);
    }

    @Override
    public Result<List<Map<String, Object>>> queryLikeInfoCount(QueryCountDto queryCountDto) {
        List<Map<String, Object>> countList=null;
        if (!CollectionUtils.isEmpty(queryCountDto.getGoodsIdList())){
             countList = goodsLikeInfoMapper.queryGiveLikeCountListByGoodsId(queryCountDto.getGoodsIdList());
        }
        if (!CollectionUtils.isEmpty(queryCountDto.getSellerIdList())){
             countList = goodsLikeInfoMapper.queryGiveLikeCountListBySellerId(queryCountDto.getSellerIdList());
        }
        return Result.createBySuccess(countList);
    }


}
