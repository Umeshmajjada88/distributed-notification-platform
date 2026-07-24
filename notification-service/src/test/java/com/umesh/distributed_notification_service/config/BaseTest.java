package com.umesh.distributed_notification_service.config;

import java.time.LocalDateTime;
import java.util.UUID;

public abstract class BaseTest {

    protected static final Long USER_ID = 1L;

    protected static final Long NOTIFICATION_ID = 100L;

    protected static final UUID EVENT_ID =
            UUID.fromString(
                    "11111111-1111-1111-1111-111111111111");

    protected static final LocalDateTime NOW =
            LocalDateTime.of(
                    2026,
                    1,
                    1,
                    10,
                    0);

}