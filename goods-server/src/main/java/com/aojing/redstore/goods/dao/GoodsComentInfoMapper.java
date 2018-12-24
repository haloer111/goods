package com.aojing.redstore.goods.dao;

import com.aojing.redstore.goods.pojo.GoodsComentInfo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface GoodsComentInfoMapper extends BaseMapper<GoodsComentInfo> {
    int deleteByPrimaryKey(String id);

    int insert(GoodsComentInfo record);

    int insertSelective(GoodsComentInfo record);

    GoodsComentInfo selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(GoodsComentInfo record);

    int updateByPrimaryKey(GoodsComentInfo record);


    //manual
    int deleteByIdAndUserId(@Param("id") String id, @Param("commenterId") String commenterId);

    List<Map<String, Object>> queryCommentCountByGoodsIdList(@Param("goodsIdList") List<String> goodsIdList);

    List<GoodsComentInfo> selectByGoodsId(String goodsId);

    List<Map<String, Object>> queryCommentCountBySellerIdList(@Param("sellerIdList")List<String> sellerIdList);
}