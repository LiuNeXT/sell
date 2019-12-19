package com.imooc.sell.exception;

import com.imooc.sell.enums.ResultEnum;

public class OrderException extends RuntimeException {


    private Integer code;



    public OrderException(ResultEnum resultEnum) {
        super(resultEnum.getMsg());
        this.code=resultEnum.getCode();
    }

    public OrderException(Integer code) {
        this.code = code;
    }

    public OrderException(String message, Integer code) {
        super(message);
        this.code = code;
    }
}
