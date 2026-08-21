package com.saga.orderservice.service;

/* author :  Ankul Deshpande */


import com.saga.orderservice.dto.OrderReqDto;
import com.saga.orderservice.entity.PurchaseOrder;
import com.saga.orderservice.event.OrderStatus;
import com.saga.orderservice.repository.OrderRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private OrderStatusPublisher orderStatusPublisher;

    @Transactional
    public PurchaseOrder createOrder(OrderReqDto orderReqDto) {
        PurchaseOrder purchaseOrder = orderRepository.save(convertDtoToEntity(orderReqDto));
        orderReqDto.setOrderId(purchaseOrder.getOrderId());
        //produce kafka event status object created (order event:)
        orderStatusPublisher.publishOrderEvent(orderReqDto, OrderStatus.ORDER_CREATED);
        return  purchaseOrder;

    }

    public List<PurchaseOrder> getAllOrder(){
        return orderRepository.findAll();
    }

    public PurchaseOrder convertDtoToEntity(OrderReqDto dto) {
        //create the entity object
        PurchaseOrder purchaseOrder = new PurchaseOrder();
        purchaseOrder.setProdId(dto.getProdId());
        purchaseOrder.setUserId(dto.getUserId());
        purchaseOrder.setOrderStatus(OrderStatus.ORDER_CREATED);
        purchaseOrder.setPrice(dto.getAmount());
        return purchaseOrder;
    }
}
