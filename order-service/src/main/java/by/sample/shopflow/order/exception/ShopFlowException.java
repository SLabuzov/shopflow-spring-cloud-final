package by.sample.shopflow.order.exception;

import lombok.Getter;

/**
 * Базовое исключение для всех бизнес-ошибок ShopFlow.
 */
@Getter
public abstract class ShopFlowException extends RuntimeException {

    private final String errorCode;

    protected ShopFlowException(String errorCode, String message) {
        super(message);
        this.errorCode = errorCode;
    }

    protected ShopFlowException(String errorCode, String message, Throwable cause) {
        super(message, cause);
        this.errorCode = errorCode;
    }
}
