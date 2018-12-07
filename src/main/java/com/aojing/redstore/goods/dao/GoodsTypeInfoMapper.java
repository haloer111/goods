package com.aojing.redstore.goods.dao;

import com.aojing.redstore.goods.pojo.GoodsTypeInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
@Mapper
public interface GoodsTypeInfoMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(GoodsTypeInfo record);

    int insertSelective(GoodsTypeInfo record);

    GoodsTypeInfo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(GoodsTypeInfo record);

    int updateByPrimaryKey(GoodsTypeInfo record);

    List<GoodsTypeInfo> queryTypeList();

    int deleteByTypeId(@Param("goodsId")Integer goodsId, @Param("typeId")Integer typeId);
}