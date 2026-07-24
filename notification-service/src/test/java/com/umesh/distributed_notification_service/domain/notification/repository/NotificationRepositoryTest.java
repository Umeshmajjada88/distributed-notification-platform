package com.umesh.distributed_notification_service.domain.notification.repository;

import com.umesh.distributed_notification_service.common.data.NotificationTestDataFactory;
import com.umesh.distributed_notification_service.domain.notification.entity.Notification;
import com.umesh.distributed_notification_service.domain.notification.enums.NotificationStatus;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import com.umesh.distributed_notification_service.common.config.JpaAuditingTestConfiguration;
import org.springframework.context.annotation.Import;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@Import(JpaAuditingTestConfiguration.class)
class NotificationRepositoryTest {

    @Autowired
    private NotificationRepository repository;

    @Test
    @DisplayName("Should save notification")
    void shouldSaveNotification() {

        Notification notification =
                NotificationTestDataFactory.pendingNotification();

        Notification saved =
                repository.save(notification);

        assertNotNull(saved.getId());

        assertEquals(
                NotificationStatus.PENDING,
                saved.getStatus());

    }

    @Test
    @DisplayName("Should find by event id")
    void shouldFindByEventId() {

        Notification notification =
                repository.save(
                        NotificationTestDataFactory.pendingNotification());

        Optional<Notification> result =
                repository.findByEventId(
                        notification.getEventId());

        assertTrue(result.isPresent());

    }

    @Test
    @DisplayName("Should return empty when event id does not exist")
    void shouldReturnEmptyWhenEventIdDoesNotExist() {

        Optional<Notification> result =
                repository.findByEventId(
                        UUID.randomUUID());

        assertTrue(result.isEmpty());

    }

    @Test
    @DisplayName("Should check event existence")
    void shouldCheckEventExistence() {

        Notification notification =
                repository.save(
                        NotificationTestDataFactory.pendingNotification());

        assertTrue(
                repository.existsByEventId(
                        notification.getEventId()));

    }

    @Test
    @DisplayName("Should find by status")
    void shouldFindByStatus() {

        repository.save(
                NotificationTestDataFactory.pendingNotification());

        List<Notification> notifications =
                repository.findByStatus(
                        NotificationStatus.PENDING);

        assertEquals(
                1,
                notifications.size());

    }

    @Test
    @DisplayName("Should count by status")
    void shouldCountByStatus() {

        repository.save(
                NotificationTestDataFactory.pendingNotification());

        repository.save(
                NotificationTestDataFactory.pendingNotification());

        long count =
                repository.countByStatus(
                        NotificationStatus.PENDING);

        assertEquals(
                2,
                count);

    }

    @Test
    @DisplayName("Should find scheduled notifications before time")
    void shouldFindScheduledNotifications() {

        Notification notification =
                NotificationTestDataFactory.scheduledNotification();

        repository.save(notification);

        List<Notification> result =
                repository.findByStatusAndScheduledAtBefore(
                        NotificationStatus.SCHEDULED,
                        LocalDateTime.now().plusDays(1));

        assertEquals(
                1,
                result.size());

    }

    @Test
    @DisplayName("Should find retryable notifications")
    void shouldFindRetryableNotifications() {

        Notification notification =
                NotificationTestDataFactory.pendingNotification();

        notification.setStatus(
                NotificationStatus.RETRYING);

        notification.setNextRetryAt(
                LocalDateTime.now().minusMinutes(1));

        repository.save(notification);

        List<Notification> result =
                repository.findByStatusAndNextRetryAtLessThanEqual(
                        NotificationStatus.RETRYING,
                        LocalDateTime.now());

        assertEquals(
                1,
                result.size());

    }

}