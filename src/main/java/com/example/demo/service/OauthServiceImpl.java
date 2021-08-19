package com.example.demo.service;

import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

public class OauthServiceImpl implements OauthService{
    @Override
    public ResponseEntity<String> makeToken(String code) {
        String queryString = ""
                + "grant_type=" + "authorization_code" + "&"
                + "client_id=" + "ca8ebc5cbee6e3047bf84a8dfd51f69d9732484f4f5a91794af7c01bb31ea857" + "&"
                + "client_secret=" + "88554039980701b168e11a21a5c09c1069bfc432122bc672d1a009022b91519f" + "&"
                + "code=" + code + "&"
                + "redirect_uri=" + "http://localhost:8080/login";
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        RestTemplate restTemplate = new RestTemplate();
        HttpEntity entity = new HttpEntity(httpHeaders);
        ResponseEntity<String> response = restTemplate.exchange("https://api.intra.42.fr/oauth/token?" + queryString, HttpMethod.POST, entity,String.class);
        return response;
    }
}
