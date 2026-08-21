package com.saga.paymentservice.repository;

/* author :  Ankul Deshpande */

import com.saga.paymentservice.entity.UserTransaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserTransactionRepository extends JpaRepository<UserTransaction,Integer> {
}
