package uri.ShortURI.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import uri.ShortURI.controller.dto.ResultResponseDto;
import uri.ShortURI.controller.dto.UriResponseDto;
import uri.ShortURI.service.UriService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
@Slf4j
public class UriController {

    private final UriService uriService;

    @Autowired
    public UriController(UriService uriService) {
        this.uriService = uriService;
    }

    @GetMapping("/")
    public String Home() {
        return "index.html";
    }

    @ResponseBody
    @CrossOrigin("*")
    @PostMapping("/") //서버에 find에 json을 뿌려주고 프론트 단에서 렌더링
    public ResultResponseDto<Object> ChangeUri(@RequestParam String originuri, Model model, HttpServletResponse response) throws Exception {
        log.info("success");
        UriResponseDto uriResponseDto = uriService.changeUri(originuri);
        model.addAttribute("result", uriResponseDto);
       return ResultResponseDto.builder()
                .statusCode(HttpStatus.OK.value())
                .message(HttpStatus.OK.name())
                .data(uriResponseDto)
                .build();
    }

    @GetMapping("/sa/{code}") //서버에서 바로 리다이렉트
    public void DirectUri(@PathVariable(name = "code") String changeduri,
                          HttpServletResponse response) throws Exception {
        UriResponseDto uriResponseDto = uriService.findByChangeUri(changeduri);
        response.sendRedirect(uriResponseDto.getOriginUri());
    }
}
