package com.saga.paymentservice.entity;

/* author :  Ankul Deshpande */

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserTransaction {
    @Id
    private Integer ordId;
    private Integer usrId;
    private Integer amount;
}
