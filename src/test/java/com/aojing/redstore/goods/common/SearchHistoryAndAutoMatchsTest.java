package com.aojing.redstore.goods.common;
import java.util.Date;
import java.util.List;

import com.aojing.redstore.goods.GoodsApplicationTests;
import com.aojing.redstore.goods.pojo.GoodsCategory;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.StringRedisTemplate;

import static org.junit.Assert.*;

/**
 * @author gexiao
 * @date 2018/12/14 15:13
 */
public class SearchHistoryAndAutoMatchsTest extends GoodsApplicationTests {
    @Autowired
    StringRedisTemplate stringRedisTemplate;
    @Test
    public void updateList() {
        ListOperations<String, String> ops = stringRedisTemplate.opsForList();
        GoodsCategory goodsCategory = new GoodsCategory();
        goodsCategory.setId("999");
        goodsCategory.setParentId("541");
        goodsCategory.setName("张根硕");
        goodsCategory.setStatus(0);
        goodsCategory.setSellerId("");
        goodsCategory.setSortOrder(0);
        goodsCategory.setCreateTime(new Date());
        goodsCategory.setUpdateTime(new Date());
        Gson gson = new Gson();
        String toJson = gson.toJson(goodsCategory);

        ops.leftPush("155",toJson);
    }

    @Test
    public void getAutoMatchs() {
        ListOperations<String, String> ops = stringRedisTemplate.opsForList();
        List<String> range = ops.range("155", 0, -1);
        Gson gson = new Gson();
        for (String s : range) {
            GoodsCategory o = gson.fromJson(s, new TypeToken<GoodsCategory>() {
            }.getType());
            System.out.println("=========="+o.getName()+"ddd:"+o.getId());
        }
    }

    @Test
    public void getDefaultAutoMatchs() {
    }
}