package com.saga.paymentservice.config;

/* author :  Ankul Deshpande */

import com.saga.paymentservice.event.OrderEvent;
import com.saga.paymentservice.event.OrderStatus;
import com.saga.paymentservice.event.PaymentEvent;
import com.saga.paymentservice.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.function.Function;

@Configuration
@Slf4j
public class PaymentConsumerConfig {

    @Autowired
    private PaymentService paymentService;

    @Bean
    public Function<Flux<OrderEvent>, Flux<PaymentEvent>> paymentProcessor() {
        log.info("PaymentConsumerConfig : paymentProcessor ");
        return orderEventFlux -> orderEventFlux.flatMap(this::processPayment);

    }

    private Mono<PaymentEvent> processPayment(OrderEvent orderEvent) {
        log.info("PaymentConsumerConfig : processPayment {}",orderEvent);
        if(OrderStatus.ORDER_CREATED.equals(orderEvent.getOrderStatus())){
            return  Mono.fromSupplier(()->this.paymentService.newOrderEvent(orderEvent));
        }
        else{
            return  Mono.fromRunnable(()->this.paymentService.cancelOrderEvent(orderEvent));
        }

    }
}
