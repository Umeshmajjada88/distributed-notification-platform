CREATE TABLE notification_template (

    id BIGSERIAL PRIMARY KEY,

    template_code VARCHAR(100) NOT NULL,

    name VARCHAR(150) NOT NULL,

    channel VARCHAR(30) NOT NULL,

    subject VARCHAR(255),

    body TEXT NOT NULL,

    description VARCHAR(500),

    status VARCHAR(30) NOT NULL,

    template_version BIGINT NOT NULL DEFAULT 1,

    is_system BOOLEAN NOT NULL DEFAULT FALSE,

    created_at TIMESTAMP NOT NULL,

    updated_at TIMESTAMP NOT NULL,

    CONSTRAINT uk_notification_template_code_channel
        UNIQUE (template_code, channel)

);

CREATE INDEX idx_notification_template_status
    ON notification_template(status);

CREATE INDEX idx_notification_template_channel
    ON notification_template(channel);