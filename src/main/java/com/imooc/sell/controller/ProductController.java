package com.imooc.sell.controller;


import com.imooc.sell.dataobject.ProductCategory;
import com.imooc.sell.dataobject.ProductInfo;
import com.imooc.sell.service.impl.CategoryServiceImpl;
import com.imooc.sell.service.impl.ProductInfoServiceImpl;
import com.imooc.sell.vo.ProductInfoVO;
import com.imooc.sell.vo.ProductVO;
import com.imooc.sell.vo.ResultVO;
import com.imooc.utils.ResultVOUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/product")
@Slf4j
public class ProductController {

    @Autowired
    private ProductInfoServiceImpl productService;

    @Autowired
    private CategoryServiceImpl categoryService;

    @GetMapping("/list")
    public ResultVO productList() throws Exception{
        List<ProductInfoVO> productInfoVOList = new ArrayList<>();
        List<ProductInfo> productInfoList = productService.findAll();
        for (ProductInfo productInfo : productInfoList){
            ProductInfoVO productInfoVO = new ProductInfoVO();
            BeanUtils.copyProperties(productInfo,productInfoVO);
            productInfoVOList.add(productInfoVO);
        }
        ProductCategory productCategory = categoryService.findOne(1);
        ProductVO productVO = new ProductVO();
        productVO.setCategoryName(productCategory.getCategoryName());
        productVO.setCategoryType(productCategory.getCategoryType());
        productVO.setProductInfoVOList(productInfoVOList);
        return  ResultVOUtil.success(productVO);
    }
}
