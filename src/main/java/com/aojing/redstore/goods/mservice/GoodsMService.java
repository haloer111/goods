package com.aojing.redstore.goods.mservice;

import com.aojing.redstore.goods.common.Result;
import com.aojing.redstore.goods.dto.GoodsDto;

/**
 * @author gexiao
 * @date 2018/12/09 下午 04:51
 */
public interface GoodsMService {

    Result addGoods(GoodsDto goodsDto);
}
