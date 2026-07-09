package com.umesh.distributed_notification_service.domain.delivery.repository;

import com.umesh.distributed_notification_service.domain.delivery.entity.NotificationDelivery;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NotificationDeliveryRepository
        extends JpaRepository<NotificationDelivery, Long> {

}