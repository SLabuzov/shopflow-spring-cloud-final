package by.sample.shopflow.order.exception;

import by.sample.shopflow.common.exception.ShopFlowException;

public class OrderValidationException extends ShopFlowException {

    public OrderValidationException(String message) {
        super("ORDER_VALIDATION_FAILED", message);
    }
}
