package com.example.demo.mock;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class SpyCreateTest {
    @Spy
    ProductService productService;

    @Test
    void createSpyNoWithStubbing() {
        Product product = productService.getProduct();
        Assertions.assertThat(productService.getProduct().getName()).isEqualTo(product.getName());
    }

    @Test
    void createSpyWithStubbing() {
        Product product = new Product("chair",20000);
        when(productService.getProduct()).thenReturn(product);
        Assertions.assertThat(productService.getProduct()).isNotEqualTo(product);
    }

    @Test
    void createInjectionMockNoWithStubbing() {

    }

    @Test
    void createInjectionMockWithStubbing() {

    }
}
