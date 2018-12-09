package com.aojing.redstore.goods.convert;

import com.aojing.redstore.goods.common.Result;
import com.aojing.redstore.goods.exception.RedStoreException;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

/**
 * @author gexiao
 * @date 2018/12/7 10:14
 */
@Slf4j
public class RestTemplate2ResultConvert {

    public static Result convert(String  dataStr) {
        Gson gson = new Gson();

        Result<String> result = null;
        try {
            result = gson.fromJson(dataStr,  new TypeToken<Result>(){}.getType()
            );
        } catch (Exception e) {
            log.error("【对象转换】错误, string={}", dataStr);
            throw new RedStoreException(1, "错误");
        }

        return result;
    }
}
