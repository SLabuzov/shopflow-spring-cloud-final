package by.sample.shopflow.common.event;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;

/**
 * Событие: заказ создан.
 * Публикуется order-service, потребляется payment-service и notification-service.
 */
public record OrderCreatedEvent(
        UUID orderId,
        UUID customerId,
        BigDecimal totalAmount,
        Instant createdAt
) {
}
