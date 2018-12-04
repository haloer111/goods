package com.aojing.redstore.goods.service.impl;
import java.util.Date;
import java.util.Set;

import com.aojing.redstore.goods.common.ServerResponse;
import com.aojing.redstore.goods.pojo.GoodsType;
import com.aojing.redstore.goods.service.GoodsTypeService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author gexiao
 * @date 2018/12/4 10:25
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class GoodsTypeServiceImplTest  {

    @Autowired
    private GoodsTypeService goodsTypeService;

    @Test
    public void addType() {
        GoodsType goodsType= new GoodsType();
        goodsType.setName("海鲜");
        goodsType.setStatus(1);
        goodsType.setSellerId(0);
        goodsType.setSortOrder(0);
        goodsType.setCreateTime(new Date());
        goodsType.setUpdateTime(new Date());

        ServerResponse serverResponse = goodsTypeService.addType(goodsType);
        Assert.assertEquals(true,serverResponse.isSuccess());
    }

    @Test
    public void updateType() {
        GoodsType goodsType= new GoodsType();
        goodsType.setName("饮料");
        goodsType.setId(100032);
        goodsType.setUpdateTime(new Date());
        ServerResponse serverResponse = goodsTypeService.updateType(goodsType);
        Assert.assertEquals(true,serverResponse.isSuccess());

    }

    @Test
    public void delType() {

        ServerResponse serverResponse = goodsTypeService.delType(100032);
        Assert.assertEquals(true,serverResponse.isSuccess());
    }

    @Test
    public void queryAllType() {
        ServerResponse<Set<GoodsType>> serverResponse = goodsTypeService.queryAllType();
        Assert.assertEquals(true,serverResponse.isSuccess());

    }

    @Test
    public void addGoodsInType() {
        //1,100033
        ServerResponse serverResponse = goodsTypeService.AddGoodsInType(1, 100033);
        Assert.assertEquals(true,serverResponse.isSuccess());

    }

    @Test
    @Transactional
    public void removeGoodsFromType() {
        ServerResponse serverResponse = goodsTypeService.RemoveGoodsFromType(1, 100033);
        Assert.assertEquals(true,serverResponse.isSuccess());
    }
}