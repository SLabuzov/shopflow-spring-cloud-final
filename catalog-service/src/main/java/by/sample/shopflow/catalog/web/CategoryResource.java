package by.sample.shopflow.catalog.web;

import by.sample.shopflow.catalog.service.CatalogUseCase;
import by.sample.shopflow.catalog.web.dto.CategoryResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/categories")
public class CategoryResource {

    private final CatalogUseCase catalogUseCase;

    public CategoryResource(CatalogUseCase catalogUseCase) {
        this.catalogUseCase = catalogUseCase;
    }

    @GetMapping
    public List<CategoryResponse> getAll() {
        return catalogUseCase.getAllCategories();
    }
}
