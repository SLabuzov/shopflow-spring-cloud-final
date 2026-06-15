package by.sample.shopflow.payment.service;

import by.sample.shopflow.common.event.OrderCreatedEvent;
import by.sample.shopflow.common.event.PaymentCompletedEvent;
import by.sample.shopflow.common.event.PaymentFailedEvent;
import by.sample.shopflow.payment.event.publisher.PaymentEventPublisher;
import by.sample.shopflow.payment.exception.PaymentNotFoundException;
import by.sample.shopflow.payment.model.Payment;
import by.sample.shopflow.payment.repository.PaymentRepository;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.UUID;

@Slf4j
@Service
@Transactional
public class PaymentProcessingService {

    private final PaymentRepository paymentRepository;
    private final PaymentEventPublisher eventPublisher;

    public PaymentProcessingService(PaymentRepository paymentRepository,
                                    PaymentEventPublisher eventPublisher) {
        this.paymentRepository = paymentRepository;
        this.eventPublisher = eventPublisher;
    }

    public void initPayment(OrderCreatedEvent event) {
        // Идемпотентность: проверяем, не обработан ли уже этот заказ
        if (paymentRepository.existsByOrderId(event.orderId())) {
            log.info("Payment already exists for order {}, skipping (idempotent)", event.orderId());
            return;
        }

        var payment = new Payment(event.orderId(), event.totalAmount());
        paymentRepository.save(payment);
    }

    public void processSuccessPayment(UUID paymentId) {
        // Эмуляция успешного платежа
        var payment = paymentRepository
                .findById(paymentId)
                .orElseThrow(() -> new PaymentNotFoundException(paymentId));

        payment.complete();
        paymentRepository.save(payment);

        eventPublisher.publishPaymentCompleted(new PaymentCompletedEvent(
                payment.getId(), payment.getOrderId(), payment.getAmount(), Instant.now()
        ));
        log.info("Payment COMPLETED for order {} — amount {}",
                payment.getOrderId(), payment.getAmount());
    }

    public void processFailPayment(UUID paymentId) {
        // Эмуляция отклонения платежа
        var payment = paymentRepository
                .findById(paymentId)
                .orElseThrow(() -> new PaymentNotFoundException(paymentId));

        payment.fail();
        paymentRepository.save(payment);

        eventPublisher.publishPaymentFailed(new PaymentFailedEvent(
                payment.getId(), payment.getOrderId(),
                "Payment FAILED",
                Instant.now()
        ));
        log.warn("Payment FAILED for order {} — amount {} exceeds limit",
                payment.getOrderId(), payment.getAmount());
    }
}
