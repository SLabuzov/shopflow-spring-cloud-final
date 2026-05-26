package by.sample.shopflow.order.client;

public class CatalogClientProductNotFoundException extends RuntimeException {

    public CatalogClientProductNotFoundException(String message) {
        super(message);
    }
}
