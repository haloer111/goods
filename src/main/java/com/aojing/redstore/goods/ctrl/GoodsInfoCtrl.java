package com.aojing.redstore.goods.ctrl;

import com.aojing.redstore.goods.common.Result;
import com.aojing.redstore.goods.service.GoodsInfoService;
import com.aojing.redstore.goods.service.GoodsTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author gexiao
 * @date 2018/12/4 18:09
 */
@RestController
public class GoodsInfoCtrl {

    @Autowired
    private GoodsTypeService goodsTypeService;
    @Autowired
    private GoodsInfoService goodsInfoService;


    @GetMapping("/upload")
    public Result<String> delFile(){

        return goodsInfoService.delMovie("1.png","1");
        //return Result.createBySuccess(list);
    }


}
