package com.example.demo.controller;

import com.example.demo.dto.ResultResponseDto;
import com.example.demo.dto.UrlResponseDto;
import com.example.demo.service.UrlServiceImpl;
import com.example.demo.utils.MakeDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.ValidationException;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

@Slf4j
@RestController
public class Controller {

    private UrlServiceImpl urlServiceImpl;
    private MakeDto makeDto;

    @Autowired
    public Controller(UrlServiceImpl urlServiceImpl, MakeDto makeDto) {
        this.urlServiceImpl = urlServiceImpl;
        this.makeDto = makeDto;
    }

    @GetMapping("/{code}")
    public void DirectUrl(@PathVariable String code, HttpServletResponse response) throws Exception {
        UrlResponseDto responseDto = urlServiceImpl.findByHashValue(code);
        response.sendRedirect(responseDto.getOriginUrl());
    }

    @CrossOrigin("*")
    @PostMapping("/general")
    public ResultResponseDto<Object> ChangeUrl(@RequestParam(required = false) String originUrl) throws NoSuchAlgorithmException, ValidationException{
        UrlResponseDto responseDto = urlServiceImpl.createUrl(originUrl);
        return makeDto.makeResultResponseDto(responseDto);
    }

    @CrossOrigin("*")
    @PostMapping("/login")
    public ResultResponseDto<Object> ChangeUrlLogin(@RequestParam(required = false) String originUrl,
                                                    @RequestParam(required = true) String name) throws ValidationException, UnsupportedEncodingException, NoSuchAlgorithmException {
        UrlResponseDto responseDto = urlServiceImpl.createUrlWithLogin(originUrl,name);
        return makeDto.makeResultResponseDto(responseDto);
    }
}