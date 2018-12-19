package com.aojing.redstore.goods.dao;

import com.aojing.redstore.goods.pojo.GoodsCategory;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface GoodsCategoryMapper {
    int deleteByPrimaryKey(String id);

    int insert(GoodsCategory record);

    int insertSelective(GoodsCategory record);

    GoodsCategory selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(GoodsCategory record);

    int updateByPrimaryKey(GoodsCategory record);





    //manual
    List<GoodsCategory> selectByParentId(String parentId);
}