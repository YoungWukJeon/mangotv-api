package com.study.mangotv.auth.login;

import com.study.mangotv.auth.login.model.LoginRequest;
import com.study.mangotv.common.util.PasswordEncoderImpl;
import com.study.mangotv.persistence.user.UserEntity;
import com.study.mangotv.persistence.user.UserRepository;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;

@Service
public class LoginService {
    @Autowired
    private UserRepository userRepository;

    // TODO: 2020-01-02 UserNotFoundException 적용하기
    public String login(LoginRequest loginRequest, HttpServletResponse response, HttpSession session) {
        PasswordEncoder passwordEncoder = new PasswordEncoderImpl();
        UserEntity userEntity = userRepository.findById(loginRequest.getId()).orElseThrow(RuntimeException::new);

        if (!passwordEncoder.matches(loginRequest.getPassword(), userEntity.getPassword())) {
            return "failure";
        }

        userEntity.setLoginDate(LocalDateTime.now());
        userRepository.saveAndFlush(userEntity);

        String code = RandomStringUtils.randomAlphanumeric(8);  // deprecated
        session.setAttribute("uid", code);

        Cookie cookie = new Cookie("USER", this.makeLoginCookie(userEntity));
        response.addCookie(cookie);

        return "success";
    }

    private String makeLoginCookie(UserEntity userEntity) {
        StringBuilder builder = new StringBuilder();
        return builder.append("srl=")
                .append(userEntity.getSrl())
                .append("&id=")
                .append(userEntity.getId())
                .append("&gender=")
                .append(userEntity.getGender())
                .append("&iconUrl=")
                .append(userEntity.getIconUrl())
                .append("&nickname=")
                .append(userEntity.getNickname())
                .append("&loginDate=")
                .append(userEntity.getLoginDate())
                .toString();
    }
}