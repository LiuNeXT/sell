package com.imooc.sell.exception;

public class OrderException extends RuntimeException {


    private Integer code;


    public OrderException(Integer code) {
        this.code = code;
    }

    public OrderException(String message, Integer code) {
        super(message);
        this.code = code;
    }
}
