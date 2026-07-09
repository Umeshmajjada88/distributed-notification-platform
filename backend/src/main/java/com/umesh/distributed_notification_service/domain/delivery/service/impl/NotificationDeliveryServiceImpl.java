package com.umesh.distributed_notification_service.domain.delivery.service.impl;

import com.umesh.distributed_notification_service.domain.delivery.entity.NotificationDelivery;
import com.umesh.distributed_notification_service.domain.delivery.repository.NotificationDeliveryRepository;
import com.umesh.distributed_notification_service.domain.delivery.service.NotificationDeliveryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class NotificationDeliveryServiceImpl
        implements NotificationDeliveryService {

    private final NotificationDeliveryRepository notificationDeliveryRepository;

    @Override
    public NotificationDelivery save(NotificationDelivery delivery) {

        return notificationDeliveryRepository.save(delivery);

    }
}