package com.imooc.sell.service.impl;

import com.imooc.sell.dataobject.ProductInfo;
import com.imooc.sell.enums.ProductStatusEnum;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.List;


@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductInfoServiceImplTest {

    @Autowired
    private ProductInfoServiceImpl productInfoService;

    @Test
    public void addProductInfo() {
            ProductInfo productInfo = new ProductInfo();
            productInfo.setProductId("12334");
            productInfo.setProductName("奶茶三兄弟");
            productInfo.setProductPrice(new BigDecimal("3.2"));
            productInfo.setProductStock(100);
            productInfo.setProductDescription("奶茶");
            productInfo.setProductIcon("http://xxx.com/jk.jpg");
            productInfo.setProductStatus(ProductStatusEnum.UP.getCode());
            productInfo.setCategoryType("4");
            productInfoService.save(productInfo);


    }

    @Test
    public void findByProductStatus() {
        List<ProductInfo> productStatusList = productInfoService.findByProductStatus();
        System.out.println(productStatusList.size());

    }

    @Test
    public  void  findByPageProduct(){
        PageRequest pageRequest = PageRequest.of(1, 10);
        Page<ProductInfo> productInfoPage = productInfoService.findAll(pageRequest);
        System.out.println(productInfoPage.getTotalElements());
        System.out.println(productInfoPage.getTotalPages());
    }
}