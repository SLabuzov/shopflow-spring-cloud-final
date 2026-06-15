package by.sample.shopflow.order.client;

import by.sample.shopflow.common.dto.ProductResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.UUID;

@FeignClient(
        configuration = CatalogClientConfig.class,
//        fallback = CatalogClientFallback.class,
        fallbackFactory = CatalogClientFallbackFactory.class,
        name = "catalog-service"
)
public interface CatalogClient {

    @GetMapping("/api/v1/products/{productId}")
    ProductResponse getProduct(@PathVariable UUID productId);

    @GetMapping("/api/v1/products/batch")
    List<ProductResponse> getProducts(@RequestParam("id") List<UUID> ids);
}
