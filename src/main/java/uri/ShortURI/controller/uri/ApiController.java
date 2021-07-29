package uri.ShortURI.controller.uri;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import uri.ShortURI.controller.uri.dto.result.ResultResponseDto;
import uri.ShortURI.controller.uri.dto.uri.UriResponsDto;
import uri.ShortURI.service.UriService;

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