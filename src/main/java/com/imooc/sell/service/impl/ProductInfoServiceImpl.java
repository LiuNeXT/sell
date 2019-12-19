package com.imooc.sell.service.impl;

import com.imooc.sell.dataobject.ProductInfo;
import com.imooc.sell.dto.CartDTO;
import com.imooc.sell.enums.ProductStatusEnum;
import com.imooc.sell.enums.ResultEnum;
import com.imooc.sell.exception.SellException;
import com.imooc.sell.repository.ProductInfoRepository;
import com.imooc.sell.service.ProductInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    @Override
    @Transactional
    public void increaseStock(List<CartDTO> cartDTOList) {
        for (CartDTO cartDTO : cartDTOList) {
            ProductInfo productInfo = productInfoService.findByProductId(cartDTO.getProductId());
            if (productInfo == null){
                throw  new SellException(ResultEnum.PRODUCT_STOCK_ERROR);
            }
            Integer count = productInfo.getProductStock() + cartDTO.getProductQuantity();

            productInfo.setProductStock(count);
            productInfoService.save(productInfo);
        }
    }

    @Override
    @Transactional
    public void decreaseStock(List<CartDTO> cartDTOList) {
        for (CartDTO cartDTO : cartDTOList) {
            ProductInfo productInfo = productInfoService.findByProductId(cartDTO.getProductId());
            if (productInfo == null){
                throw  new SellException(ResultEnum.PRODUCT_STOCK_ERROR);
            }
            Integer count = productInfo.getProductStock() - cartDTO.getProductQuantity();
            if (count < 0){
                throw new SellException((ResultEnum.PRODUCT_STOCK_ERROR));
            }
            productInfo.setProductStock(count);
            productInfoService.save(productInfo);
        }
    }

    @Override
    public ProductInfo onSale(String productId) {
        ProductInfo productInfo = productInfoService.findByProductId(productId);
        if (productInfo == null){
            throw  new SellException(ResultEnum.PRODUCT_NOT_EXIST);
        }
        productInfo.setProductStatus(ProductStatusEnum.UP.getCode());
        productInfoService.save(productInfo);
        return productInfo;
    }

    @Override
    public ProductInfo offSale(String productId) {
        ProductInfo productInfo = productInfoService.findByProductId(productId);
        if (productInfo == null){
            throw  new SellException(ResultEnum.PRODUCT_NOT_EXIST);
        }
        productInfo.setProductStatus(ProductStatusEnum.DOWN.getCode());
        productInfoService.save(productInfo);
        return productInfo;
    }


    /***
     * 默认finALL只找首页10个产品
     */
    public List<ProductInfo> findAll() {
        Page<ProductInfo> productInfoPage = productInfoService.findAll(PageRequest.of(0,10));
        return  productInfoPage.getContent();
    }


    public ProductInfo findOne(String productId){
        return productInfoService.findByProductId(productId);
    }


}
