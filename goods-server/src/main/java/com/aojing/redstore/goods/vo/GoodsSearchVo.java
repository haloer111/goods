package com.aojing.redstore.goods.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * @author gexiao
 * @date 2018/12/14 15:46
 */
@Data
public class GoodsSearchVo implements Serializable {

    private String goodsId;
    private String goodsName;
    private String storeName;
    private String absoluteImg;

    public GoodsSearchVo() {
    }

    public GoodsSearchVo(String goodsId, String goodsName, String storeName, String absoluteImg) {
        this.goodsId = goodsId;
        this.goodsName = goodsName;
        this.storeName = storeName;
        this.absoluteImg = absoluteImg;
    }

    public GoodsSearchVo(String goodsId, String goodsName, String storeName) {
        this.goodsId = goodsId;
        this.goodsName = goodsName;
        this.storeName = storeName;
    }
}
