package com.study.mangotv.user;

import com.study.mangotv.user.model.UserInfoResponse;
import com.study.mangotv.persistence.user.UserRepository;
import com.study.mangotv.user.model.UserRegistrationRequest;
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

    public UserInfoResponse saveUserInfo(UserRegistrationRequest userRegistrationRequest) {
        // TODO: 2019-12-03 사용자 입력 데이터에 대한 Validation 체크 
        
        if (!validateUniqueColumn(userRegistrationRequest)) {   // TODO: 2019-12-03 Unique 데이터에 대한 처리를 위한 정의 필요 
            throw new RuntimeException("validation fail");
        }

        return new UserInfoResponse().userEntityToUserInfoResponse(
                userRepository.save(userRegistrationRequest.toUserEntity())
        );
    }

    private boolean validateUniqueColumn(UserRegistrationRequest userRegistrationRequest) {
        if (userRepository.findById(userRegistrationRequest.getId()).isPresent()) {
            return false;
        }
        if (userRepository.findByNickname(userRegistrationRequest.getNickname()).isPresent()) {
            return false;
        }
        if (userRepository.findByEmail(userRegistrationRequest.getEmail()).isPresent()) {
            return false;
        }
        return true;
    }
}
