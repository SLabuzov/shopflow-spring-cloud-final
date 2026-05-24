package by.sample.shopflow.order.exception;

import java.util.UUID;

/**
 * Ресурс не найден — базовый класс для XxxNotFoundException в каждом сервисе.
 */
public class ResourceNotFoundException extends ShopFlowException {

    public ResourceNotFoundException(String resourceType, UUID id) {
        super(
                "RESOURCE_NOT_FOUND",
                "%s with id %s not found".formatted(resourceType, id)
        );
    }
}
