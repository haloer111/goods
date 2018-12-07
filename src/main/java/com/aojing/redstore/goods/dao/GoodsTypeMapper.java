package com.aojing.redstore.goods.dao;

import com.aojing.redstore.goods.pojo.GoodsType;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface GoodsTypeMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(GoodsType record);

    int insertSelective(GoodsType record);

    GoodsType selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(GoodsType record);

    int updateByPrimaryKey(GoodsType record);
}