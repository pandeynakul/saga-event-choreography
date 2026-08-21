package com.saga.paymentservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/* author :  Ankul Deshpande */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderReqDto {

    private Integer userId;
    private Integer prodId;
    private Integer amount;
    private Integer orderId;
}
