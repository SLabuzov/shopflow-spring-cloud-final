package by.sample.shopflow.catalog.web;

import by.sample.shopflow.catalog.service.CatalogUseCase;
import by.sample.shopflow.catalog.web.dto.ProductResponse;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/products")
public class ProductResource {

    private final CatalogUseCase catalogUseCase;

    public ProductResource(CatalogUseCase catalogUseCase) {
        this.catalogUseCase = catalogUseCase;
    }

    @GetMapping("/{id}")
    public ProductResponse getById(@PathVariable UUID id) {
        return catalogUseCase.getProduct(id);
    }

    @GetMapping("/batch")
    public List<ProductResponse> getProductsByIds(@RequestParam("id") List<UUID> ids) {
        return catalogUseCase.getProducts(ids);
    }

    @GetMapping
    public PagedModel<ProductResponse> getAll(
            @RequestParam(required = false) UUID categoryId,
            @RequestParam(required = false) Boolean available,
            Pageable pageable) {
        return catalogUseCase.getProducts(categoryId, available, pageable);
    }

}
