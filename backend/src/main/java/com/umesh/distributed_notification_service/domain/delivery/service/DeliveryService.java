package com.umesh.distributed_notification_service.domain.delivery.service;

import com.umesh.distributed_notification_service.domain.notification.event.dto.NotificationEvent;

public interface DeliveryService {

    void deliver(NotificationEvent event);

}