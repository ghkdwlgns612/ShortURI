package com.example.demo.controller;

import com.example.demo.dto.NameImgModel;
import com.example.demo.dto.ResultResponseDto;
import com.example.demo.dto.TokenDto;
import com.example.demo.dto.UrlResponseDto;
import com.example.demo.service.OauthService;
import com.example.demo.service.UrlService;
import com.example.demo.utils.MakeDto;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
    ObjectMapper objectMapper = new ObjectMapper();
    TokenDto tokenDto = new TokenDto();

    @Autowired
    public UrlController(UrlService urlService, OauthService oauthService, MakeDto makeDto) {
        this.urlService = urlService;
        this.oauthService = oauthService;
        this.makeDto = makeDto;
    }

    @GetMapping("/sa/{code}")
    public void DirectUrl(@PathVariable String code, HttpServletResponse response) throws Exception {
        log.info("code = {}", code);
        UrlResponseDto responseDto = urlService.findByHashValue(code);
        response.sendRedirect(responseDto.getOriginUrl());
    }

    @PostMapping("/general")
    @ResponseBody
    public ResultResponseDto<Object> ChangeUrl(@RequestParam(required = false) String originUrl) throws NoSuchAlgorithmException, ValidationException{
        UrlResponseDto responseDto = urlService.createUrl(originUrl);
        return makeDto.makeResultResponseDto(responseDto);
    }

    @GetMapping("/login")
    public String CodeUrl(@RequestParam String code, Model model) throws JsonProcessingException {
        ResponseEntity<String> response = oauthService.makeToken(code);
        tokenDto = objectMapper.readValue(response.getBody(), TokenDto.class);
        NameImgModel nameImgModel = oauthService.getModel(tokenDto.getAccess_token(), model);
        return "login";
    }

    @PostMapping("/login")
    @ResponseBody
    public ResultResponseDto<Object> ChangeUrlLogin(@RequestParam(required = false) String originUrl,
                                                    @RequestParam(required = true) String name) throws ValidationException, UnsupportedEncodingException, NoSuchAlgorithmException {
        UrlResponseDto responseDto = urlService.createUrlWithLogin(originUrl,name);
        return makeDto.makeResultResponseDto(responseDto);
    }
}