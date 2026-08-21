package com.saga.orderservice.event;

/* author :  Ankul Deshpande */

import com.saga.orderservice.dto.PaymentReqDto;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.UUID;

@NoArgsConstructor
@Data
public class PaymentEvent implements Event {

    private UUID eventId = UUID.randomUUID();
    private Date eventDate = new Date();
    private PaymentReqDto paymentReqDto;
    private PaymentStatus paymentStatus;

    public PaymentEvent(PaymentReqDto paymentReqDto, PaymentStatus paymentStatus) {
        this.paymentReqDto = paymentReqDto;
        this.paymentStatus = paymentStatus;
    }

    @Override
    public UUID getEventId() {
        return eventId;
    }

    @Override
    public Date getDate() {
        return eventDate;
    }
}
