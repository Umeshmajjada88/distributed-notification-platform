package com.umesh.delivery_service.websocket.event;

import com.umesh.delivery_service.domain.delivery.enums.DeliveryProvider;
import com.umesh.shared.types.NotificationChannel;
import lombok.Builder;
import lombok.Value;

import java.time.LocalDateTime;

@Value
@Builder
public class NotificationStatusEvent {

    Long notificationId;

    Long deliveryId;

    String status;

    NotificationChannel channel;

    DeliveryProvider provider;

    String message;

    LocalDateTime timestamp;

}