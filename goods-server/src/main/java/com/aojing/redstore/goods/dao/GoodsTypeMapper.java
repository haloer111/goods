package com.aojing.redstore.goods.dao;

import com.aojing.redstore.goods.pojo.GoodsType;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface GoodsTypeMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(GoodsType record);

    int insertSelective(GoodsType record);

    GoodsType selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(GoodsType record);

    int updateByPrimaryKey(GoodsType record);



    //manual
    List<String> queryGoodsIdByTypeId(String categoryId);

}