package com.example.demo.controller;

import com.example.demo.controller.dto.ResultResponseDto;
import com.example.demo.controller.dto.UriResponseDto;
import com.example.demo.service.UriService;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.JsonNode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
@Slf4j
public class UriController {

    private final UriService uriService;

    @Autowired
    public UriController(UriService uriService) {
        this.uriService = uriService;
    }

    @GetMapping("/sa/{code}")
    public void DirectUri(@PathVariable(name = "code") String changeduri,
                          HttpServletResponse response) throws Exception {
        UriResponseDto uriResponseDto = uriService.findByChangeUri(changeduri);
        response.sendRedirect(uriResponseDto.getOriginUri());
    }

    @PostMapping("/find")
    public ResultResponseDto<Object> ChangeUri(@RequestParam String originuri) throws Exception {
        UriResponseDto uriResponseDto = uriService.changeUri(originuri);
        return ResultResponseDto.builder()
                .statusCode(HttpStatus.OK.value())
                .message(HttpStatus.OK.name())
                .data(uriResponseDto)
                .build();
    }


}
