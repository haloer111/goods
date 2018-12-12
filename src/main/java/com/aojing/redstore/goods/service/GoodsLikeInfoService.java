package com.aojing.redstore.goods.service;

import com.aojing.redstore.goods.common.Result;

import java.util.List;

/**
 * @author gexiao
 * @date 2018/12/6 10:21
 */
public interface GoodsLikeInfoService {
    /**
     * 点赞
     * @param likerId 点赞人id
     * @param goodsId 商品id
     * @return
     */
    Result giveLike(String likerId, String goodsId);

    /**
     * 根据商品id查询点赞数
     * @param goodsId 商品id
     * @return
     */
    Result<Integer> queryLikeInfoCount( String goodsId);

}
