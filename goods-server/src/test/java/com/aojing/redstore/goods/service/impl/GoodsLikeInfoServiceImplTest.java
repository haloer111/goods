package com.aojing.redstore.goods.service.impl;

import com.aojing.redstore.goods.GoodsServerApplicationTests;
import com.aojing.redstore.goods.common.Result;
import com.aojing.redstore.goods.dto.QueryCountDto;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * @author gexiao
 * @date 2018/12/21 14:35
 */
public class GoodsLikeInfoServiceImplTest extends GoodsServerApplicationTests {

    @Autowired
    private GoodsLikeInfoServiceImpl goodsLikeInfoService;

    @Test
    public void queryLikeInfoCount() {
        QueryCountDto queryCountDto = new QueryCountDto();
        queryCountDto.setGoodsIdList(Arrays.asList("edc3bf9b504f4c0f9d9f5d33e9d7a88915444273865685goods","edc3bf9b504f4c0f9d9f5d33e9d7a88915444273865685goods"));
        Result<List<Map<String, Object>>> listResult = goodsLikeInfoService.queryLikeInfoCount(queryCountDto);
        Assert.assertNotNull(listResult);
    }
}