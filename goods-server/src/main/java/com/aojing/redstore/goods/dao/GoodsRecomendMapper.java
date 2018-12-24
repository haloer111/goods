package com.aojing.redstore.goods.dao;

import com.aojing.redstore.goods.pojo.GoodsExamineInfo;
import com.aojing.redstore.goods.pojo.GoodsRecomend;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

public interface GoodsRecomendMapper  extends BaseMapper<GoodsRecomend> {
    int deleteByPrimaryKey(String id);

    int insert(GoodsRecomend record);

    int insertSelective(GoodsRecomend record);

    GoodsRecomend selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(GoodsRecomend record);

    int updateByPrimaryKey(GoodsRecomend record);
}