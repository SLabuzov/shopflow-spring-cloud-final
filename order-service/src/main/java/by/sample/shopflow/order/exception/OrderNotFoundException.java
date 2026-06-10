package by.sample.shopflow.order.exception;

import by.sample.shopflow.common.exception.ResourceNotFoundException;

import java.util.UUID;

public class OrderNotFoundException extends ResourceNotFoundException {

    public OrderNotFoundException(UUID id) {
        super("Order", id);
    }
}
