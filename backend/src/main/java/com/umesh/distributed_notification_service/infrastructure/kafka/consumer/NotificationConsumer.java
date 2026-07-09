package com.umesh.distributed_notification_service.infrastructure.kafka.consumer;

import com.umesh.distributed_notification_service.common.constants.KafkaTopics;
import com.umesh.distributed_notification_service.domain.notification.event.dto.NotificationEvent;
import com.umesh.distributed_notification_service.domain.notification.processor.NotificationProcessor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class NotificationConsumer {

    private final NotificationProcessor notificationProcessor;

    @KafkaListener(topics = KafkaTopics.NOTIFICATION_CREATED, groupId = "notification-delivery-group")
    public void consume(NotificationEvent event) {

        log.info(
                "Notification event received from Kafka. eventId={}",
                event.getEventId());

        notificationProcessor.process(event);

        log.info(
                "Kafka message processed successfully. eventId={}",
                event.getEventId());

        log.info(
                "Kafka Consumer received {}",
                event.getEventId());
    }
}