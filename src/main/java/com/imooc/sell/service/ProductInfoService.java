package com.imooc.sell.service;

import com.imooc.sell.dataobject.ProductInfo;

import java.util.List;

public interface ProductInfoService {


    //查询所有在架商品
    List<ProductInfo> findByProductStatus();


    ProductInfo save(ProductInfo productInfo);


    //Page<ProductInfo> findAll(Pageable pageable);


    //增加库存


    //减少库存
}
