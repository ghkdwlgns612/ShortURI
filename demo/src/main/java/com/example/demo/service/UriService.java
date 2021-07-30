package com.example.demo.service;

import com.example.demo.controller.dto.UriResponseDto;
import com.example.demo.entity.Uri;
import com.example.demo.repository.UriRepository;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.validator.routines.UrlValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
public class UriService {

    private UriRepository uriRepository;
    private Base62ConverService convertService = new Base62ConverService();

    @Autowired
    public UriService(UriRepository uriRepository) {
        this.uriRepository = uriRepository;
    }

    public UriResponseDto findByChangeUri(String changedUri) throws Exception {
        Optional<Uri> uri = uriRepository.findBychangeduri(changedUri);
        if (uri.isEmpty())
            throw new Exception();
        else
            return UriResponseDto.builder()
                    .seq(uri.get().getSeq())
                    .originUri(uri.get().getOriginuri())
                    .changedUri(uri.get().getChangeduri())
                    .build();
    }

    public UriResponseDto changeUri(String originUri) throws Exception {
        int index = originUri.indexOf("://");
        if (index == -1)
            originUri = "https://" + originUri;
        if (checkUri(originUri) == false)
            throw new Exception();
        Uri uri = Uri.builder().originuri(originUri).changeduri(convertService.change(originUri)).build();
        uriRepository.save(uri);
        return UriResponseDto.builder()
                    .changedUri(uri.getChangeduri())
                    .originUri(uri.getOriginuri())
                    .build();
    }

    private boolean checkUri(String originUri) {
        UrlValidator urlValidator = new UrlValidator();
        return urlValidator.isValid(originUri);
    }
}
