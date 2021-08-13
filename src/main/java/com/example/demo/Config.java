package com.example.demo;

import com.example.demo.repository.UrlRepository;
import com.example.demo.service.UrlCheckService;
import com.example.demo.service.UrlService;
import com.example.demo.utils.MakeDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Config {
    private UrlRepository urlRepository;

    @Autowired
    public Config(UrlRepository urlRepository) {
        this.urlRepository = urlRepository;
    }

    @Bean
    public UrlService urlService() {
        return new UrlService(urlRepository,urlCheckService(),makeDto());
    }

    @Bean
    public UrlCheckService urlCheckService() {
        return new UrlCheckService(makeDto());
    }

    @Bean
    public MakeDto makeDto() {
        return new MakeDto();
    }
}
