package com.umesh.distributed_notification_service.domain.delivery.strategy;

import com.umesh.distributed_notification_service.domain.notification.event.dto.NotificationEvent;
import com.umesh.distributed_notification_service.domain.notification.enums.NotificationChannel;

public interface NotificationDeliveryStrategy {

    NotificationChannel getSupportedChannel();

    void deliver(NotificationEvent event);

}