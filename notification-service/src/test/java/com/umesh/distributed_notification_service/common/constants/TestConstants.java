package com.umesh.distributed_notification_service.common.constants;

import java.time.LocalDateTime;
import java.util.UUID;

public final class TestConstants {

    private TestConstants() {
    }

    public static final Long USER_ID = 1L;

    public static final String SUBJECT = "Welcome";

    public static final String MESSAGE =
            "Welcome to Distributed Notification Platform";

    public static final UUID EVENT_ID =
            UUID.fromString("11111111-1111-1111-1111-111111111111");

    public static final LocalDateTime FUTURE_TIME =
            LocalDateTime.now().plusHours(2);

}