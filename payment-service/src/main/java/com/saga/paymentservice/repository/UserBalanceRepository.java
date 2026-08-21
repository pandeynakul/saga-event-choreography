package com.saga.paymentservice.repository;

/* author :  Ankul Deshpande */

import com.saga.paymentservice.entity.UserBalance;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserBalanceRepository extends JpaRepository<UserBalance,Integer> {
}
