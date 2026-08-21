package com.saga.orderservice.event;

/* author :  Ankul Deshpande */

import com.saga.orderservice.dto.OrderReqDto;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.UUID;

@NoArgsConstructor
@Data
public class OrderEvent implements Event{
    private UUID eventId = UUID.randomUUID();
    private Date eventDate = new Date();
    private OrderReqDto orderReqDto;
    private OrderStatus orderStatus;

    public OrderEvent(OrderReqDto orderReqDto, OrderStatus orderStatus) {
        this.orderReqDto = orderReqDto;
        this.orderStatus = orderStatus;
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
