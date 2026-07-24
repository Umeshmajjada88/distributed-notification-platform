package com.umesh.distributed_notification_service.common.data;

import com.umesh.distributed_notification_service.common.constants.TestConstants;
import com.umesh.distributed_notification_service.domain.notification.dto.request.CreateNotificationRequest;
import com.umesh.distributed_notification_service.domain.notification.entity.Notification;
import com.umesh.distributed_notification_service.domain.notification.enums.NotificationChannel;
import com.umesh.distributed_notification_service.domain.notification.enums.NotificationStatus;
import java.util.UUID;

public final class NotificationTestDataFactory {

    private NotificationTestDataFactory() {
    }

    public static CreateNotificationRequest pendingRequest() {

        return CreateNotificationRequest.builder()
                .userId(TestConstants.USER_ID)
                .channel(NotificationChannel.EMAIL)
                .subject(TestConstants.SUBJECT)
                .message(TestConstants.MESSAGE)
                .build();

    }

    public static CreateNotificationRequest scheduledRequest() {

        return CreateNotificationRequest.builder()
                .userId(TestConstants.USER_ID)
                .channel(NotificationChannel.EMAIL)
                .subject(TestConstants.SUBJECT)
                .message(TestConstants.MESSAGE)
                .scheduledAt(TestConstants.FUTURE_TIME)
                .build();

    }

    public static Notification pendingNotification() {

        return Notification.builder()
                .userId(TestConstants.USER_ID)
                .eventId(UUID.randomUUID())
                .channel(NotificationChannel.EMAIL)
                .status(NotificationStatus.PENDING)
                .subject(TestConstants.SUBJECT)
                .message(TestConstants.MESSAGE)
                .retryCount(0)
                .build();

    }

    public static Notification scheduledNotification() {

        return Notification.builder()
                .userId(TestConstants.USER_ID)
                .eventId(UUID.randomUUID())
                .channel(NotificationChannel.EMAIL)
                .status(NotificationStatus.SCHEDULED)
                .subject(TestConstants.SUBJECT)
                .message(TestConstants.MESSAGE)
                .retryCount(0)
                .scheduledAt(TestConstants.FUTURE_TIME)
                .build();

    }

}