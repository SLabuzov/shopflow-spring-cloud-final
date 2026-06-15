package by.sample.shopflow.payment.event.listener;

import by.sample.shopflow.common.event.OrderCancelledEvent;
import by.sample.shopflow.common.event.OrderCreatedEvent;
import by.sample.shopflow.payment.service.PaymentProcessingService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaHandler;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

/**
 * Kafka-потребитель: слушает топик order-events.
 * Использует @KafkaHandler для маршрутизации по типу события.
 */
@Slf4j
@Component
@KafkaListener(topics = "order-events")
public class OrderEventListener {

    private final PaymentProcessingService paymentService;

    public OrderEventListener(PaymentProcessingService paymentService) {
        this.paymentService = paymentService;
    }

    @KafkaHandler
    public void handleOrderCreated(OrderCreatedEvent event) {
        log.info("Received OrderCreatedEvent: orderId={}, amount={}",
                event.orderId(), event.totalAmount());
        paymentService.initPayment(event);
    }

    @KafkaHandler
    public void handleOrderCancelled(OrderCancelledEvent event) {
        log.info("Received OrderCancelledEvent: orderId={}, reason={}",
                event.orderId(), event.reason());
        // В реальной системе здесь был бы возврат средств (refund)
    }

    @KafkaHandler(isDefault = true)
    public void handleUnknown(Object event) {
        log.warn("Received unknown event on order-events: {}", event.getClass().getName());
    }
}
