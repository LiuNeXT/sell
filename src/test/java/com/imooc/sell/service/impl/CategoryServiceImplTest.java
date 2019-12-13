package com.imooc.sell.service.impl;

import com.imooc.sell.dataobject.ProductCategory;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;


@RunWith(SpringRunner.class)
@SpringBootTest
public class CategoryServiceImplTest {


    @Autowired CategoryServiceImpl categoryService;

    @Test
    public void findOne() {
        ProductCategory  productCategory = categoryService.findOne(1);
        Assert.assertEquals(new Integer(3),productCategory.getCategoryType());
    }

    @Test
    public void findAll() {
        List<ProductCategory> productCategoryList = categoryService.findAll();
        Assert.assertNotEquals(0,productCategoryList.size());

    }

    @Test
    public void findByCategoryTypein() {
        List<ProductCategory> productCategoryList = categoryService.findByCategoryTypein(Arrays.asList(1, 2, 3));
        for (ProductCategory p: productCategoryList) {
            System.out.println(p.toString());
        }
    }

    @Test
    public void save() {
        ProductCategory p = new ProductCategory("碎碎面",4);
        categoryService.save(p);
    }
}