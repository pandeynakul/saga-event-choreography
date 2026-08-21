package com.saga.paymentservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/* author :  Ankul Deshpande */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaymentReqDto {
    private Integer userId;
    private Integer amount;
    private Integer orderId;
}
