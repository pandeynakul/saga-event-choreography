package com.saga.orderservice.service;

/* author :  Ankul Deshpande */

import com.saga.orderservice.dto.OrderReqDto;
import com.saga.orderservice.event.OrderEvent;
import com.saga.orderservice.event.OrderStatus;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Sinks;


@Service
@Slf4j
public class OrderStatusPublisher {
    @Autowired
    private Sinks.Many<OrderEvent> orderSinks;

    public void publishOrderEvent(OrderReqDto orderReqDto, OrderStatus orderStatus) {
        log.info("=====>Publishing OrderEvent: {} {}",orderReqDto, orderStatus);
        OrderEvent orderEvent = new OrderEvent(orderReqDto, orderStatus);
        log.info("=====>Publishing OrderEvent: {} ",orderEvent);
        orderSinks.tryEmitNext(orderEvent);
    }
}
