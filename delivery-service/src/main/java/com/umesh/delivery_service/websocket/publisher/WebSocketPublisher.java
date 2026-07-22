package com.umesh.delivery_service.websocket.publisher;

import com.umesh.delivery_service.websocket.event.NotificationStatusEvent;

public interface WebSocketPublisher {

    void publish(NotificationStatusEvent event);

}