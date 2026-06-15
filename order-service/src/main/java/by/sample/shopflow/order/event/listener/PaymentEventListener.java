package by.sample.shopflow.order.event.listener;

import by.sample.shopflow.common.event.PaymentCompletedEvent;
import by.sample.shopflow.common.event.PaymentFailedEvent;
import by.sample.shopflow.order.service.OrderUseCase;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaHandler;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

/**
 * Kafka-потребитель: слушает payment-events.
 * Обновляет статус заказа и публикует компенсирующие события (Saga).
 */
@Component
@KafkaListener(topics = "payment-events")
public class PaymentEventListener {

    private static final Logger log = LoggerFactory.getLogger(PaymentEventListener.class);

    private final OrderUseCase orderUseCase;

    public PaymentEventListener(OrderUseCase orderUseCase) {
        this.orderUseCase = orderUseCase;
    }

    @KafkaHandler
    public void handlePaymentCompleted(PaymentCompletedEvent event) {
        log.info("Received PaymentCompletedEvent for order {}", event.orderId());
        try {
            orderUseCase.markOrderPaid(event.orderId());
            log.info("Order {} marked as PAID", event.orderId());
        } catch (IllegalStateException e) {
            log.warn("Could not mark order {} as paid: {}", event.orderId(), e.getMessage());
        }
    }

    @KafkaHandler
    public void handlePaymentFailed(PaymentFailedEvent event) {
        log.info("Received PaymentFailedEvent for order {}: {}", event.orderId(), event.reason());
        try {
            orderUseCase.cancelOrder(event.orderId(), event.reason());
            log.info("Order {} cancelled (Saga compensation)", event.orderId());
        } catch (IllegalStateException e) {
            log.warn("Could not cancel order {}: {}", event.orderId(), e.getMessage());
        }
    }

    @KafkaHandler(isDefault = true)
    public void handleUnknown(Object event) {
        log.warn("Received unknown event on payment-events: {}", event.getClass().getName());
    }
}
