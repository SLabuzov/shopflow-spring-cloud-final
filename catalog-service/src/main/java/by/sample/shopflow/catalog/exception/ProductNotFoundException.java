package by.sample.shopflow.catalog.exception;

import java.util.UUID;

public class ProductNotFoundException extends ResourceNotFoundException {

    public ProductNotFoundException(UUID id) {
        super("Product", id);
    }
}
