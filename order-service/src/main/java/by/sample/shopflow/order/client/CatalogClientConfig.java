package by.sample.shopflow.order.client;

import feign.codec.ErrorDecoder;
import org.springframework.context.annotation.Bean;

/**
 * Конфигурация для обработки ошибок в Feign‑клиенте каталога.
 * <p>
 * Определяет кастомный ErrorDecoder ({@link CatalogClientErrorDecoder}), который интерпретирует
 * HTTP‑ошибки (например, 4xx / 5xx) от catalog-service и преобразует их в доменные исключения,
 * а не в стандартные Feign‑исключения.
 */
public class CatalogClientConfig {

    /**
     * Возвращает кастомный декодер ошибок для Feign‑клиента каталога.
     *
     * @return экземпляр {@link CatalogClientErrorDecoder}, который используется для декодирования
     *         HTTP‑ошибок от catalog-service в прикладные исключения.
     */
    @Bean
    public ErrorDecoder errorDecoder() {
        return new CatalogClientErrorDecoder();
    }
}
