package by.sample.shopflow.notification.service;

import by.sample.shopflow.common.event.OrderCancelledEvent;
import by.sample.shopflow.common.event.OrderCreatedEvent;
import by.sample.shopflow.common.event.PaymentCompletedEvent;
import by.sample.shopflow.common.event.PaymentFailedEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class ConsoleNotificationService implements OrderNotificationService, PaymentNotificationService {

    private static final Logger log = LoggerFactory.getLogger(ConsoleNotificationService.class);

    @Override
    public void sendOrderCreatedNotification(OrderCreatedEvent event) {
        log.info("📧 NOTIFICATION: New order {} created for customer {}. Total: {} ₽",
                event.orderId(), event.customerId(), event.totalAmount());
        // В реальной системе: отправка email / push-уведомления
    }

    @Override
    public void sendOrderCancelledNotification(OrderCancelledEvent event) {
        log.info("📧 NOTIFICATION: Order {} cancelled for customer {}. Reason: {}",
                event.orderId(), event.customerId(), event.reason());
    }

    @Override
    public void sendPaymentCompletedNotification(PaymentCompletedEvent event) {

    }

    @Override
    public void sendPaymentFailedNotification(PaymentFailedEvent event) {

    }
}
