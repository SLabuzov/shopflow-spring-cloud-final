package by.sample.shopflow.notification.service;

import by.sample.shopflow.common.event.OrderCancelledEvent;
import by.sample.shopflow.common.event.OrderCreatedEvent;

public interface OrderNotificationService {
    void sendOrderCreatedNotification(OrderCreatedEvent event);
    void sendOrderCancelledNotification(OrderCancelledEvent event);
}
