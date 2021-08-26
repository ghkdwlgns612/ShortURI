package com.example.demo.service;

import com.example.demo.controller.UrlController;
import com.example.demo.repository.UrlRepository;
import com.example.demo.utils.Base62Converter;
import com.example.demo.utils.MakeDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.ResultHandler;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@ExtendWith(MockitoExtension.class)
@WebMvcTest(UrlController.class)
public class OuathServiceTest {

    private static MockMvc mockMvc;

    static UrlRepository urlRepository;
    @Mock
    static
    ObjectMapper objectMapper = new ObjectMapper();
    @Mock
    static
    Base62Converter base62Converter = new Base62Converter();
    @Mock
    static
    OauthService oauthService = new OauthServiceImpl(urlRepository,objectMapper,base62Converter);
    @Mock
    static
    MakeDto makeDto = new MakeDto();
    @Mock
    static
    UrlCheckService urlCheckService = new UrlCheckService(makeDto);
    @Mock
    static
    UrlService urlService = new UrlServiceImpl(urlRepository,urlCheckService,makeDto,base62Converter);
    @BeforeAll
    public static void setUpMockMvc() {
        mockMvc = MockMvcBuilders.standaloneSetup(new UrlController(urlService,oauthService,makeDto,objectMapper)).build();
    }
    @Test
    void makeTokenTest() throws Exception {
    
    }

    @Test
    void getModelTest() {

    }
    private ResultHandler print() {
        return null;
    }
}
