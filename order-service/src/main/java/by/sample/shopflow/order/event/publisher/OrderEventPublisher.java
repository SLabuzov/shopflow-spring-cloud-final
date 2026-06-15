package by.sample.shopflow.order.event.publisher;

import by.sample.shopflow.common.event.OrderCancelledEvent;
import by.sample.shopflow.common.event.OrderCreatedEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class OrderEventPublisher {

    private static final String TOPIC = "order-events";

    private final KafkaTemplate<String, Object> kafkaTemplate;

    public OrderEventPublisher(KafkaTemplate<String, Object> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void publishOrderCreated(OrderCreatedEvent event) {
        kafkaTemplate.send(TOPIC, event.orderId().toString(), event);
        log.info("Published OrderCreatedEvent for order {}", event.orderId());
    }

    public void publishOrderCancelled(OrderCancelledEvent event) {
        kafkaTemplate.send(TOPIC, event.orderId().toString(), event);
        log.info("Published OrderCancelledEvent for order {}", event.orderId());
    }
}
