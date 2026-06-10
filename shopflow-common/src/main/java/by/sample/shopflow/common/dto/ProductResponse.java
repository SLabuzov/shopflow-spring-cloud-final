package by.sample.shopflow.common.dto;

import java.math.BigDecimal;
import java.util.UUID;

/**
 * Ответ с данными о товаре — используется для межсервисного взаимодействия.
 * Catalog-service возвращает, Order-service потребляет.
 */
public record ProductResponse(
        UUID id,
        UUID categoryId,
        String name,
        BigDecimal price,
        boolean available
) {
}
