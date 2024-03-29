package com.imooc.sell.repository;

import com.imooc.sell.dataobject.ProductCategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface ProductCategoryRepository extends JpaRepository<ProductCategory,Integer> {


    ProductCategory findByCategoryId(Integer categoryId);

    List<ProductCategory> findByCategoryTypeIn(List<Integer> categoryTypeIdList);
}
