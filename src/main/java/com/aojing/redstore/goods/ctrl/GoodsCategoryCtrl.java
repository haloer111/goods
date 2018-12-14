package com.aojing.redstore.goods.ctrl;

import com.aojing.redstore.goods.common.Result;
import com.aojing.redstore.goods.pojo.GoodsCategory;
import com.aojing.redstore.goods.service.GoodsCategoryService;
import com.aojing.redstore.goods.vo.GoodsCategoryVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.cache.interceptor.SimpleKeyGenerator;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import java.io.Serializable;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author gexiao
 * @date 2018/12/11 17:45
 */
@RestController
@RequestMapping("/category")
public class GoodsCategoryCtrl {

    @Autowired
    private GoodsCategoryService categoryService;
    @Autowired
    private RedisTemplate<String, Serializable> redisCacheTemplate;

    @GetMapping("/queryCategoryList")
   // @Cacheable(value = "category#${select.cache.timeout:1000}", key = "123")
    public Result<List<GoodsCategoryVo>> queryCategoryList(@RequestParam(defaultValue = "0") String parentId) {
        String key = GoodsCategoryCtrl.class.getSimpleName()+parentId;

        Result<List<GoodsCategoryVo>> list = categoryService.selectCategoryListByParentId(parentId);
        redisCacheTemplate.opsForValue().set(key,list,30,TimeUnit.MINUTES);
        //redisCacheTemplate.opsForList().remove()
        return list;
    }

}
