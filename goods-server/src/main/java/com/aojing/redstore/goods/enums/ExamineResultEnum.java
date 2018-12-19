package com.aojing.redstore.goods.enums;

import lombok.Getter;

/**
 * @author gexiao
 * @date 2018/12/10 10:25
 */
@Getter
public enum ExamineResultEnum {

    FIRST_EXAMINE(1, "初审"),

    SECOND_EXAMINE(2, "复审"),
    REFUSE(3, "审核不通过"),







    ;

    private Integer code;

    private String message;

    ExamineResultEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
