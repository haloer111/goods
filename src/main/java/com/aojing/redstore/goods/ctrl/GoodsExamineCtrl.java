package com.aojing.redstore.goods.ctrl;

import com.aojing.redstore.goods.common.Result;
import com.aojing.redstore.goods.dto.ExamineDto;
import com.aojing.redstore.goods.dto.GoodsDto;
import com.aojing.redstore.goods.pojo.GoodsExamineInfo;
import com.aojing.redstore.goods.service.GoodsExamineInfoService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author gexiao
 * @date 2018/12/10 10:22
 */
@RestController
public class GoodsExamineCtrl {

    @Autowired
    GoodsExamineInfoService goodsExamineInfoService;

    @PostMapping("/add")
    public Result addGoods(ExamineDto examineDto) {
        GoodsExamineInfo examineInfo = new GoodsExamineInfo();
        BeanUtils.copyProperties(examineDto,examineInfo);
        return goodsExamineInfoService.examine(examineInfo);
    }

}
