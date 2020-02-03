package com.study.mangotv.persistence.category;

import com.study.mangotv.MangoTvApiApplication;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.*;

@ActiveProfiles(profiles = {"local"})
@SpringBootTest(classes = {MangoTvApiApplication.class})
public class CategoryRepositoryTest {

    @Autowired
    private CategoryRepository categoryRepository;

    @Test
    public void 카테고리_전체_목록() {
        categoryRepository.findAll().forEach(System.out::println);
    }

    @Test
    public void 특정코드로_시작하는_전체_카테고리() {
        String code = "B";
        categoryRepository.findByCodeStartingWith(code).forEach(System.out::println);
    }

    @Test
    public void 한글이름을_포함하는_카테고리() {
        String nameKr = "스";
        categoryRepository.findByNameKrContaining(nameKr)
                .stream()
                .sorted((o1, o2) -> o1.getNameKr().compareTo(o2.getNameKr()))
                .forEach(System.out::println);
    }

    @Test
    public void 영어이름을_포함하는_카테고리() {
        String nameEn = "st";
        categoryRepository.findByNameEnContaining(nameEn)
                .stream()
                .sorted((o1, o2) -> o1.getNameEn().compareTo(o2.getNameEn()))
                .forEach(System.out::println);
    }

    @Test
    public void Depth가_같은_카테고리() {
        Short depth = (short) 1;
        categoryRepository.findByDepth(depth).forEach(System.out::println);
    }
}