package com.example.demo.mock;

import javassist.NotFoundException;
import org.aspectj.weaver.ast.Not;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.stubbing.Answer;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
public class MockTest {

    @Mock
    ProductService productService;

    @Test
    void mockThenReturnTest() {
        Product product = new Product("desk",15000);
        when(productService.getProduct()).thenReturn(product);
        assertThat(productService.getProduct()).isEqualTo(product);
    }

    @Test
    void mockThenThrowTest() {
        when(productService.getProduct()).thenThrow(new IllegalAccessError());
        assertThatThrownBy(() -> productService.getProduct()).isInstanceOf(IllegalAccessError.class);
    }

    @Test
    void mockThenAnswerTest() {
        Product product = new Product("desk",15000);
        when(productService.getProduct()).thenAnswer(invocation -> {
                    System.out.println("MockTest.mockThenAnswerTest");
                    return product;
            }
        );
        assertThat(productService.getProduct()).isEqualTo(product);
    }

    @Test
    void mockDoReturnTest() {
        Product product = new Product("desk",15000);
        doReturn(product).when(productService).getProduct(); //productService객체의 getProduct()를 호출 시 product리턴
        assertThat(productService.getProduct()).isEqualTo(product);
    }
}
