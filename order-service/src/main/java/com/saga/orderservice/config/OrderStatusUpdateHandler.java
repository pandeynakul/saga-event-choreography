package com.saga.orderservice.config;

/* author :  Ankul Deshpande */

import com.saga.orderservice.dto.OrderReqDto;
import com.saga.orderservice.entity.PurchaseOrder;
import com.saga.orderservice.event.OrderStatus;
import com.saga.orderservice.event.PaymentStatus;
import com.saga.orderservice.repository.OrderRepository;
import com.saga.orderservice.service.OrderStatusPublisher;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.Transactional;

import java.util.function.Consumer;

@Configuration
@Slf4j
public class OrderStatusUpdateHandler {


    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private OrderStatusPublisher orderStatusPublisher;

    @Transactional
    public void updateOrder(int id, Consumer<PurchaseOrder> consumer) {
        log.info("OrderStatusUpdateHandler : consumer {} ",id);
        orderRepository.findById(id).ifPresent(consumer.andThen(this::updateOrder));
    }

    private void updateOrder(PurchaseOrder purchaseOrder) {
        log.info("OrderStatusUpdateHandler : updateOrder {} ",purchaseOrder);
        boolean isPayCompleted = PaymentStatus.PAYMENT_COMPLETED.equals(purchaseOrder.getPaymentStatus());
        OrderStatus orderStatus = isPayCompleted ? OrderStatus.ORDER_COMPLETED : OrderStatus.ORDER_CANCELED;
        purchaseOrder.setOrderStatus(orderStatus);
        if (!isPayCompleted) {
            orderStatusPublisher.publishOrderEvent(convertEntityToDto(purchaseOrder), orderStatus);
        }

    }

    private OrderReqDto convertEntityToDto(PurchaseOrder purchaseOrder) {
        OrderReqDto orderReqDto = new OrderReqDto();
        orderReqDto.setOrderId(purchaseOrder.getOrderId());
        orderReqDto.setUserId(purchaseOrder.getUserId());
        orderReqDto.setAmount(purchaseOrder.getPrice());
        orderReqDto.setProdId(purchaseOrder.getProdId());
        return orderReqDto;
    }
}
