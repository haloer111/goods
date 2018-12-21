package com.aojing.redstore.goods.service.impl;

import com.aojing.redstore.goods.GoodsServerApplicationTests;
import com.aojing.redstore.goods.vo.CategoryVo;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.junit.Assert.*;

/**
 * @author gexiao
 * @date 2018/12/21 11:58
 */
public class GoodsTypeServiceImplTest extends GoodsServerApplicationTests {

    @Autowired
   private GoodsTypeServiceImpl goodsTypeService;
    @Test
    public void queryCategoryVo() {
        List<CategoryVo> categoryVoList = goodsTypeService.queryCategoryVo("1");
        Assert.assertNotNull(categoryVoList);
    }
}