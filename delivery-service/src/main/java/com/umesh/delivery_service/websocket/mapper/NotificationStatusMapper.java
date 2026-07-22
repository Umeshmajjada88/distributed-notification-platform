package com.umesh.delivery_service.websocket.mapper;

import com.umesh.delivery_service.domain.delivery.entity.Delivery;
import com.umesh.delivery_service.websocket.event.NotificationStatusEvent;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class NotificationStatusMapper {

    public NotificationStatusEvent toEvent(
            Delivery delivery,
            String message) {

        return NotificationStatusEvent.builder()
                .notificationId(delivery.getNotificationId())
                .deliveryId(delivery.getId())
                .status(delivery.getStatus().name())
                .channel(delivery.getChannel())
                .provider(delivery.getProvider())
                .message(message)
                .timestamp(LocalDateTime.now())
                .build();

    }

}