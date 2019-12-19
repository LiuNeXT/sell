package com.imooc.sell.service.impl;

import com.imooc.sell.converter.OrderMaster2OrderDTOConverter;
import com.imooc.sell.dataobject.OrderDetail;
import com.imooc.sell.dataobject.OrderMaster;
import com.imooc.sell.dataobject.ProductInfo;
import com.imooc.sell.dto.CartDTO;
import com.imooc.sell.dto.OrderDTO;
import com.imooc.sell.enums.OrderStatusEnum;
import com.imooc.sell.enums.PayStatusEnum;
import com.imooc.sell.enums.ResultEnum;
import com.imooc.sell.exception.OrderException;
import com.imooc.sell.exception.SellException;
import com.imooc.sell.repository.OrderDetailRepository;
import com.imooc.sell.repository.OrderMasterRepository;
import com.imooc.sell.service.OrderService;
import com.imooc.utils.KeyUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class OrderServiceImpl implements OrderService {

    @Autowired
    private ProductInfoServiceImpl productInfoRepository;

    @Autowired
    private OrderDetailRepository orderDetailRepository;

    @Autowired
    private OrderMasterRepository orderMasterRepository;


    @Override
    @Transactional
    public OrderDTO create(OrderDTO orderDTO) {

//        //查询商品（数量，价格）
//        BigDecimal count = new BigDecimal("ZERO");
//        List<OrderDetail> orderDetailList = orderDTO.getOrderDetailList();
//        if (orderDetailList.size() == 0){
//            throw  new OrderException("OrderDetailList为空");
//        }
//
//        for (OrderDetail orderDetail : orderDetailList) {
//            OrderDetail byDetailId = orderDetailRepository.findByDetailId(orderDetail.getDetailId());
//            if (byDetailId.getProductQuantity() < orderDetail.getProductQuantity()){
//                throw  new OrderException("产品库存小于0");
//            }
//
//            BigDecimal productPrice = byDetailId.getProductPrice();
//            count = productPrice.multiply(new BigDecimal(orderDetail.getProductQuantity()).add(count);
//        }
//        System.out.println(count);
//        if (count.equals(orderDTO.getOrderAmount())){
//            log.info("订单金额匹配正确 =【{}元】",count);
//        }else {
//            throw new OrderException(400);
//        }
//
//
//        //计算总价
//
//
//        //写入订单数据库
//        for (OrderDetail orderDetail : orderDetailList) {
//            OrderDetail byDetailId = orderDetailRepository.findByDetailId(orderDetail.getDetailId());
//            Integer  count1 = byDetailId.getProductQuantity() - orderDetail.getProductQuantity() ;
//            if (count1 > 0){
//                byDetailId.setProductQuantity(count1);
//                orderDetailRepository.save(orderDetail);
//            }
//        }
//
//        OrderMaster orderMaster = new OrderMaster();
//        BeanUtils.copyProperties(orderDTO,orderMaster);
//        orderMasterRepository.save(orderMaster);
//
//        //扣减库存
//        orderDTO.setOrderStatus(OrderStatusEnum.NEW.getCode());



        //----------------------------分割线
        //1. 查询商品（数量, 价格）
        String orderId = KeyUtil.genUniqueKey();
        BigDecimal orderAmount = new BigDecimal(0);

        for (OrderDetail orderDetail : orderDTO.getOrderDetailList()) {
            ProductInfo productInfo = productInfoRepository.findOne(orderDetail.getProductId());
            if (productInfo == null){
                throw  new SellException(ResultEnum.PRODUCT_NOT_EXIST);
            }

            //2. 计算订单总价
            orderAmount = productInfo.getProductPrice()
                    .multiply(new BigDecimal(orderDetail.getProductQuantity()))
                    .add(orderAmount);

            //订单详情入库
            BeanUtils.copyProperties(productInfo,orderDetail);
            orderDetail.setDetailId(KeyUtil.genUniqueKey());
            orderDetail.setOrderId(orderId);
            orderDetail.setProductQuantity(orderDetail.getProductQuantity());
            orderDetailRepository.save(orderDetail);
        }

        //3.写入到OrderMaster
        OrderMaster orderMaster = new OrderMaster();
        BeanUtils.copyProperties(orderDTO,orderMaster);
        orderMaster.setOrderId(orderId);
        orderMaster.setOrderAmount(orderAmount);
        orderMaster.setOrderStatus(OrderStatusEnum.NEW.getCode());
        orderMaster.setPayStatus(PayStatusEnum.WAIT.getCode());
        orderMasterRepository.save(orderMaster);


        //扣减库存

//        List<CartDTO> cartDTOList = new ArrayList<>();
//        for (OrderDetail orderDetail : orderDTO.getOrderDetailList()) {
//            CartDTO cartDTO = new CartDTO();
//            cartDTO.setProductId(orderDetail.getProductId());
//            cartDTO.setProductQuantity(orderDetail.getProductQuantity());
//            cartDTOList.add(cartDTO);
//        }
        List<CartDTO> cartDTOList = orderDTO.getOrderDetailList().stream()
                .map(e -> new CartDTO(e.getProductId(),e.getProductQuantity()))
                .collect(Collectors.toList());
        productInfoRepository.decreaseStock(cartDTOList);
        return orderDTO;
    }



    @Override
    public OrderDTO findOne(String orderId) {
        OrderMaster orderMaster = orderMasterRepository.findByOrderId(orderId);
        if (orderMaster == null) {
            throw new SellException(ResultEnum.ORDER_NOT_EXIST);
        }
        List<OrderDetail> orderDetailList = orderDetailRepository.findByOrderId(orderId);
        if (CollectionUtils.isEmpty(orderDetailList)) {
            throw new SellException(ResultEnum.ORDERDETAIL_NOT_EXIST);
        }
        OrderDTO orderDTO = new OrderDTO();
        BeanUtils.copyProperties(orderMaster,orderDTO);
        orderDTO.setOrderDetailList(orderDetailList);
        return orderDTO;
    }

    @Override
    public Page<OrderDTO> findList(String buyerOpenid, Pageable pageable) {
        Page<OrderMaster> orderMasterPage = orderMasterRepository.findByBuyerOpenid(buyerOpenid, pageable);
        List<OrderDTO> orderDTOList = OrderMaster2OrderDTOConverter.convert(orderMasterPage.getContent());
        return new PageImpl<OrderDTO>(orderDTOList,pageable,orderMasterPage.getTotalElements());
    }

    @Override
    @Transactional
    public OrderDTO cancel(OrderDTO orderDTO) {

        if (!orderDTO.getOrderStatus().equals(OrderStatusEnum.NEW.getCode())) {
            log.error("【取消订单】订单状态不正确, orderId={}, orderStatus={}", orderDTO.getOrderId(),orderDTO.getOrderStatus());
        }

        OrderMaster orderMaster = orderMasterRepository.findByOrderId(orderDTO.getOrderId());
        if (orderMaster == null){
            throw  new OrderException(ResultEnum.ORDER_NOT_EXIST);
        }
        orderMaster.setOrderStatus(OrderStatusEnum.CANCEL.getCode());
        OrderMaster updateResult = orderMasterRepository.save(orderMaster);


        if (updateResult == null) {
            log.error("【取消订单】更新失败, orderMaster={}", orderMaster);
            throw new SellException(ResultEnum.ORDER_UPDATE_FAIL);
        }

        //返回库存
        if (CollectionUtils.isEmpty(orderDTO.getOrderDetailList())){
            log.error("【取消订单】订单中无商品详情, orderDTO={}", orderDTO);
            throw new SellException(ResultEnum.ORDER_DETAIL_EMPTY);
        }
        List<CartDTO> cartDTOList = orderDTO.getOrderDetailList().stream()
                .map(e -> new CartDTO(e.getProductId(),e.getProductQuantity()))
                .collect(Collectors.toList());
        productInfoRepository.increaseStock(cartDTOList);
        return orderDTO;
    }

    @Override
    public OrderDTO finish(OrderDTO orderDTO) {
        return null;
    }

    @Override
    @Transactional
    public OrderDTO paid(OrderDTO orderDTO) {
        //判断订单状态
        if (!orderDTO.getOrderStatus().equals(OrderStatusEnum.NEW.getCode())) {
            log.error("【订单支付完成】订单状态不正确, orderId={}, orderStatus={}", orderDTO.getOrderId(), orderDTO.getOrderStatus());
            throw new SellException(ResultEnum.ORDER_STATUS_ERROR);
        }

        //判断支付状态
        if (!orderDTO.getPayStatus().equals(PayStatusEnum.WAIT.getCode())) {
            log.error("【订单支付完成】订单支付状态不正确, orderDTO={}", orderDTO);
            throw new SellException(ResultEnum.ORDER_PAY_STATUS_ERROR);
        }

        //修改支付状态
        orderDTO.setPayStatus(PayStatusEnum.SUCCESS.getCode());
        OrderMaster orderMaster = new OrderMaster();
        BeanUtils.copyProperties(orderDTO, orderMaster);
        OrderMaster updateResult = orderMasterRepository.save(orderMaster);
        if (updateResult == null) {
            log.error("【订单支付完成】更新失败, orderMaster={}", orderMaster);
            throw new SellException(ResultEnum.ORDER_UPDATE_FAIL);
        }

        return orderDTO;
    }

//    @Override
//    public Page<OrderDTO> findList(Pageable pageable) {
//        return null;
//    }

    @Override
    public Page<OrderDTO> findAllList(Pageable pageable) {
        Page<OrderMaster> orderMasterAllPage = orderMasterRepository.findAll(pageable);
        List<OrderDTO> orderDTOAllList = OrderMaster2OrderDTOConverter.convert(orderMasterAllPage.getContent());
        return new PageImpl<OrderDTO>(orderDTOAllList,pageable,orderMasterAllPage.getTotalElements());
    }
}
