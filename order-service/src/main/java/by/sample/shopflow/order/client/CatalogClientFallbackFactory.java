package by.sample.shopflow.order.client;

import by.sample.shopflow.common.dto.ProductResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.UUID;

@Component
public class CatalogClientFallbackFactory implements FallbackFactory<CatalogClient> {

    private static final Logger log = LoggerFactory.getLogger(CatalogClientFallbackFactory.class);

    @Override
    public CatalogClient create(Throwable cause) {
        return new CatalogClient() {

            @Override
            public ProductResponse getProduct(UUID productId) {
                log.error("Fallback triggered for getProduct with ID: {}. Reason: {}",
                        productId, cause.getMessage(), cause);

                // Пробрасываем кастомную ошибку (так как без продукта заказ не сделать)
                throw new CatalogClientUnavailableException("Catalog service is unavailable. Cannot fetch product info.");
            }

            @Override
            public List<ProductResponse> getProducts(List<UUID> ids) {
                log.error("Fallback triggered for batch getProducts for IDs: {}. Reason: {}",
                        ids, cause.getMessage(), cause);

                // Для пакетной валидации заказа лучше вернуть пустой список.
                // Ваш сервис заказов увидит несовпадение размеров коллекций
                // и сам выбросит красивый OrderValidationException, как мы настраивали ранее.
                return Collections.emptyList();
            }
        };
    }
}
