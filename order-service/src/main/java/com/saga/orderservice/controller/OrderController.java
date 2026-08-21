package com.saga.orderservice.controller;

/* author :  Ankul Deshpande */

import com.saga.orderservice.dto.OrderReqDto;
import com.saga.orderservice.entity.PurchaseOrder;
import com.saga.orderservice.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/order")
@Slf4j
public class OrderController {

    @Autowired
    public OrderService orderService;

    @PostMapping("/create")
    public PurchaseOrder purchaseOrder(@RequestBody OrderReqDto orderReqDto) {

        return orderService.createOrder(orderReqDto);

    }

    @GetMapping
    public List<PurchaseOrder> getOrderDetails() {
        return orderService.getAllOrder();
    }

}
