package com.study.mangotv.user;

import com.study.mangotv.user.model.UserInfoResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/{srl}")
    public UserInfoResponse getUserInfo(@PathVariable Long srl) {
        return userService.getUserInfo(srl);
    }
}