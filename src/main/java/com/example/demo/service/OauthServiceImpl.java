package com.example.demo.service;

import com.example.demo.domain.Url;
import com.example.demo.dto.NameImgModel;
import com.example.demo.repository.UrlRepository;
import com.example.demo.utils.Base62Converter;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Slf4j
@Service
public class OauthServiceImpl implements OauthService{

    private final UrlRepository urlRepository;
    Base62Converter base62Converter = new Base62Converter();
    NameImgModel nameImgModel = new NameImgModel();
    ObjectMapper objectMapper = new ObjectMapper();

    @Autowired
    public OauthServiceImpl(UrlRepository urlRepository) {
        this.urlRepository = urlRepository;
    }

    @Override
    public ResponseEntity<String> makeToken(String code) {
        String queryString = makeQureyForAccessToken(code);
        HttpEntity entity = getHttpHeader();
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response = restTemplate.exchange("https://api.intra.42.fr/oauth/token?" + queryString, HttpMethod.POST, entity,String.class);
        return response;
    }

    @Override
    public NameImgModel getModel(String accessToken, Model model) throws JsonProcessingException {
        String queryString = "https://api.intra.42.fr/v2/me?access_token=" + accessToken;
        HttpEntity entity = getHttpHeader();
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response = restTemplate.exchange(queryString, HttpMethod.GET, entity,String.class);
        nameImgModel = objectMapper.readValue(response.getBody(), NameImgModel.class);
        List<Url> urls = urlRepository.findAllUrlByName(nameImgModel.getLogin());
        String[] res = encodingUrl(urls);
        setModel(model, urls,res);
        return nameImgModel;
     }

    private String[] encodingUrl(List<Url> urls) {
        String[] ret = new String[urls.size()];
        for(int i=0;i<urls.size();i++) {
            String hashvalue = urls.get(i).getHashvalue();
            ret[i] = base62Converter.encoding(hashvalue);
        }
        return ret;
    }

    private void setModel(Model model, List<Url> urls,String[] res) {
        model.addAttribute("name",nameImgModel.getLogin());
        model.addAttribute("image",nameImgModel.getImage_url());
        model.addAttribute("urls", urls);
        model.addAttribute("encoding",res);
    }

    private HttpEntity getHttpHeader() {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity entity = new HttpEntity(httpHeaders);
        return entity;
    }

    private String makeQureyForAccessToken(String code) {
        String queryString = ""
                + "grant_type=" + "authorization_code" + "&"
                + "client_id=" + "ca8ebc5cbee6e3047bf84a8dfd51f69d9732484f4f5a91794af7c01bb31ea857" + "&"
                + "client_secret=" + "88554039980701b168e11a21a5c09c1069bfc432122bc672d1a009022b91519f" + "&"
                + "code=" + code + "&"
                + "redirect_uri=" + "http://localhost:8080/login";
        return queryString;
    }
}
