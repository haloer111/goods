package com.aojing.redstore.goods.ctrl;

import com.aojing.redstore.goods.common.Result;
import com.aojing.redstore.goods.pojo.GoodsCategory;
import com.aojing.redstore.goods.service.GoodsCategoryService;
import com.aojing.redstore.goods.vo.GoodsCategoryVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author gexiao
 * @date 2018/12/11 17:45
 */
@RestController
@RequestMapping("/category")
public class GoodsCategoryCtrl {

    @Autowired
    private GoodsCategoryService categoryService;

    @PostMapping("/queryCategoryList")
    public Result<List<GoodsCategoryVo>> queryCategoryList(@RequestParam(defaultValue = "0") String parentId) {
        return categoryService.selectCategoryListByParentId(parentId);
    }

    @PostMapping("/test")
    public String  test( String parentId) {
        return parentId;
    }
}
