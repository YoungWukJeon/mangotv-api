package com.study.mangotv.user;

import com.study.mangotv.user.model.UserInfoResponse;
import com.study.mangotv.user.model.UserRegistrationRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping(value = "/{srl}")
    public UserInfoResponse getUserInfo(
            @PathVariable Long srl) {
        return userService.getUserInfo(srl);
    }

    @PostMapping(value = "", consumes = {"application/json"})
    public UserInfoResponse saveUserInfo(@RequestBody UserRegistrationRequest userRegistrationRequest) {
        return userService.saveUserInfo(userRegistrationRequest);
    }
}