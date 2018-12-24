package com.aojing.redstore.goods.dao;

import com.aojing.redstore.goods.pojo.GoodsExamineInfo;
import com.aojing.redstore.goods.pojo.GoodsHotSell;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

public interface GoodsHotSellMapper  extends BaseMapper<GoodsHotSell> {
    int deleteByPrimaryKey(String id);

    int insert(GoodsHotSell record);

    int insertSelective(GoodsHotSell record);

    GoodsHotSell selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(GoodsHotSell record);

    int updateByPrimaryKey(GoodsHotSell record);
}