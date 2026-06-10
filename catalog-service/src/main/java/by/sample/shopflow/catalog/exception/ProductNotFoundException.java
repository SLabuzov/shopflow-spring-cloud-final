package by.sample.shopflow.catalog.exception;

import by.sample.shopflow.common.exception.ResourceNotFoundException;

import java.util.UUID;

public class ProductNotFoundException extends ResourceNotFoundException {

    public ProductNotFoundException(UUID id) {
        super("Product", id);
    }
}
