package com.aojing.redstore.goods.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author gexiao
 * @date 2018/12/7 14:42
 */
@Getter
@AllArgsConstructor
public enum PromotionEnum {

    ON_PROMOTION(1,"促销"),
    UN_PROMOTION(0,"未促销"),



    ;

    private int code;
    private String value;


}
