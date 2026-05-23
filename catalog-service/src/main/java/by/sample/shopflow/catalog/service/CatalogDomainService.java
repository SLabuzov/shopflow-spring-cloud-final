package by.sample.shopflow.catalog.service;

import by.sample.shopflow.catalog.exception.ProductNotFoundException;
import by.sample.shopflow.catalog.mapper.CategoryMapper;
import by.sample.shopflow.catalog.mapper.ProductMapper;
import by.sample.shopflow.catalog.repository.CategoryRepository;
import by.sample.shopflow.catalog.repository.ProductRepository;
import by.sample.shopflow.catalog.web.dto.CategoryResponse;
import by.sample.shopflow.catalog.web.dto.ProductResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedModel;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class CatalogDomainService implements CatalogUseCase {

    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;
    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    public CatalogDomainService(CategoryRepository categoryRepository,
                                CategoryMapper categoryMapper,
                                ProductRepository productRepository,
                                ProductMapper productMapper) {
        this.categoryRepository = categoryRepository;
        this.categoryMapper = categoryMapper;
        this.productRepository = productRepository;
        this.productMapper = productMapper;
    }

    @Override
    public List<CategoryResponse> getAllCategories() {
        return categoryRepository.findAll()
                .stream()
                .map(categoryMapper::convert)
                .toList();
    }

    @Override
    public ProductResponse getProduct(UUID id) {
        return productRepository.findById(id)
                .map(productMapper::convert)
                .orElseThrow(() -> new ProductNotFoundException(id));
    }

    @Override
    public PagedModel<ProductResponse> getProducts(UUID categoryId, Boolean available, Pageable pageable) {
        Page<ProductResponse> result = productRepository
                .findFiltered(categoryId, available, pageable)
                .map(productMapper::convert);

        return new PagedModel<>(result);
    }
}
