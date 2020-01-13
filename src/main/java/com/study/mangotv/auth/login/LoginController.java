package com.study.mangotv.auth.login;

import com.study.mangotv.auth.login.model.LoginRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@RestController
@RequestMapping(value = "/api/v1/auth")
public class LoginController {
    @Autowired
    private LoginService loginService;

    @PostMapping(value = "/login", consumes = {"application/json"})
    public String login(@RequestBody LoginRequest loginRequest, HttpSession session) {
        return loginService.login(loginRequest, session);
    }

    @GetMapping(value = "/logout")
    public void logout(HttpSession session) {
        loginService.logout(session);
    }
}
