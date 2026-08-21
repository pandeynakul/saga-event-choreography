package com.saga.paymentservice.service;

/* author :  Ankul Deshpande */

import com.saga.paymentservice.dto.OrderReqDto;
import com.saga.paymentservice.dto.PaymentReqDto;
import com.saga.paymentservice.entity.UserBalance;
import com.saga.paymentservice.entity.UserTransaction;
import com.saga.paymentservice.event.OrderEvent;
import com.saga.paymentservice.event.PaymentEvent;
import com.saga.paymentservice.event.PaymentStatus;
import com.saga.paymentservice.repository.UserBalanceRepository;
import com.saga.paymentservice.repository.UserTransactionRepository;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@Slf4j
public class PaymentService {
    @Autowired
    private UserBalanceRepository userBalanceRepository;
    @Autowired
    private UserTransactionRepository userTransactionRepository;

    @PostConstruct
    public void initUserBalanceInDB() {
        userBalanceRepository.saveAll(Stream.of(new UserBalance(101, 5000),
                new UserBalance(102, 6000),
                new UserBalance(103, 7000),
                new UserBalance(104, 8000),
                new UserBalance(105, 9000),
                new UserBalance(106, 2000),
                new UserBalance(107, 3000)
        ).collect(Collectors.toList()));

    }

    @Transactional
    public PaymentEvent newOrderEvent(OrderEvent orderEvent) {
        log.info("***PaymentService** newOrderEvent: {}", orderEvent);
        OrderReqDto orderReqDto = orderEvent.getOrderReqDto();
        //build the payment req object
        PaymentReqDto paymentReqDto = new PaymentReqDto(orderReqDto.getOrderId(),
                orderReqDto.getUserId(), orderReqDto.getAmount());
        //check balance:
        return userBalanceRepository.findById(orderReqDto.getUserId()).
                filter(ub -> ub.getPrice() > orderReqDto.getAmount()).
                map(ub -> {
                    ub.setPrice(ub.getPrice() - orderReqDto.getAmount());
                    userTransactionRepository.save(new UserTransaction(orderReqDto.getOrderId(),
                            orderReqDto.getUserId(), orderReqDto.getAmount()));
                    return new PaymentEvent(paymentReqDto, PaymentStatus.PAYMENT_COMPLETED);
                }).orElse(new PaymentEvent(paymentReqDto, PaymentStatus.PAYMENT_FAILED));
    }

    @Transactional
    public void cancelOrderEvent(OrderEvent orderEvent) {
        log.info("***PaymentService** cancelOrderEvent: {}", orderEvent);
        userTransactionRepository.findById(orderEvent.getOrderReqDto().getOrderId()).
                ifPresent(ut -> {
                    userTransactionRepository.delete(ut);
                    userTransactionRepository.findById(ut.getUsrId()).
                            ifPresent(ub -> ub.setAmount(ub.getAmount() + ut.getAmount()));

                });
    }
}
