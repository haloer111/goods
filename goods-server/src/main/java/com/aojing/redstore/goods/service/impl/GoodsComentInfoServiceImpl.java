package com.aojing.redstore.goods.service.impl;

import com.aojing.redstore.goods.common.Result;
import com.aojing.redstore.goods.dao.GoodsComentInfoMapper;
import com.aojing.redstore.goods.pojo.GoodsComentInfo;
import com.aojing.redstore.goods.service.GoodsComentInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author gexiao
 * @date 2018/12/6 10:41
 */
@Service
public class GoodsComentInfoServiceImpl implements GoodsComentInfoService {

    @Autowired
    private GoodsComentInfoMapper goodsComentInfoMapper;

    //    comment[评论商品]
    public Result comment(GoodsComentInfo comentInfo) {
        if (comentInfo == null) {
            return Result.createByErrorMessage("评论商品,参数不正确");
        }
        int result = goodsComentInfoMapper.insertSelective(comentInfo);
        if (result > 0) {
            return Result.createBySuccessMessage("评论商品,成功");

        }
        return Result.createByErrorMessage("评论商品,失败");

    }


    //    discomment[退回评论]
    public Result discomment(Integer id, String commenterId) {
        if (id == null) {
            return Result.createByErrorMessage("退回评论,参数不正确");
        }

        int result = goodsComentInfoMapper.deleteByIdAndUserId(id,commenterId);
        if (result > 0) {
            return Result.createBySuccessMessage("评论商品,成功");
        }
        return Result.createByErrorMessage("评论商品,失败");
    }
}
