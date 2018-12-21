package com.aojing.redstore.goods.dao;

import com.aojing.redstore.goods.pojo.GoodsComentInfo;
import com.aojing.redstore.goods.pojo.GoodsExamineInfo;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface GoodsExamineInfoMapper   {
    int deleteByPrimaryKey(String id);

    int insert(GoodsExamineInfo record);

    int insertSelective(GoodsExamineInfo record);

    GoodsExamineInfo selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(GoodsExamineInfo record);

    int updateByPrimaryKey(GoodsExamineInfo record);
}