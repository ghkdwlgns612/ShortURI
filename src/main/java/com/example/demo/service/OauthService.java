package com.example.demo.service;

import org.springframework.http.ResponseEntity;

public interface OauthService {
    public ResponseEntity<String> makeToken(String code);
}
