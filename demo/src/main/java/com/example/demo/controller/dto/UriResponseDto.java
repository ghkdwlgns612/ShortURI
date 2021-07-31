package com.example.demo.controller.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
public class UriResponseDto {
    private String originUri;
    private String changedUri;

    @Builder
    public UriResponseDto(String originUri, String changedUri) {
        this.originUri = originUri;
        this.changedUri = changedUri;
    }
}
