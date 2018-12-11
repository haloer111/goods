package com.aojing.redstore.goods.service.impl;
import java.math.BigDecimal;

import com.aojing.redstore.goods.common.Result;

import java.util.Date;

import com.aojing.redstore.goods.GoodsApplicationTests;
import com.aojing.redstore.goods.dto.GoodsDto;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author gexiao
 * @date 2018/12/09 下午 04:57
 */
public class GoodsInfoServiceImplTest extends GoodsApplicationTests {

    @Autowired
    GoodsInfoServiceImpl service;
    @Test
    public void addOrUpdateNew() {
        GoodsDto goodsDto = new GoodsDto();
        goodsDto.setPrice(new BigDecimal("15"));
        goodsDto.setCostPrice(new BigDecimal("10"));
        goodsDto.setSalesPromotion(0);
        goodsDto.setSeller("葛潇");
        goodsDto.setSite("七星路");
        goodsDto.setBrand("nike");
        goodsDto.setName("气垫鞋");
        goodsDto.setSellNumber("17758671346");
        goodsDto.setSellStatus(0);
        goodsDto.setGoodsDesc("好看");
        goodsDto.setTips("好看");
        goodsDto.setUnit("元");
        goodsDto.setInstruction("");
        goodsDto.setStartTime(new Date());
        goodsDto.setEndTime(new Date());
        goodsDto.setValue("15");
        goodsDto.setPrecondition("");
        goodsDto.setDetail("好看详情");


        Result result = service.updateGoods(goodsDto);
        Assert.assertTrue(result.isSuccess());

    }
}