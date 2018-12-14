package com.aojing.redstore.goods.util;

import com.aojing.redstore.goods.pojo.GoodsCategory;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import lombok.extern.slf4j.Slf4j;

/**
 * Created by 廖师兄
 * 2017-07-04 01:30
 */
@Slf4j
public class JsonUtil {

    public static String toJson(Object object) {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.setPrettyPrinting();
        Gson gson = gsonBuilder.create();
        return gson.toJson(object);
    }

    public static Object JsonForm(String json, Object a) {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.setPrettyPrinting();
        Gson gson = gsonBuilder.create();

        Object o = null;
        try {
            o = gson.fromJson(json, new TypeToken<Object>() {
            }.getType());
        } catch (JsonSyntaxException e) {
            log.error("json转换实体类错误", e.getMessage());
        }
        return o;
    }
}
