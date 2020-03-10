package com.study.mangotv.persistence.streamingviewernum;

import com.study.mangotv.MangoTvApiApplication;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ActiveProfiles(profiles = {"local"})
@SpringBootTest(classes = {MangoTvApiApplication.class})
class StreamingViewerNumRepositoryTest {

    @Autowired
    private StreamingViewerNumRepository streamingViewerNumRepository;

    @Test
    public void 시청인원조회() {
        streamingViewerNumRepository.findAll().forEach(System.out::println);
    }

    @Transactional
    @Test
    public void 특정_방송_시청인원조회() {
        String streamingInfoNo = "NKR62fP9gAm56rmnL6JL";
        StreamingViewerNumEntity streamingViewerNumEntity = streamingViewerNumRepository.findById(streamingInfoNo)
                .orElse(StreamingViewerNumEntity.builder()
                        .streamingInfoNo("NOT MATCH")
                        .build()
                );
        System.out.println(streamingViewerNumEntity);
    }

    @Transactional
    @Test
    public void 만개_데이터_합계_후_정렬_시간() {
        List<StreamingViewerNumEntity> streamingViewerNumEntities = new ArrayList<> ();

        for (int i = 0; i < 10000; ++i) {
            int mobileNum = (int) (Math.random() * 10000);
            int webNum = (int) (Math.random() * 10000);

            streamingViewerNumEntities.add(StreamingViewerNumEntity.builder()
                    .streamingInfoNo("TEST" + i)
                    .mobileNum(mobileNum)
                    .webNum(webNum)
                    .build());
        }
        System.out.println("*********START*********");
        long start = System.currentTimeMillis();
        streamingViewerNumEntities.stream()
                .sorted((a, b) -> (a.getMobileNum() + a.getWebNum() > b.getMobileNum() + b.getWebNum())? -1: 1)
                .limit(5)
                .forEach(System.out::println);
        long end = System.currentTimeMillis();
        System.out.println("**********END**********");
        System.out.println((end - start) + "ms takes");
    }
}