package com.example.demo.service;

import com.example.demo.dto.NameImgModel;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.http.ResponseEntity;

public interface OauthService {
    public ResponseEntity<String> makeToken(String code);
    public NameImgModel getModel(String accessToken) throws JsonProcessingException;
}
