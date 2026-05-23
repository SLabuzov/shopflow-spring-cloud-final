package by.sample.shopflow.catalog.web.dto;

import java.math.BigDecimal;
import java.util.UUID;


public record ProductResponse(
        UUID id,
        UUID categoryId,
        String name,
        BigDecimal price,
        boolean available
) {
}
