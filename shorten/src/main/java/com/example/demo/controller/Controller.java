package com.example.demo.controller;

import com.example.demo.dto.ResultResponseDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
public class Controller {
    @GetMapping("/{code}")
    public void DirectUrl(@PathVariable String code) {
        //서비스에서 찾아서 리다이렉트
    }

    @PostMapping("/general")
    public ResultResponseDto<Object> ChangeUrl(@RequestParam String originUrl) {
        //Id를 Base62이용하여 바꾸고 바로저장.
        return null;
    }

    @PostMapping("/login")
    public ResultResponseDto<Object> ChangeUrlLogin() {
        //OriginUrl을 UTF-8 -> SHA512 -> Base62로 바꾸고 중복확인 후 저장.
        return null;
    }
}
