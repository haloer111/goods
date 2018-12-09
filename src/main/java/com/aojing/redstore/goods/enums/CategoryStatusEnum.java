package com.aojing.redstore.goods.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author gexiao
 * @date 2018/12/4 13:48
 */
@Getter
@AllArgsConstructor
public enum CategoryStatusEnum {
    ENABLE(1,"正常"),
    DISABLE(2,"废弃"),


    ;

    private int code;
    private String value;




}
