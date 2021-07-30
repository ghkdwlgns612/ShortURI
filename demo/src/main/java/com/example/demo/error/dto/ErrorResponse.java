package com.example.demo.error.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ErrorResponse {
    private String message;
    private int statusCode;
    private String errmsg;
}