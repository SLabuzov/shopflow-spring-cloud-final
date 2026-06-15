package by.sample.shopflow.common.event;

import java.time.Instant;
import java.util.UUID;

/**
 * Событие: заказ отменён (компенсирующая транзакция Saga).
 * Публикуется order-service, потребляется notification-service.
 */
public record OrderCancelledEvent(
        UUID orderId,
        UUID customerId,
        String reason,
        Instant cancelledAt
) {
}
