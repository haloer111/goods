package com.aojing.redstore.goods.service;

import com.aojing.redstore.goods.common.Result;

/**
 * @author gexiao
 * @date 2018/12/6 10:21
 */
public interface GoodsLikeInfoService {
    //    giveLike[点赞]
    Result giveLike(String likerId, String goodsId);
}
