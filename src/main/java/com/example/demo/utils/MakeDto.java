package com.example.demo.utils;

import com.example.demo.dto.ResultResponseDto;
import com.example.demo.dto.UrlResponseDto;
import org.springframework.http.HttpStatus;

public class MakeDto {

    public ResultResponseDto<Object> makeResultResponseDto(UrlResponseDto responseDto) {
        return ResultResponseDto.builder()
                .data(responseDto)
                .message(HttpStatus.OK.name())
                .statusCode(HttpStatus.OK.value())
                .build();
    }
    public UrlResponseDto makeUrlResponseDto(String originUrl, String extract10Char, String encodedStr,String name) {
        return UrlResponseDto.builder()
                .originUrl(originUrl)
                .encodedValue(encodedStr)
                .hashValue(extract10Char)
                .name(name)
                .build();
    }

    public UrlResponseDto makeUrlResponseDto(String originUrl, String extract10Char, String encodedStr) {
        return UrlResponseDto.builder()
                .originUrl(originUrl)
                .encodedValue(encodedStr)
                .hashValue(extract10Char)
                .build();
    }

}
