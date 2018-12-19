package com.aojing.redstore.goods.service.impl;

import com.aojing.redstore.goods.common.Result;
import com.aojing.redstore.goods.dao.GoodsExamineInfoMapper;
import com.aojing.redstore.goods.pojo.GoodsExamineInfo;
import com.aojing.redstore.goods.service.GoodsExamineInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author gexiao
 * @date 2018/12/6 11:12
 */
@Service
public class GoodsExamineInfoServiceImpl implements GoodsExamineInfoService {

    @Autowired
    private GoodsExamineInfoMapper examineInfoMapper;

    //    Examine[审核商品信息]
    public Result examine(GoodsExamineInfo examineInfo) {
        if (examineInfo == null) {
            return Result.createByErrorMessage("审核商品信息,参数不正确");
        }
        int result = examineInfoMapper.insertSelective(examineInfo);

        if (result > 0) {
            return Result.createBySuccess("审核商品信息成功");
        }
        return Result.createByErrorMessage("审核商品信息失败");

    }
}
