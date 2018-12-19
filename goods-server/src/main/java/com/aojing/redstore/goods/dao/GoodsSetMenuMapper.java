package com.aojing.redstore.goods.dao;

import com.aojing.redstore.goods.pojo.GoodsSetMenu;

public interface GoodsSetMenuMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(GoodsSetMenu record);

    int insertSelective(GoodsSetMenu record);

    GoodsSetMenu selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(GoodsSetMenu record);

    int updateByPrimaryKey(GoodsSetMenu record);
}