package com.imooc.sell.repository;

import com.imooc.sell.dataobject.ProductCategory;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;


@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class ProductCategoryDaoTest {

    @Autowired ProductCategoryRepository repository;


    @Test
    public  void findAllProductCategory(){
        List<ProductCategory> all = repository.findAll();
        for ( ProductCategory p: all) {
            System.out.println(p.toString());
        }
    }

    @Test  //新增
    public void addProductCategory(){
        ProductCategory p = new ProductCategory("大海",4);
        repository.save(p);
    }

    @Test  //修改
    public void modifyProductCategory(){
        ProductCategory p = repository.findByCategoryId(3);
        p.setCategoryType(1);
        repository.save(p);
    }

    @Test //findByCategoryTypeIn(In要小心留意)
    public void  findByCategoryType(){
        List<ProductCategory> productCategoryList = repository.findByCategoryTypeIn(Arrays.asList(1, 2, 3));
        for (ProductCategory p: productCategoryList) {
            System.out.println(p.toString());
        }
    }
}