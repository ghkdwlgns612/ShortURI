package com.example.demo.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
public class UrlResponseDto {
    private String hashValue;
    private String originUrl;
    private String encodedValue;

    @Builder
    public UrlResponseDto(String hashValue, String originUrl, String encodedValue) {
        this.hashValue = hashValue;
        this.originUrl = originUrl;
        this.encodedValue = encodedValue;
    }
}