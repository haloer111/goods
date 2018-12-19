package com.aojing.redstore.goods.service.impl;

import com.aojing.redstore.goods.common.Result;
import com.aojing.redstore.goods.dao.GoodsLikeInfoMapper;
import com.aojing.redstore.goods.pojo.GoodsLikeInfo;
import com.aojing.redstore.goods.service.GoodsLikeInfoService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

        GoodsLikeInfo likeInfo = new GoodsLikeInfo();
        likeInfo.setLikerId(likerId);
        likeInfo.setGoodsId(goodsId);
        likeInfo.setCreateTime(new Date());
        likeInfo.setUpdateTime(new Date());

        int result = goodsLikeInfoMapper.insertSelective(likeInfo);
        if (result > 0) {
            return Result.createBySuccessMessage("点赞,成功");

        }
        return Result.createByErrorMessage("点赞,失败");
    }

    Result queryLikeInfo(String likerId, String goodsId) {
        if (StringUtils.isNotBlank(goodsId) && StringUtils.isNotBlank(likerId)) {
            return Result.createByErrorMessage("查询,参数不正确");
        }
        List<GoodsLikeInfo> likeInfoList = goodsLikeInfoMapper.queryByLikerIdOrGoodsId(likerId, goodsId);
        if (likeInfoList.size() > 0) {
            return Result.createBySuccessMessage("查询,成功");
        }
        return Result.createByErrorMessage("查询,失败");
    }

    public Result<Integer> queryLikeInfoCount(String goodsId) {
        if (StringUtils.isBlank(goodsId)) {
            return Result.createByErrorMessage("查询,参数不正确");
        }
        Integer count = goodsLikeInfoMapper.queryLikeInfoCount(goodsId);
        return Result.createBySuccess(count);
    }

    @Override
    public Result<List<Map<String, Object>>> queryLikeInfoCount(List<String> goodsIdList) {
        List<Map<String, Object>> countList = goodsLikeInfoMapper.queryLikeInfoCountList(goodsIdList);
        return Result.createBySuccess(countList);
    }


}
