package com.imooc.sell.service;


import com.imooc.sell.dataobject.ProductCategory;

import java.util.List;

public interface CategorySerivce {

    ProductCategory findOne(Integer categoryId);

    List<ProductCategory> findAll();

    List<ProductCategory> findByCategoryTypein(List<Integer> categoryTypeList);

    ProductCategory save(ProductCategory productCategory);

}
