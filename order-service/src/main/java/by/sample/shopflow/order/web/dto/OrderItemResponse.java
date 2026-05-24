package by.sample.shopflow.order.web.dto;

import java.math.BigDecimal;
import java.util.UUID;

public record OrderItemResponse(
        UUID productId,
        String productName,
        BigDecimal price,
        int quantity,
        BigDecimal subtotal
) {
}
