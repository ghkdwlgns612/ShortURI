package com.example.demo.mock;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class MockCreateTest {

    @Mock
    ProductService productService;

    @Test
    void createMockNoWithStubbing() {
        Assertions.assertNull(productService.getProduct());
    }

    @Test
    void createMockWithStubbing() {
        Product product = new Product("desk",15000);
        when(productService.getProduct()).thenReturn(product);
        org.assertj.core.api.Assertions.assertThat(productService.getProduct()).isEqualTo(product);
    }

}
