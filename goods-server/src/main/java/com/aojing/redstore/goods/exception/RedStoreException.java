package com.aojing.redstore.goods.exception;

import com.aojing.redstore.goods.enums.ExceptionEnum;
import lombok.Getter;


@Getter
public class RedStoreException extends RuntimeException{

    private Integer code;

    public RedStoreException(ExceptionEnum resultEnum) {
        super(resultEnum.getMessage());

        this.code = resultEnum.getCode();
    }

    public RedStoreException(Integer code, String message) {
        super(message);
        this.code = code;
    }
}
