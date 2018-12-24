package com.aojing.redstore.goods.dao;

import com.aojing.redstore.goods.pojo.GoodsCategory;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

public interface GoodsCategoryMapper extends BaseMapper<GoodsCategory> {
    int deleteByPrimaryKey(String id);

    int insert(GoodsCategory record);

    int insertSelective(GoodsCategory record);

    GoodsCategory selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(GoodsCategory record);

    int updateByPrimaryKey(GoodsCategory record);

    //manual
    List<GoodsCategory> selectByParentId(String parentId);
}