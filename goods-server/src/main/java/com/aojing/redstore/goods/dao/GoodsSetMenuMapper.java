package com.aojing.redstore.goods.dao;

import com.aojing.redstore.goods.pojo.GoodsExamineInfo;
import com.aojing.redstore.goods.pojo.GoodsSetMenu;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface GoodsSetMenuMapper  {
    int deleteByPrimaryKey(String id);

    int insert(GoodsSetMenu record);

    int insertSelective(GoodsSetMenu record);

    GoodsSetMenu selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(GoodsSetMenu record);

    int updateByPrimaryKey(GoodsSetMenu record);
}