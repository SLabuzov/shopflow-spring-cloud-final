package by.sample.shopflow.order.client;

import feign.Response;
import feign.codec.ErrorDecoder;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CatalogClientErrorDecoder implements ErrorDecoder {

    private final ErrorDecoder defaultDecoder = new Default();

    @Override
    public Exception decode(String methodKey, Response response) {
        log.error("Feign error: method={}, status={}", methodKey, response.status());

        if (response.status() == 404) {
            return new CatalogClientProductNotFoundException(
                    "Product not found in catalog"
            );
        }

        return defaultDecoder.decode(methodKey, response);
    }
}
