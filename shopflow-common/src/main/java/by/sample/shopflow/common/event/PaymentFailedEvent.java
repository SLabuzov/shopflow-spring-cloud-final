package by.sample.shopflow.common.event;

import java.time.Instant;
import java.util.UUID;

/**
 * Событие: оплата не прошла.
 * Публикуется payment-service, потребляется order-service (компенсация) и notification-service.
 */
public record PaymentFailedEvent(
        UUID paymentId,
        UUID orderId,
        String reason,
        Instant failedAt
) {
}
