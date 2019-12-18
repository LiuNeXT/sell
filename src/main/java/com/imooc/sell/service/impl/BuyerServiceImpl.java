package com.imooc.sell.service.impl;

import com.imooc.sell.dto.OrderDTO;
import com.imooc.sell.repository.OrderDetailRepository;
import com.imooc.sell.repository.OrderMasterRepository;
import com.imooc.sell.service.BuyerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BuyerServiceImpl implements BuyerService {

    @Autowired
    private OrderMasterRepository orderMasterRepository;

    @Autowired
    private OrderDetailRepository orderDetailRepository;

//    @Override
//    public OrderDTO findOrderOne(String openid, String orderId) {
//
//        PageRequest pageRequest = PageRequest.of(0,10);
//        Page<OrderMaster> orderMasterPage = orderMasterRepository.findByBuyerOpenid(openid,pageRequest);
//        List<OrderMaster> orderMasterList = orderMasterPage.getContent();
//
//        for (OrderMaster orderMaster : orderMasterList) {
//            OrderDTO orderDTO = new OrderDTO();
//            BeanUtils.copyProperties(orderMaster,orderDTO);
//            orderDTO.setOrderId("");
//            orderDTO.setBuyerName("");
//            orderDTO.setBuyerPhone("");
//            orderDTO.setBuyerAddress("");
//            orderDTO.setBuyerOpenid("");
//            orderDTO.setOrderAmount(new BigDecimal("0"));
//            orderDTO.setOrderStatus(0);
//            orderDTO.setPayStatus(0);
//            orderDTO.setCreateTime(new Date());
//            orderDTO.setUpdateTime(new Date());
//            orderDTO.setOrderDetailList(Lists.newArrayList());
//        }
//
//
//
//
//        return null;
//    }

    @Override
    public OrderDTO findOrderOne(String openid, String orderId) {
        return null;
    }

    @Override
    public OrderDTO cancelOrder(String openid, String orderId) {
        return null;
    }
}
