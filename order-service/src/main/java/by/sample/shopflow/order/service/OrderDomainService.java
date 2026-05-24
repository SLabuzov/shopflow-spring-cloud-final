package by.sample.shopflow.order.service;

import by.sample.shopflow.order.exception.OrderNotFoundException;
import by.sample.shopflow.order.mapper.OrderMapper;
import by.sample.shopflow.order.model.Order;
import by.sample.shopflow.order.model.OrderItem;
import by.sample.shopflow.order.repository.OrderRepository;
import by.sample.shopflow.order.web.dto.CreateOrderRequest;
import by.sample.shopflow.order.web.dto.OrderResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@Transactional
public class OrderDomainService implements OrderUseCase {

    private static final Logger log = LoggerFactory.getLogger(OrderDomainService.class);

    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;

    public OrderDomainService(OrderRepository orderRepository,
                              OrderMapper orderMapper) {
        this.orderRepository = orderRepository;
        this.orderMapper = orderMapper;
    }

    @Override
    public OrderResponse createOrder(UUID customerId, CreateOrderRequest request) {
        var order = new Order(customerId);
        for (var product : request.items()) {
            var orderItem = new OrderItem(
                    product.productId(),
                    product.productName(),
                    product.price(),
                    product.quantity()
            );
            order.addItem(orderItem);
        }
        var savedOrder = orderRepository.save(order);
        log.info("Created new order {} for the customer {}", savedOrder.getId(), customerId);

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
    public void cancelOrder(UUID orderId) {
        var order = orderRepository.findById(orderId)
                .orElseThrow(() -> new OrderNotFoundException(orderId));
        order.cancel();
        orderRepository.save(order);
    }
}
