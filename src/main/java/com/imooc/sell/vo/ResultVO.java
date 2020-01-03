package com.imooc.sell.vo;

import lombok.Data;

import java.io.Serializable;

@Data
public class ResultVO<T> implements Serializable {

    private  Integer code;

    private String msg;

    private  T data;
}
