package com.aojing.redstore.goods.dao;

import com.aojing.redstore.goods.pojo.GoodsExamineInfo;
import com.aojing.redstore.goods.pojo.GoodsSetMenu;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

public interface GoodsSetMenuMapper extends BaseMapper<GoodsSetMenu> {
    int deleteByPrimaryKey(String id);

    int insert(GoodsSetMenu record);

    int insertSelective(GoodsSetMenu record);

    GoodsSetMenu selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(GoodsSetMenu record);

    int updateByPrimaryKey(GoodsSetMenu record);
}