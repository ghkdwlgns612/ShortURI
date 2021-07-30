package com;

import com.example.demo.repository.UriRepository;
import com.example.demo.service.UriService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringConfig {
    private final UriRepository uriRepository;

    @Autowired
    public SpringConfig(UriRepository uriRepository) {
        this.uriRepository = uriRepository;
    }

    public UriService uriService() {
        return new UriService(uriRepository);
    }
}
