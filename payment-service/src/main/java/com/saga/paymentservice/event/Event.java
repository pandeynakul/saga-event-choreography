package com.saga.paymentservice.event;

/* author :  Ankul Deshpande */

import java.util.Date;
import java.util.UUID;

public interface Event {
    UUID getEventId();
    Date getDate();
}
