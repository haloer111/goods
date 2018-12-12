package com.aojing.redstore.goods.vo;

import lombok.Data;

import java.util.Map;

/**
 * @author gexiao
 * @date 2018/12/11 17:57
 */
@Data
public class GoodsInfoVo {
    String goodsId;
    String goodsName;
    int likeCount;
    String content;
    String userId;
    String userName;
    String icon;
    String bgImg;

    public GoodsInfoVo Map2GoodsInfo(Map map){
        this.setGoodsId((String) map.get("goodsId"));
        this.setBgImg((String) map.get("absolutePath"));

        return this;

    }

}
