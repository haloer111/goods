package com.aojing.redstore.goods.dao;

import com.aojing.redstore.goods.pojo.GoodsComentInfo;
import com.aojing.redstore.goods.pojo.GoodsExamineInfo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

public interface GoodsExamineInfoMapper extends BaseMapper<GoodsExamineInfo> {
    int deleteByPrimaryKey(String id);

    int insert(GoodsExamineInfo record);

    int insertSelective(GoodsExamineInfo record);

    GoodsExamineInfo selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(GoodsExamineInfo record);

    int updateByPrimaryKey(GoodsExamineInfo record);
}