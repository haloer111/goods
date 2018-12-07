package com.aojing.redstore.goods.service;

import com.aojing.redstore.goods.common.Result;
import com.aojing.redstore.goods.pojo.GoodsTypeInfo;

import java.util.List;

/**
 * @author gexiao
 * @date 2018/12/4 15:35
 */
public interface GoodsTypeInfoService {

    Result<List<GoodsTypeInfo>> queryGoodsTypeInfoList();
}
