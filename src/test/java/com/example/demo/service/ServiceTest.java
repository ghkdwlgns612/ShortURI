package com.example.demo.service;

import com.example.demo.controller.UrlController;
import com.example.demo.dto.UrlResponseDto;
import com.example.demo.repository.UrlRepository;
import com.example.demo.utils.Base62Converter;
import com.example.demo.utils.MakeDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.ResultHandler;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.ValidationException;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@SpringBootTest
public class ServiceTest {

    static UrlRepository urlRepository;
    ObjectMapper objectMapper = new ObjectMapper();
    Base62Converter base62Converter = new Base62Converter();
    OauthService oauthService = new OauthServiceImpl(urlRepository,objectMapper,base62Converter);
    MakeDto makeDto = new MakeDto();
    UrlCheckService urlCheckService = new UrlCheckService(makeDto);
    UrlServiceImpl urlService = new UrlServiceImpl(urlRepository,urlCheckService,makeDto,base62Converter);

    @Test
    void urlCheck() throws Exception {
        String ans_true = urlCheckService.checkUrl("naver.com");
        ValidationException e = Assertions.assertThrows(ValidationException.class,
                () -> urlCheckService.checkUrl("naverxx"));
    }

    @Test
    void getModelTest() {

    }
}
