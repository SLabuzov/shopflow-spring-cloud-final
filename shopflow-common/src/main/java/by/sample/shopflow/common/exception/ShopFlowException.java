package by.sample.shopflow.common.exception;

/**
 * Базовое исключение для всех бизнес-ошибок ShopFlow.
 */
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

    public String getErrorCode() {
        return errorCode;
    }
}
