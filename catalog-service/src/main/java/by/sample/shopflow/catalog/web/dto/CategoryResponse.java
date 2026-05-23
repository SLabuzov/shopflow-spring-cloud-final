package by.sample.shopflow.catalog.web.dto;

import java.util.UUID;

public record CategoryResponse(
        UUID id,
        UUID parentId,
        String name,
        String description
) {
}
