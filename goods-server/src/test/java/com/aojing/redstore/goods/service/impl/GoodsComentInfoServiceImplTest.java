package com.aojing.redstore.goods.service.impl;

import com.aojing.redstore.goods.GoodsServerApplicationTests;
import com.aojing.redstore.goods.common.Result;
import com.aojing.redstore.goods.dto.QueryCountDto;
import com.netflix.discovery.converters.Auto;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;

/**
 * @author gexiao
 * @date 2018/12/21 15:23
 */
public class GoodsComentInfoServiceImplTest extends GoodsServerApplicationTests {

    @Autowired
    private
    GoodsComentInfoServiceImpl goodsComentInfoService;
    @Test
    public void commentCount() {
        QueryCountDto queryCountDto = new QueryCountDto();
        queryCountDto.setSellerIdList(Arrays.asList("123","1234"));

        Result<List<Map<String, Object>>> listResult = goodsComentInfoService.commentCount(queryCountDto);
        Assert.assertNotNull(listResult);

    }
}