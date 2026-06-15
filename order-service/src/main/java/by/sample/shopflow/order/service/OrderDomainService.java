package by.sample.shopflow.order.service;

import by.sample.shopflow.common.dto.ProductResponse;
import by.sample.shopflow.common.event.OrderCancelledEvent;
import by.sample.shopflow.common.event.OrderCreatedEvent;
import by.sample.shopflow.order.client.CatalogClient;
import by.sample.shopflow.order.event.publisher.OrderEventPublisher;
import by.sample.shopflow.order.exception.OrderNotFoundException;
import by.sample.shopflow.order.exception.OrderValidationException;
import by.sample.shopflow.order.mapper.OrderMapper;
import by.sample.shopflow.order.model.Order;
import by.sample.shopflow.order.model.OrderItem;
import by.sample.shopflow.order.repository.OrderRepository;
import by.sample.shopflow.order.web.dto.CreateOrderRequest;
import by.sample.shopflow.order.web.dto.OrderItemRequest;
import by.sample.shopflow.order.web.dto.OrderResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@Transactional
public class OrderDomainService implements OrderUseCase {

    private static final Logger log = LoggerFactory.getLogger(OrderDomainService.class);

    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;
    private final CatalogClient catalogClient;
    private final OrderEventPublisher orderEventPublisher;

    public OrderDomainService(OrderRepository orderRepository,
                              OrderMapper orderMapper,
                              CatalogClient catalogClient,
                              OrderEventPublisher orderEventPublisher) {
        this.orderRepository = orderRepository;
        this.orderMapper = orderMapper;
        this.catalogClient = catalogClient;
        this.orderEventPublisher = orderEventPublisher;
    }

    @Override
    public OrderResponse createOrder(UUID customerId, CreateOrderRequest request) {
        List<ProductResponse> validatedProducts = validateProductsFromOrderRequest(request);

        // ПРОВЕРКА: Количество найденных продуктов должно строго совпадать с запросом
        if (validatedProducts.size() != request.items().size()) {
            throw new OrderValidationException("One or more products from the request do not exist in the catalog");
        }

        Map<UUID, OrderItemRequest> itemsMap = request
                .items()
                .stream()
                .collect(
                        Collectors.toMap(
                                OrderItemRequest::productId,
                                Function.identity()
                        )
                );

        var order = new Order(customerId);
        for (var checkedProduct : validatedProducts) {
            OrderItemRequest requestItem = itemsMap.get(checkedProduct.id());

            var orderItem = new OrderItem(
                    checkedProduct.id(),
                    checkedProduct.name(),
                    checkedProduct.price(),
                    requestItem.quantity()  // Количество берём из запроса
            );
            order.addItem(orderItem);
        }
        var savedOrder = orderRepository.save(order);
        log.info("Created new order {} for the customer {}", savedOrder.getId(), customerId);

        // Публикуем событие — запускаем Saga
        var event = new OrderCreatedEvent(
                order.getId(),
                order.getCustomerId(),
                order.getTotalAmount(),
                order.getCreatedAt()
        );
        orderEventPublisher.publishOrderCreated(event);

        return orderMapper.convert(savedOrder);
    }

    @Override
    @Transactional(readOnly = true)
    public OrderResponse getOrder(UUID orderId) {
        var order = orderRepository.findById(orderId)
                .orElseThrow(() -> new OrderNotFoundException(orderId));

        return orderMapper.convert(order);
    }

    @Override
    @Transactional(readOnly = true)
    public List<OrderResponse> getOrdersByCustomer(UUID customerId) {
        return orderRepository.findByCustomerId(customerId)
                .stream()
                .map(orderMapper::convert)
                .toList();
    }

    @Override
    public void cancelOrder(UUID orderId, String reason) {
        var order = orderRepository.findById(orderId)
                .orElseThrow(() -> new OrderNotFoundException(orderId));
        order.cancel();
        orderRepository.save(order);

        var event = new OrderCancelledEvent(
                order.getId(),
                order.getCustomerId(),
                reason,
                Instant.now()
        );
        orderEventPublisher.publishOrderCancelled(event);
    }

    @Override
    public void markOrderPaid(UUID orderId) {
        var order = orderRepository.findById(orderId)
                .orElseThrow(() -> new OrderNotFoundException(orderId));
        order.markPaid();
        orderRepository.save(order);
    }

    private List<ProductResponse> validateProductsFromOrderRequest(CreateOrderRequest request) {
        // Все productIds, которые используются в заказе
        var productIds = request
                .items()
                .stream()
                .map(OrderItemRequest::productId)
                .toList();

        return catalogClient.getProducts(productIds);
    }
}
