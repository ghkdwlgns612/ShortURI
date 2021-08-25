package com.example.demo.mock;

public class ProductService {

    public Product getProduct() {
        return new Product("desk",15000);
    }

    public Product getProduct(String name, Integer price) {
        return new Product(name,price);
    }
}
