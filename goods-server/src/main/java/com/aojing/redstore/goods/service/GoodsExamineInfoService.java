package com.aojing.redstore.goods.service;

import com.aojing.redstore.goods.common.Result;
import com.aojing.redstore.goods.pojo.GoodsExamineInfo;

/**
 * @author gexiao
 * @date 2018/12/6 11:12
 */
public interface GoodsExamineInfoService {
    //    Examine[审核商品信息]
    Result examine(GoodsExamineInfo examineInfo);
}