package com.saga.orderservice.config;

import com.saga.orderservice.event.PaymentEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.function.Consumer;

/* author :  Ankul Deshpande */
@Configuration
@Slf4j
public class EventConsumerConfig {


    @Autowired
    private OrderStatusUpdateHandler orderStatusUpdateHandler;

    @Bean
    public Consumer<PaymentEvent> paymentEventConsumer() {

        log.info("EventConsumerConfig : paymentEventConsumer ======> ");
        //listen payment-event topic
        //will check payment status
        //if payment status completed ->complete the order
        //if payment status failed -> cancel the order
        return (payment) -> orderStatusUpdateHandler.updateOrder(payment.getPaymentReqDto().getOrderId(),
                po -> {
                    log.info("Received PaymentEvent: {}", payment);
                    log.info("Updating OrderId={} with PaymentStatus={}",
                            payment.getPaymentReqDto().getOrderId(),
                            payment.getPaymentStatus());
                    po.setPaymentStatus(payment.getPaymentStatus());
                });

    }
}
