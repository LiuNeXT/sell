package com.imooc.sell.service.impl;

import com.imooc.sell.dataobject.OrderMaster;
import com.imooc.sell.enums.OrderStatusEnum;
import com.imooc.sell.enums.PayStatusEnum;
import com.imooc.sell.repository.OrderMasterRepository;
import com.imooc.utils.JsonUtil;
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
public class OrderMasterServiceImplTest {

    @Autowired
    private OrderMasterRepository orderMasterRepository;

    @Test
    public void findByBuyerOpenid() {

    }

    @Test
    public  void save(){
        OrderMaster orderMaster = new OrderMaster();
        orderMaster.setOrderId("001");
        orderMaster.setBuyerName("alex");
        orderMaster.setBuyerPhone("15918842828");
        orderMaster.setBuyerAddress("增城");
        orderMaster.setBuyerOpenid("131513512");
        orderMaster.setOrderAmount(new BigDecimal(5));
        orderMaster.setOrderStatus(OrderStatusEnum.NEW.getCode());
        orderMaster.setPayStatus(PayStatusEnum.SUCCESS.getCode());
        //log.info(JSON.toJSONString(orderMaster));
        log.info(JsonUtil.toJson(orderMaster));
        orderMasterRepository.save(orderMaster);
    }
}