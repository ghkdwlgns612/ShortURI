package com.example.demo.controller;

import com.example.demo.dto.ResultResponseDto;
import com.example.demo.dto.UrlResponseDto;
import com.example.demo.service.UrlService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.ValidationException;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

@Slf4j
@RestController
public class Controller {

    private UrlService urlService;

    @Autowired
    public Controller(UrlService urlService) {
        this.urlService = urlService;
    }

    @GetMapping("/{code}")
    public void DirectUrl(@PathVariable String code, HttpServletResponse response) throws Exception {
        //서비스에서 찾아서 리다이렉트
        UrlResponseDto responseDto = urlService.findByHashValue(code);
        response.sendRedirect(responseDto.getOriginUrl());
    }

    @PostMapping("/general")
    public ResultResponseDto<Object> ChangeUrl(@RequestParam String originUrl) throws NoSuchAlgorithmException, ValidationException {
        //Id를 Base62이용하여 바꾸고 바로저장.
        UrlResponseDto responseDto = urlService.createUrl(originUrl);
        return ResultResponseDto.builder()
                .data(responseDto)
                .message(HttpStatus.OK.name())
                .statusCode(HttpStatus.OK.value())
                .build();
    }

    @PostMapping("/login")
    public ResultResponseDto<Object> ChangeUrlLogin(@RequestParam String originUrl) throws ValidationException, UnsupportedEncodingException, NoSuchAlgorithmException {
        //OriginUrl을 UTF-8 -> SHA512 -> Base62로 바꾸고 중복확인 후 저장.
        UrlResponseDto responseDto = urlService.createUrlWithLogin(originUrl);
        return ResultResponseDto.builder()
                .data(responseDto)
                .message(HttpStatus.OK.name())
                .statusCode(HttpStatus.OK.value())
                .build();
    }
}