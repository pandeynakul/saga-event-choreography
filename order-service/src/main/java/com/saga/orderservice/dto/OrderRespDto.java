package com.saga.orderservice.dto;

/* author :  Ankul Deshpande */

import com.saga.orderservice.event.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderRespDto {
    private Integer userId;
    private Integer prodId;
    private Integer amount;
    private Integer orderId;
    private OrderStatus orderStatus;
}
