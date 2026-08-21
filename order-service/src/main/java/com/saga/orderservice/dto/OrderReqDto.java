package com.saga.orderservice.dto;

/* author :  Ankul Deshpande */

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderReqDto {
    private Integer userId;
    private Integer prodId;
    private Integer amount;
    private Integer orderId;
}
