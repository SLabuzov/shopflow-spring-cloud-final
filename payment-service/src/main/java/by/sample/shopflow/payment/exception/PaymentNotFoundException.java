package by.sample.shopflow.payment.exception;

import by.sample.shopflow.common.exception.ResourceNotFoundException;

import java.util.UUID;

public class PaymentNotFoundException extends ResourceNotFoundException {

    public PaymentNotFoundException(UUID id) {
        super("Payment", id);
    }
}
