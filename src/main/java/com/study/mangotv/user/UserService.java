package com.study.mangotv.user;

import com.study.mangotv.common.exception.ApiException;
import com.study.mangotv.common.exception.UserNotFoundException;
import com.study.mangotv.user.model.UserInfoResponse;
import com.study.mangotv.persistence.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    // TODO: 2019-12-03 UserNotFoundException 적용하기 
    public UserInfoResponse getUserInfo(Long srl) {
        return new UserInfoResponse().userEntityToUserInfoResponse(
                userRepository.findById(srl).orElseThrow(RuntimeException::new)
        );
    }
}
