package com.study.mangotv.user;

import com.study.mangotv.user.exception.UserNotFoundException;
import com.study.mangotv.user.model.UserInfoResponse;
import com.study.mangotv.user.persistence.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public UserInfoResponse getUserInfo(Long srl) {
        return new UserInfoResponse().userEntityToUserInfoResponse(
                userRepository.findById(srl).orElseThrow(UserNotFoundException::new)
        );
    }
}