package com.example.demo.service;

import com.example.demo.dto.UrlResponseDto;

import javax.xml.bind.ValidationException;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

public interface UrlService {
     UrlResponseDto findByHashValue(String encodedValue) throws Exception;
     String checkUrl(String originUrl) throws ValidationException;
     UrlResponseDto createUrlWithLogin(String originUrl, String name) throws NoSuchAlgorithmException, ValidationException, UnsupportedEncodingException;
}
