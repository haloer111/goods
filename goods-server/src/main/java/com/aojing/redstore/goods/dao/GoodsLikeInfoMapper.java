package com.aojing.redstore.goods.dao;

import com.aojing.redstore.goods.pojo.GoodsLikeInfo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface GoodsLikeInfoMapper  extends BaseMapper<GoodsLikeInfo> {
    int deleteByPrimaryKey(String id);

    int insert(GoodsLikeInfo record);

    int insertSelective(GoodsLikeInfo record);

    GoodsLikeInfo selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(GoodsLikeInfo record);

    int updateByPrimaryKey(GoodsLikeInfo record);


    //manual
    GoodsLikeInfo queryByLikerIdAndGoodsIdOrSellerId(@Param("likerId")String likerId,
                                                     @Param("goodsId")String goodsId,
                                                     @Param("sellerId")String sellerId);

    Integer queryLikeInfoCount(String goodsId);

    List<Map<String, Object>> queryGiveLikeCountListByGoodsId(@Param("goodsIdList") List<String> goodsIdList);

    Integer queryLikeInfoCountByCommentId(String commentId);

    List<Map<String, Object>> queryGiveLikeCountListBySellerId(@Param("sellerIdList")List<String> sellerIdList);

}