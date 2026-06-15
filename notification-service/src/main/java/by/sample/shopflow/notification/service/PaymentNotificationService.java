package by.sample.shopflow.notification.service;

import by.sample.shopflow.common.event.PaymentCompletedEvent;
import by.sample.shopflow.common.event.PaymentFailedEvent;

public interface PaymentNotificationService {
    void sendPaymentCompletedNotification(PaymentCompletedEvent event);
    void sendPaymentFailedNotification(PaymentFailedEvent event);
}
