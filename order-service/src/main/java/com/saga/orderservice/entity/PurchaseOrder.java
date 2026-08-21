package com.saga.orderservice.entity;

/* author :  Ankul Deshpande */

import com.saga.orderservice.event.OrderStatus;
import com.saga.orderservice.event.PaymentStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "PURCHASE_ORD_TBL")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PurchaseOrder {
    private Integer userId;
    private Integer prodId;
    private Integer price;
    @Id
    @GeneratedValue
    @Column(name = "order_id")
    private Integer orderId;
    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;
    @Enumerated(EnumType.STRING)
    private PaymentStatus paymentStatus;
}
