package com.study.mangotv.controller;

import com.study.mangotv.domain.UserEntity;
import com.study.mangotv.domain.UserJpaRepository;
import com.study.mangotv.domain.model.RequestUserDto;
import com.study.mangotv.domain.model.ResponseUserOneDto;
import com.study.mangotv.domain.model.UserInfoDto;
import com.study.mangotv.exception.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/user")
@Transactional
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
    public ResponseEntity<ResponseUserOneDto> createUser(@RequestBody RequestUserDto requestUserDto) {

        UserEntity userEntity = requestUserDto.toEntity();
        UserEntity savedUserEntity = userJpaRepository.save(userEntity);

        ResponseUserOneDto responseUserOneDto = new ResponseUserOneDto(savedUserEntity);

        return ResponseEntity.ok().body(responseUserOneDto);
    }

    @PatchMapping("/{srl}")
    public ResponseEntity<ResponseUserOneDto> updateUser(@PathVariable Long srl,
                                                         @RequestBody RequestUserDto requestUserDto){

        UserEntity foundUser = userJpaRepository.findBySrl(srl);

        if(foundUser == null){
            throw new UserNotFoundException("User Not Found ON SRL : " + srl);
        }

        /** TODO - User 업데이트 처리 어떻게?  **/

        return null;
    }

    @DeleteMapping("/{srl}")
    public ResponseEntity<?> deleteUser(@PathVariable Long srl){

        userJpaRepository.deleteBySrl(srl);

        /**  TODO - Delete 성공여부 및 srl 이 존재하지 않는다면 어떻게? **/

        return ResponseEntity.ok().body("Delete Success.");
    }
}