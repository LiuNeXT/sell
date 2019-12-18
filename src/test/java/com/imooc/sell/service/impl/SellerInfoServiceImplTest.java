package com.imooc.sell.service.impl;

import com.imooc.sell.dataobject.SellerInfo;
import com.imooc.sell.repository.SellerInfoRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


@RunWith(SpringRunner.class)
@SpringBootTest
public class SellerInfoServiceImplTest {


    @Autowired
    private  SellerInfoRepository sellerInfoRepository;

    @Test
    public void  save(){
        SellerInfo sellerInfo = new SellerInfo();
        sellerInfo.setId("3653555");
        sellerInfo.setUserName("qealex");
        sellerInfo.setPassWord("password");
        sellerInfo.setOpenId("sdasdasdasd");
        sellerInfoRepository.save(sellerInfo);

    }
}