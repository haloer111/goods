package com.aojing.redstore.goods.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author gexiao
 * @date 2018/12/4 13:48
 */
@Getter
@AllArgsConstructor
public enum GoodsStatusEnum {
    ON_SALE(10,"在售"),
    SUSPEND_SALES(20,"下架"),
    SALD_OUT(30,"售空"),
    STOCK_OUT(40,"缺货"),
    HALT_SALE(50,"停售"),


    ;

    private int code;
    private String value;




}
