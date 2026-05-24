package by.sample.shopflow.order.exception;

import java.util.UUID;

public class OrderNotFoundException extends ResourceNotFoundException {

    public OrderNotFoundException(UUID id) {
        super("Order", id);
    }
}
