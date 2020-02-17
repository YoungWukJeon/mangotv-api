package com.study.mangotv.persistence.streaminginfo;

import com.study.mangotv.MangoTvApiApplication;
import com.study.mangotv.persistence.category.CategoryEntity;
import com.study.mangotv.persistence.category.CategoryRepository;
import com.study.mangotv.persistence.user.UserEntity;
import com.study.mangotv.persistence.user.UserRepository;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

@ActiveProfiles(profiles = {"local"})
@SpringBootTest(classes = {MangoTvApiApplication.class})
class StreamingInfoRepositoryTest {
    @Autowired
    private StreamingInfoRepository streamingInfoRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private CategoryRepository categoryRepository;

    @Transactional
    @Test
    public void 방송목록조회() {
        streamingInfoRepository.findAll().forEach(System.out::println);
    }

    @Test
    public void 특정제목이_포함된_방송목록조회() {
        String title = "테스트";
        streamingInfoRepository.findByTitleContaining(title).forEach(System.out::println);
    }

    @Transactional
    @Test
    public void 방송추가_기본() {
        long streamerSrl = 1L;
        String categoryCode = "C01";

        UserEntity userEntity = userRepository.findById(streamerSrl).orElseThrow(RuntimeException::new);
        CategoryEntity categoryEntity = categoryRepository.findById(categoryCode).orElseThrow(RuntimeException::new);

        streamingInfoRepository.save(
                StreamingInfoEntity.builder()
                        .no(RandomStringUtils.randomAlphanumeric(20))
                        .userEntity(userEntity)
                        .title("")
                        .bps("")
                        .resolution("")
                        .categoryEntity(categoryEntity)
                        .platform(StreamingPlatform.PC)
                        .startDate(LocalDateTime.now())
                        .build()
        );
    }

    @Transactional
    @Test
    public void 방송추가_비번방() {
        long streamerSrl = 1L;
        String categoryCode = "C01";

        UserEntity userEntity = userRepository.findById(streamerSrl).orElseThrow(RuntimeException::new);
        CategoryEntity categoryEntity = categoryRepository.findById(categoryCode).orElseThrow(RuntimeException::new);

        streamingInfoRepository.save(
                StreamingInfoEntity.builder()
                        .no(RandomStringUtils.randomAlphanumeric(20))
                        .userEntity(userEntity)
                        .isSecret(true)
                        .password("asd")
                        .title("")
                        .bps("")
                        .resolution("")
                        .categoryEntity(categoryEntity)
                        .platform(StreamingPlatform.PC)
                        .startDate(LocalDateTime.now())
                        .build()
        );
    }

    @Transactional
    @Test
    public void 방송추가_19방송() {
        long streamerSrl = 1L;
        String categoryCode = "C01";

        UserEntity userEntity = userRepository.findById(streamerSrl).orElseThrow(RuntimeException::new);
        CategoryEntity categoryEntity = categoryRepository.findById(categoryCode).orElseThrow(RuntimeException::new);

        streamingInfoRepository.save(
                StreamingInfoEntity.builder()
                        .no(RandomStringUtils.randomAlphanumeric(20))
                        .userEntity(userEntity)
                        .adultRestriction(true)
                        .title("")
                        .bps("")
                        .resolution("")
                        .categoryEntity(categoryEntity)
                        .platform(StreamingPlatform.PC)
                        .startDate(LocalDateTime.now())
                        .build()
        );
    }
}