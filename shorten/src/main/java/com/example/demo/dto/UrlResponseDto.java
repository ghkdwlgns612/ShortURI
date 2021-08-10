package com.example.demo.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
public class UrlResponseDto {
    private String hashValue;
    private String originUrl;
    private String extract10Char;

    @Builder
    public UrlResponseDto(String hashValue, String originUrl, String extract10Char) {
        this.hashValue = hashValue;
        this.originUrl = originUrl;
        this.extract10Char = extract10Char;
    }
}