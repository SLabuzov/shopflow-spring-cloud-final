package by.sample.shopflow.order.exception;

public class OrderValidationException extends ShopFlowException {

    public OrderValidationException(String message) {
        super("ORDER_VALIDATION_FAILED", message);
    }
}
