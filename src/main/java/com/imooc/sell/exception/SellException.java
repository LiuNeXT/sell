package com.imooc.sell.exception;

import com.imooc.sell.enums.ResultEnum;

public class SellException extends RuntimeException {

    private Integer code;


    public SellException(ResultEnum  resultEnum) {
        super(resultEnum.getMsg());
        this.code = code;
    }


    public SellException(Integer code) {
        this.code = code;
    }

    public SellException(String message, Integer code) {
        super(message);
        this.code = code;
    }
}
