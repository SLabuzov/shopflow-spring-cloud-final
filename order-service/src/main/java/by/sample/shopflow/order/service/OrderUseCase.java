package by.sample.shopflow.order.service;

import by.sample.shopflow.order.web.dto.CreateOrderRequest;
import by.sample.shopflow.order.web.dto.OrderResponse;

import java.util.List;
import java.util.UUID;

public interface OrderUseCase {

    OrderResponse createOrder(UUID customerId, CreateOrderRequest request);

    OrderResponse getOrder(UUID orderId);

    List<OrderResponse> getOrdersByCustomer(UUID customerId);

    void cancelOrder(UUID orderId, String reason);

    void markOrderPaid(UUID orderId);
}
