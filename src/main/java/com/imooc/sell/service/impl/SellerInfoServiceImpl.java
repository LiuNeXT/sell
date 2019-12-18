package com.imooc.sell.service.impl;

import com.imooc.sell.repository.SellerInfoRepository;
import com.imooc.sell.service.SellerInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SellerInfoServiceImpl implements SellerInfoService {


    @Autowired
    private SellerInfoRepository sellerInfoRepository;



}
