package com.aojing.redstore.goods.dao;

import com.aojing.redstore.goods.pojo.GoodsHotSell;

public interface GoodsHotSellMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(GoodsHotSell record);

    int insertSelective(GoodsHotSell record);

    GoodsHotSell selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(GoodsHotSell record);

    int updateByPrimaryKey(GoodsHotSell record);
}