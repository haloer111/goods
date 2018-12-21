package com.aojing.redstore.goods.service;

import com.aojing.redstore.goods.common.Result;
import com.aojing.redstore.goods.dto.QueryCountDto;
import com.aojing.redstore.goods.pojo.GoodsComentInfo;
import com.aojing.redstore.goods.vo.CommentVo;

import java.util.List;
import java.util.Map;

/**
 * @author gexiao
 * @date 2018/12/6 10:41
 */
public interface GoodsComentInfoService {
    //    comment[评论商品]
    Result comment(GoodsComentInfo comentInfo);

    //    discomment[退回评论]
    Result discomment(String id, String commenterId);

    /**
     * 查询评论数,根据商品id集合
     * @param goodsIdList 商品id集合
     * @return
     */
     Result<List<Map<String, Object>>> commentCount(QueryCountDto queryCountDto);

    /**
     * 查询该商品的全部评论信息
     * @param goodsId
     * @return
     */
     Result<List<CommentVo>> getAllCommentByGoodsId(String goodsId);
}
