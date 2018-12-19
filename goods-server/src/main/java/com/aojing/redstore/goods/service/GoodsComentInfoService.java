package com.aojing.redstore.goods.service;

import com.aojing.redstore.goods.common.Result;
import com.aojing.redstore.goods.pojo.GoodsComentInfo;

/**
 * @author gexiao
 * @date 2018/12/6 10:41
 */
public interface GoodsComentInfoService {
    //    comment[评论商品]
    Result comment(GoodsComentInfo comentInfo);

    //    discomment[退回评论]
    Result discomment(Integer id, String commenterId);
}
