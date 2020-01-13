package com.study.mangotv.auth.login;

import com.study.mangotv.auth.login.model.LoginRequest;
import com.study.mangotv.common.model.LoginSessionData;
import com.study.mangotv.common.util.PasswordEncoderImpl;
import com.study.mangotv.persistence.user.UserEntity;
import com.study.mangotv.persistence.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;

@Service
public class LoginService {
    @Autowired
    private UserRepository userRepository;

    // TODO: 2020-01-02 UserNotFoundException 적용하기
    public String login(LoginRequest loginRequest, HttpSession session) {
        PasswordEncoder passwordEncoder = new PasswordEncoderImpl();
        UserEntity userEntity = userRepository.findById(loginRequest.getId()).orElseThrow(RuntimeException::new);

        if (!passwordEncoder.matches(loginRequest.getPassword(), userEntity.getPassword())) {
            return "failure";
        }

        userEntity.setLoginDate(LocalDateTime.now());
        userRepository.saveAndFlush(userEntity);

        session.setAttribute("loginSessionData", LoginSessionData.builder().userEntity(userEntity).build());

        return "success";
    }

    public void logout(HttpSession session) {
        session.invalidate();
    }
}