package by.sample.shopflow.notification.event.listener;

import by.sample.shopflow.common.event.OrderCancelledEvent;
import by.sample.shopflow.common.event.OrderCreatedEvent;
import by.sample.shopflow.notification.service.OrderNotificationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaHandler;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

/**
 * Слушатель событий заказов.
 * Эмулирует отправку уведомлений (email / push / SMS).
 */
@Component
@KafkaListener(topics = "order-events")
public class OrderEventListener {

    private static final Logger log = LoggerFactory.getLogger(OrderEventListener.class);
    private final OrderNotificationService orderNotificationService;

    public OrderEventListener(OrderNotificationService orderNotificationService) {
        this.orderNotificationService = orderNotificationService;
    }

    @KafkaHandler
    public void handleOrderCreated(OrderCreatedEvent event) {
        orderNotificationService.sendOrderCreatedNotification(event);
    }

    @KafkaHandler
    public void handleOrderCancelled(OrderCancelledEvent event) {
        orderNotificationService.sendOrderCancelledNotification(event);
    }

    @KafkaHandler(isDefault = true)
    public void handleUnknown(Object event) {
        log.warn("Received unknown event on order-events: {}", event.getClass().getName());
    }
}
