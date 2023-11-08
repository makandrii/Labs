package org.makarov.lab7.exceptions;

public class ProductNotFoundException extends RuntimeException {
    public ProductNotFoundException(Integer productId) {
        super(String.format("Product id:%d not found", productId));
    }
}
