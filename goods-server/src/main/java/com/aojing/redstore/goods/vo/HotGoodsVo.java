package com.aojing.redstore.goods.vo;


import lombok.Data;

import java.math.BigDecimal;

/**
 * @author gexiao
 * @date 2018/12/24 15:29
 */
@Data
public class HotGoodsVo {

    /**
     * 商品id
     */
    private String id;

    /**
     * 商品名字
     */
    private String name;

    /**
     * 价格
     */
    private BigDecimal price;

    /**
     * 使用说明
     */
    private String instruction;

    /**
     * 位置
     */
    private String site;
}
