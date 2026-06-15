package by.sample.shopflow.payment.event.publisher;

import by.sample.shopflow.common.event.PaymentCompletedEvent;
import by.sample.shopflow.common.event.PaymentFailedEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class PaymentEventPublisher {

    private static final Logger log = LoggerFactory.getLogger(PaymentEventPublisher.class);
    private static final String TOPIC = "payment-events";

    private final KafkaTemplate<String, Object> kafkaTemplate;

    public PaymentEventPublisher(KafkaTemplate<String, Object> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void publishPaymentCompleted(PaymentCompletedEvent event) {
        kafkaTemplate.send(TOPIC, event.orderId().toString(), event);
        log.info("Published PaymentCompletedEvent for order {}", event.orderId());
    }

    public void publishPaymentFailed(PaymentFailedEvent event) {
        kafkaTemplate.send(TOPIC, event.orderId().toString(), event);
        log.info("Published PaymentFailedEvent for order {}: {}", event.orderId(), event.reason());
    }
}
