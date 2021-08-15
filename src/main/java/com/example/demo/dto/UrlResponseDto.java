package com.example.demo.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
public class UrlResponseDto {
    private String hashValue;
    private String originUrl;
    private String encodedValue;
    private String name;

    @Builder
    public UrlResponseDto(String hashValue, String originUrl, String encodedValue, String name) {
        this.hashValue = hashValue;
        this.originUrl = originUrl;
        this.encodedValue = encodedValue;
        this.name = name;
    }
}