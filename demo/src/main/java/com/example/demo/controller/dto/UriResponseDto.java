package com.example.demo.controller.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
public class UriResponseDto {
    private Long seq;
    private String originUri;
    private String changedUri;

    @Builder
    public UriResponseDto(Long seq, String originUri, String changedUri) {
        this.seq = seq;
        this.originUri = originUri;
        this.changedUri = changedUri;
    }
}
