package com.imooc.sell.service.impl;

import com.imooc.sell.dataobject.ProductInfo;
import com.imooc.sell.enums.ProductStatusEnum;
import com.imooc.sell.repository.ProductInfoRepository;
import com.imooc.sell.service.ProductInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductInfoServiceImpl implements ProductInfoService {

    @Autowired
    private ProductInfoRepository productInfoService;

    @Override
    public List<ProductInfo> findByProductStatus() {
        return productInfoService.findByProductStatus(ProductStatusEnum.UP.getCode());
    }

    @Override
    public ProductInfo save(ProductInfo productInfo) {
        return productInfoService.save(productInfo);
    }




    @Override
    public Page<ProductInfo> findAll(Pageable pageable) {
        return productInfoService.findAll(pageable);
    }


    /***
     * 默认finALL只找首页10个产品
     */
    public List<ProductInfo> findAll() {
        Page<ProductInfo> productInfoPage = productInfoService.findAll(PageRequest.of(0,10));
        return  productInfoPage.getContent();
    }




}
