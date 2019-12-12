package com.imooc.sell.service.impl;
import com.imooc.sell.dataobject.ProductInfo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;


@RunWith(SpringRunner.class)
@SpringBootTest

public class ProductInfoServiceImplTest {

    @Autowired
    private ProductInfoServiceImpl productInfoService;

    @Test
    public void addProductInfo() {
        ProductInfo productInfo = new ProductInfo();
        productInfo.setProductId("123");
        productInfo.setProductName("liu");
        productInfo.setProductPrice(new BigDecimal("0"));
        productInfo.setProductStock(100);
        productInfo.setProductDescription("da");
        productInfo.setProductIcon("sads");
        productInfo.setProductStatus(0);
        productInfo.setCategoryType("3");
        productInfoService.save(productInfo);
    }

    @Test
    public void findByProductStatus() {
    }
}