package com.example.demo.controller;

import com.example.demo.dto.ResultResponseDto;
import com.example.demo.dto.UrlResponseDto;
import com.example.demo.service.UrlService;
import com.example.demo.utils.MakeDto;
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
    private MakeDto makeDto;

    @Autowired
    public Controller(UrlService urlService, MakeDto makeDto) {
        this.urlService = urlService;
        this.makeDto = makeDto;
    }

    @GetMapping("/{code}")
    public void DirectUrl(@PathVariable String code, HttpServletResponse response) throws Exception {
        UrlResponseDto responseDto = urlService.findByHashValue(code);
        response.sendRedirect(responseDto.getOriginUrl());
    }
    @PostMapping("/general")
    public ResultResponseDto<Object> ChangeUrl(@RequestParam String originUrl) throws NoSuchAlgorithmException, ValidationException {
        UrlResponseDto responseDto = urlService.createUrl(originUrl);
        return makeDto.makeResultResponseDto(responseDto);
    }
    @PostMapping("/login")
    public ResultResponseDto<Object> ChangeUrlLogin(@RequestParam String originUrl) throws ValidationException, UnsupportedEncodingException, NoSuchAlgorithmException {
        UrlResponseDto responseDto = urlService.createUrlWithLogin(originUrl);
        return makeDto.makeResultResponseDto(responseDto);
    }
}