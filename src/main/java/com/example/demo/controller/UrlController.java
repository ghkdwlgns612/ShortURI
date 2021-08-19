package com.example.demo.controller;

import com.example.demo.dto.ResultResponseDto;
import com.example.demo.dto.UrlResponseDto;
import com.example.demo.service.OauthService;
import com.example.demo.service.UrlService;
import com.example.demo.service.UrlServiceImpl;
import com.example.demo.utils.MakeDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.ValidationException;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

@Slf4j
@Controller
@CrossOrigin("*")
public class UrlController {

    private UrlService urlService;
    private OauthService oauthService;
    private MakeDto makeDto;

    @Autowired
    public UrlController(UrlService urlService, OauthService oauthService, MakeDto makeDto) {
        this.urlService = urlService;
        this.oauthService = oauthService;
        this.makeDto = makeDto;
    }

    @GetMapping("/sa/{code}")
    public void DirectUrl(@PathVariable String code, HttpServletResponse response) throws Exception {
        UrlResponseDto responseDto = urlService.findByHashValue(code);
        response.sendRedirect(responseDto.getOriginUrl());
    }

    @PostMapping("/general")
    public ResultResponseDto<Object> ChangeUrl(@RequestParam(required = false) String originUrl) throws NoSuchAlgorithmException, ValidationException{
        UrlResponseDto responseDto = urlService.createUrl(originUrl);
        return makeDto.makeResultResponseDto(responseDto);
    }

    @GetMapping("/login")
    public String CodeUrl(@RequestParam String code) {
        log.info(code);
        ResponseEntity<String> response = oauthService.makeToken(code);
        log.info(String.valueOf(response));
        return "login1";
    }

    @PostMapping("/login")
    public ResultResponseDto<Object> ChangeUrlLogin(@RequestParam(required = false) String originUrl,
                                                    @RequestParam(required = true) String name) throws ValidationException, UnsupportedEncodingException, NoSuchAlgorithmException {
        UrlResponseDto responseDto = urlService.createUrlWithLogin(originUrl,name);
        return makeDto.makeResultResponseDto(responseDto);
    }
}