package com.aojing.redstore.goods.service.impl;

import com.aojing.redstore.goods.GoodsServerApplicationTests;
import com.aojing.redstore.goods.common.DecreaseStockInput;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.Assert.*;

/**
 * @author gexiao
 * @date 2018/12/19 11:35
 */
public class GoodsInfoServiceImplTest extends GoodsServerApplicationTests {

    @Autowired
    private GoodsInfoServiceImpl goodsInfoService;

    @Test
    public void decreaseStock() {
        DecreaseStockInput decreaseStockInput = new DecreaseStockInput();
        decreaseStockInput.setGoodsId("56324235656465d4df2312");
        decreaseStockInput.setGoodsCount(90);

       // goodsInfoService.decreaseStock(decreaseStockInput);
    }
}