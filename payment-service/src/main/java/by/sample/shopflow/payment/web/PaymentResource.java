package by.sample.shopflow.payment.web;

import by.sample.shopflow.payment.service.PaymentProcessingService;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/payments")
public class PaymentResource {

    private final PaymentProcessingService paymentProcessingService;

    public PaymentResource(PaymentProcessingService paymentProcessingService) {
        this.paymentProcessingService = paymentProcessingService;
    }

    @PostMapping("/success")
    void success(@RequestBody SuccessCommand event) {
        paymentProcessingService.processSuccessPayment(event.paymentId());
    }

    @PostMapping("/fail")
    void fail(@RequestBody FailCommand event) {
        paymentProcessingService.processFailPayment(event.paymentId());
    }
}


record SuccessCommand (UUID paymentId) {
}

record FailCommand (UUID paymentId) {
}