package com.umesh.distributed_notification_service.domain.delivery.service.impl;

import com.umesh.distributed_notification_service.domain.delivery.entity.NotificationDelivery;
import com.umesh.distributed_notification_service.domain.delivery.enums.DeliveryAttemptStatus;
import com.umesh.distributed_notification_service.domain.delivery.factory.DeliveryStrategyFactory;
import com.umesh.distributed_notification_service.domain.delivery.service.DeliveryService;
import com.umesh.distributed_notification_service.domain.delivery.service.NotificationDeliveryService;
import com.umesh.distributed_notification_service.domain.notification.entity.Notification;
import com.umesh.distributed_notification_service.domain.notification.enums.NotificationStatus;
import com.umesh.distributed_notification_service.domain.notification.event.dto.NotificationEvent;
import com.umesh.distributed_notification_service.domain.notification.repository.NotificationRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Slf4j
@Service
@RequiredArgsConstructor
public class DeliveryServiceImpl implements DeliveryService {

    private final DeliveryStrategyFactory strategyFactory;

    private final NotificationRepository notificationRepository;

    private final NotificationDeliveryService notificationDeliveryService;

    @Override
    @Transactional
    public void deliver(NotificationEvent event) {

        log.info(
                "Dispatching notification. eventId={}",
                event.getEventId());

        Notification notification = notificationRepository
                .findById(event.getNotificationId())
                .orElseThrow(() -> new RuntimeException(
                        "Notification not found : "
                                + event.getNotificationId()));

        NotificationDelivery delivery = NotificationDelivery.builder()
                .notificationId(notification.getId())
                .channel(notification.getChannel())
                .attemptNumber(notification.getRetryCount() + 1)
                .status(DeliveryAttemptStatus.PROCESSING)
                .startedAt(LocalDateTime.now())
                .build();

        notificationDeliveryService.save(delivery);

        try {

            strategyFactory
                    .getStrategy(event.getChannel())
                    .deliver(event);

            delivery.setStatus(DeliveryAttemptStatus.SUCCESS);
            delivery.setCompletedAt(LocalDateTime.now());

            notification.setStatus(NotificationStatus.SENT);

        } catch (Exception ex) {

            delivery.setStatus(DeliveryAttemptStatus.FAILED);
            delivery.setCompletedAt(LocalDateTime.now());
            delivery.setErrorMessage(ex.getMessage());

            notification.setStatus(NotificationStatus.FAILED);

            throw ex;

        } finally {

            notificationRepository.save(notification);

            notificationDeliveryService.save(delivery);

        }

        log.info(
                "Notification delivery completed. eventId={}",
                event.getEventId());
    }
}