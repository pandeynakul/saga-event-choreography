package com.saga.orderservice.repository;

/* author :  Ankul Deshpande */

import com.saga.orderservice.entity.PurchaseOrder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository  extends JpaRepository<PurchaseOrder, Integer> {
}
