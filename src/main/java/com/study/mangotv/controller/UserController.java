package com.study.mangotv.controller;

import com.study.mangotv.domain.UserEntity;
import com.study.mangotv.domain.UserJpaRepository;
import com.study.mangotv.domain.model.RequestUserSaveDto;
import com.study.mangotv.domain.model.ResponseUserOneDto;
import com.study.mangotv.domain.model.UserInfoDto;
import com.study.mangotv.exception.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/user") 
public class UserController {

    @Autowired
    private UserJpaRepository userJpaRepository;

    @GetMapping("/{srl}")
    public ResponseEntity<UserInfoDto> selectUser(@PathVariable Long srl) throws UserNotFoundException{

        UserEntity userEntity = userJpaRepository.findBySrl(srl);

        if(userEntity == null){
            throw new UserNotFoundException("User Not Found On SRL : " + srl);
        }

        UserInfoDto userInfoDto = new UserInfoDto(userEntity);

        return ResponseEntity.ok().body(userInfoDto);
    }

    @PostMapping("/")
    public ResponseEntity<ResponseUserOneDto> createUser(@RequestBody RequestUserSaveDto requestUserSaveDto) {

        UserEntity userEntity = requestUserSaveDto.toEntity();
        UserEntity savedUserEntity = userJpaRepository.save(userEntity);

        ResponseUserOneDto responseUserOneDto = new ResponseUserOneDto(savedUserEntity);

        return ResponseEntity.ok().body(responseUserOneDto);
    }
    
}