package by.sample.shopflow.catalog.service;

import by.sample.shopflow.catalog.web.dto.CategoryResponse;
import by.sample.shopflow.catalog.web.dto.ProductResponse;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedModel;

import java.util.List;
import java.util.UUID;

public interface CatalogUseCase {

    List<CategoryResponse> getAllCategories();

    ProductResponse getProduct(UUID id);

    List<ProductResponse> getProducts(List<UUID> ids);

    PagedModel<ProductResponse> getProducts(UUID categoryId, Boolean available, Pageable pageable);
}
