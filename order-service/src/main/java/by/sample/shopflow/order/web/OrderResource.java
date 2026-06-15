package by.sample.shopflow.order.web;

import by.sample.shopflow.order.service.OrderUseCase;
import by.sample.shopflow.order.web.dto.CreateOrderRequest;
import by.sample.shopflow.order.web.dto.OrderResponse;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/orders")
public class OrderResource {

    private final OrderUseCase orderUseCase;

    public OrderResource(OrderUseCase orderUseCase) {
        this.orderUseCase = orderUseCase;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public OrderResponse create(@Valid @RequestBody CreateOrderRequest request) {
        return orderUseCase.createOrder(request.customerId(), request);
    }

    @GetMapping()
    public List<OrderResponse> getCustomerOrders(@RequestParam(name = "customer") UUID customerId) {
        return orderUseCase.getOrdersByCustomer(customerId);
    }

    @GetMapping("/{orderId}")
    public OrderResponse getById(@PathVariable UUID orderId) {
        return orderUseCase.getOrder(orderId);
    }

    @PostMapping("/{orderId}/cancel")
    public void cancelOrder(@PathVariable UUID orderId) {
        orderUseCase.cancelOrder(orderId, "Manual cancellation");
    }
}
