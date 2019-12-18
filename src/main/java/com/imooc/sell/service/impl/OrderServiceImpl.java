package com.imooc.sell.service.impl;

import com.imooc.sell.dataobject.OrderDetail;
import com.imooc.sell.dataobject.OrderMaster;
import com.imooc.sell.dto.OrderDTO;
import com.imooc.sell.enums.OrderStatusEnum;
import com.imooc.sell.exception.OrderException;
import com.imooc.sell.repository.OrderDetailRepository;
import com.imooc.sell.repository.OrderMasterRepository;
import com.imooc.sell.repository.ProductInfoRepository;
import com.imooc.sell.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
@Slf4j
public class OrderServiceImpl implements OrderService {

    @Autowired
    private ProductInfoRepository productInfoRepository;

    @Autowired
    private OrderDetailRepository orderDetailRepository;

    @Autowired
    private OrderMasterRepository orderMasterRepository;


    @Override
    public OrderDTO create(OrderDTO orderDTO) {

        //查询商品（数量，价格）
        BigDecimal count = new BigDecimal("ZERO");
        List<OrderDetail> orderDetailList = orderDTO.getOrderDetailList();
        for (OrderDetail orderDetail : orderDetailList) {
            Integer productQuantity = orderDetail.getProductQuantity();
            BigDecimal productPrice = orderDetail.getProductPrice();
            count = new BigDecimal(productQuantity).multiply(productPrice).add(count);
        }
        System.out.println(count);
        if (count.equals(orderDTO.getOrderAmount())){
            log.info("订单金额=【{}元】",count);
        }else {
            throw new OrderException(400);
        }


        //计算总价


        //写入订单数据库
        for (OrderDetail orderDetail : orderDetailList) {
            Integer productQuantity = orderDetail.getProductQuantity();
            OrderDetail byDetailId = orderDetailRepository.findByDetailId(orderDetail.getDetailId());
            Integer  count1 = byDetailId.getProductQuantity() - orderDetail.getProductQuantity() ;
            if (count1 > 0){
                byDetailId.setProductQuantity(count1);
                orderDetailRepository.save(orderDetail);
            }
        }

        OrderMaster orderMaster = new OrderMaster();
        BeanUtils.copyProperties(orderDTO,orderMaster);
        orderMasterRepository.save(orderMaster);

        //扣减库存
        orderDTO.setOrderStatus(OrderStatusEnum.NEW.getCode());


        return null;
    }



    @Override
    public OrderDTO findOne(String orderId) {
        return null;
    }

    @Override
    public Page<OrderDTO> findList(String buyerOpenid, Pageable pageable) {
        return null;
    }

    @Override
    public OrderDTO cancel(OrderDTO orderDTO) {
        return null;
    }

    @Override
    public OrderDTO finish(OrderDTO orderDTO) {
        return null;
    }

    @Override
    public OrderDTO paid(OrderDTO orderDTO) {
        return null;
    }

    @Override
    public Page<OrderDTO> findList(Pageable pageable) {
        return null;
    }
}
