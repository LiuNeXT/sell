package com.imooc.sell.service.impl;
import com.imooc.sell.dataobject.OrderDetail;
import com.imooc.sell.repository.OrderDetailRepository;
import com.imooc.sell.repository.OrderMasterRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;


@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class OrderDetailServiceImplTest {

    @Autowired
    private OrderMasterRepository orderMasterService;

    @Autowired
    private OrderDetailRepository orderDetailService;

    @Test
    public void findByDetailId() {
        OrderDetail byDetailId = orderDetailService.findByDetailId("20191217001");
        log.info(byDetailId.toString());
    }

    @Test
    public  void  save(){
        OrderDetail orderDetail = new OrderDetail();
        orderDetail.setDetailId("20191217001");
        orderDetail.setOrderId("0001");
        orderDetail.setProductId("132");
        orderDetail.setProductName("奶茶三兄弟");
        orderDetail.setProductPrice(new BigDecimal("3.2"));
        orderDetail.setProductQuantity(1);
        orderDetail.setProductIcon("xxx.jpg");
        orderDetailService.save(orderDetail);



    }
}