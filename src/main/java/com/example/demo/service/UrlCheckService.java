package com.example.demo.service;

import com.example.demo.domain.Url;
import com.example.demo.dto.UrlResponseDto;
import com.example.demo.utils.MakeDto;
import javassist.NotFoundException;
import org.apache.commons.validator.routines.UrlValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.bind.ValidationException;
import java.util.Optional;

@Service
public class UrlCheckService {
    private MakeDto makeDto;

    @Autowired
    public UrlCheckService(MakeDto makeDto) {
        this.makeDto = makeDto;
    }

    /**
     * OriginUrl이 알맞은 형식으로 들어왔는지 확인하는 로직
     * **/
    public String checkUrl(String originUrl) throws ValidationException {
        originUrl = originUrl.toLowerCase();
        int index = originUrl.indexOf("://"); //앞Q
        String changedUrl = "";
        if (index == -1)
            originUrl = "https://" + originUrl;
        if (validateUrl(originUrl) == false) //SCHEME없으면 인증이 안되기 때문에 앞에서 확인해줌.
            throw new ValidationException(originUrl);
        return originUrl;
    }

    private boolean validateUrl(String originUrl) {
        UrlValidator urlValidator = new UrlValidator();
        return urlValidator.isValid(originUrl);
    }

    /**
     * 객체가 Null인지 아닌지 확인하는 함수. find에서 찾은 것을 가져와 dto building.
     */
    public UrlResponseDto isEmpty(String encodedValue, Optional<Url> result) throws NotFoundException {
        if (result.isEmpty())
            throw new NotFoundException(encodedValue);
        else
            return makeDto.makeUrlResponseDto(result.get().getOriginurl(),result.get().getHashvalue(),encodedValue);
    }
}
