package com.aojing.redstore.goods.dao;

import com.aojing.redstore.goods.pojo.GoodsRecomend;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface GoodsRecomendMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(GoodsRecomend record);

    int insertSelective(GoodsRecomend record);

    GoodsRecomend selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(GoodsRecomend record);

    int updateByPrimaryKey(GoodsRecomend record);
}