package by.sample.shopflow.order.client;

import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
public class CatalogClientFallback implements CatalogClient {
    @Override
    public ProductResponse getProduct(UUID productId) {
        throw new CatalogClientUnavailableException("Order Service is unavailable");
    }

    @Override
    public List<ProductResponse> getProducts(List<UUID> ids) {
        return List.of();
    }
}
