package by.sample.shopflow.common.event;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;

/**
 * Событие: оплата завершена.
 * Публикуется payment-service, потребляется order-service и notification-service.
 */
public record PaymentCompletedEvent(
        UUID paymentId,
        UUID orderId,
        BigDecimal amount,
        Instant completedAt
) {
}
