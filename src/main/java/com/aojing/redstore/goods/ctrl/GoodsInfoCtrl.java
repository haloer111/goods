package com.aojing.redstore.goods.ctrl;

import com.aojing.redstore.goods.common.Result;
import com.aojing.redstore.goods.pojo.GoodsInfo;
import com.aojing.redstore.goods.service.GoodsTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * @author gexiao
 * @date 2018/12/4 18:09
 */
@RestController
public class GoodsInfoCtrl {

    @Autowired
    private GoodsTypeService goodsTypeService;

    public Result<List<GoodsInfo>> add(){

        List<GoodsInfo> list = new ArrayList<>();

        return Result.createBySuccess(list);
    }
}
