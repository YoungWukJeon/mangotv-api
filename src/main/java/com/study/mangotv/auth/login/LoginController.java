package com.study.mangotv.auth.login;

import com.study.mangotv.auth.login.model.LoginRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@RestController
@RequestMapping(value = "/api/v1/auth/login")
public class LoginController {
    @Autowired
    private LoginService loginService;

    @PostMapping(value = "")
    public String login(@RequestBody LoginRequest loginRequest, HttpServletResponse response, HttpSession session) {
        return loginService.login(loginRequest, response, session);
    }
}
