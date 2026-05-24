package by.sample.shopflow.order.web.dto;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;
import java.util.UUID;

public record OrderResponse(
        UUID id,
        UUID customerId,
        String status,
        BigDecimal totalAmount,
        List<OrderItemResponse> items,
        Instant createdAt
) {
}
