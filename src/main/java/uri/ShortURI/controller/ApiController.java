package uri.ShortURI.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponents;
import uri.ShortURI.controller.dto.ResultResponseDto;
import uri.ShortURI.domain.TestObject;
import uri.ShortURI.domain.UriResponsDto;
import uri.ShortURI.repository.UriRepository;
import uri.ShortURI.service.UriService;

import javax.servlet.http.HttpServletRequest;

@RestController
public class ApiController {

    private final UriService uriService;

    @Autowired
    public ApiController(UriService uriService) {
        this.uriService = uriService;
    }

    @GetMapping("/find")
    public ResultResponseDto SearchUriAll(@RequestParam String uri) {
        UriResponsDto uriResponsDto = uriService.findByOrigin(uri);
        return ResultResponseDto.builder()
                .statusCode(HttpStatus.OK.value())
                .message(HttpStatus.OK.name())
                .data(uriResponsDto)
                .build();
    }

    @PostMapping("/change")
    public ResultResponseDto SavaUri(@RequestParam String uri) {
        UriResponsDto uriResponsDto = uriService.save(uri);
        return ResultResponseDto.builder()
                .statusCode(HttpStatus.OK.value())
                .message(HttpStatus.OK.name())
                .data(uriResponsDto)
                .build();
    }
}