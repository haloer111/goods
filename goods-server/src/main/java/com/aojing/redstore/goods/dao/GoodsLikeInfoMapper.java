package com.aojing.redstore.goods.dao;

import com.aojing.redstore.goods.pojo.GoodsExamineInfo;
import com.aojing.redstore.goods.pojo.GoodsLikeInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface GoodsLikeInfoMapper  {
    int deleteByPrimaryKey(String id);

    int insert(GoodsLikeInfo record);

    int insertSelective(GoodsLikeInfo record);

    GoodsLikeInfo selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(GoodsLikeInfo record);

    int updateByPrimaryKey(GoodsLikeInfo record);


    //manual
    GoodsLikeInfo queryByLikerIdAndGoodsId(String likerId, String goodsId);

    Integer queryLikeInfoCount(String goodsId);

    List<Map<String, Object>> queryGiveLikeCountListByGoodsId(@Param("goodsIdList") List<String> goodsIdList);

    Integer queryLikeInfoCountByCommentId(String commentId);

    List<Map<String, Object>> queryGiveLikeCountListBySellerId(@Param("sellerIdList")List<String> sellerIdList);

}