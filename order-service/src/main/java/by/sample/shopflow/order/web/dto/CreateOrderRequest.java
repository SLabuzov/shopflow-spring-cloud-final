package by.sample.shopflow.order.web.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;

import java.util.List;
import java.util.UUID;

public record CreateOrderRequest(
        UUID customerId,
        @NotEmpty @Valid List<OrderItemRequest> items
) {
}
