package com.aojing.redstore.goods.dao;

import com.aojing.redstore.goods.pojo.GoodsLikeInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface GoodsLikeInfoMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(GoodsLikeInfo record);

    int insertSelective(GoodsLikeInfo record);

    GoodsLikeInfo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(GoodsLikeInfo record);

    int updateByPrimaryKey(GoodsLikeInfo record);




    //manual
    List<GoodsLikeInfo> queryByLikerIdOrGoodsId(String likerId, String goodsId);

    Integer queryLikeInfoCount(String goodsId);

    List<Map<String, Object>> queryLikeInfoCountList(@Param("goodsIdList") List<String> goodsIdList);
}