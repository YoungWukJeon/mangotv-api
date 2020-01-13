package com.study.mangotv.auth.emailauthenticationcode;

import com.study.mangotv.common.model.CodeMessageResponse;
import com.study.mangotv.auth.emailauthenticationcode.model.EmailAuthenticationCodeRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/v1/auth/email_authentication_code")
public class EmailAuthenticationCodeController {
    @Autowired
    private EmailAuthenticationCodeService emailAuthenticationCodeService;

    @PostMapping(value = "", consumes = {"application/json"})
    public CodeMessageResponse generateCode(@RequestBody EmailAuthenticationCodeRequest emailAuthenticationCodeRequest) {
        return emailAuthenticationCodeService.generateCode(emailAuthenticationCodeRequest);
    }

    @GetMapping("/{email:.+}/")
    public CodeMessageResponse matchCode(@PathVariable(name = "email") String email, String code) {
        return emailAuthenticationCodeService.matchCode(email, code);
    }
}
