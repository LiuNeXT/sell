package com.imooc.sell.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class ProductVO {



    @JsonProperty("name")
    private String categoryName;


    @JsonProperty("type")
    private Integer categoryType;


    @JsonProperty("food")
    private List<ProductInfoVO> productInfoVOList;

}
