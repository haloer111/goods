package com.aojing.redstore.goods.dao;

import com.aojing.redstore.goods.pojo.GoodsExamineInfo;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface GoodsExamineInfoMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(GoodsExamineInfo record);

    int insertSelective(GoodsExamineInfo record);

    GoodsExamineInfo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(GoodsExamineInfo record);

    int updateByPrimaryKey(GoodsExamineInfo record);
}