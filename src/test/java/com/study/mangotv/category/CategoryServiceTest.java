package com.study.mangotv.category;

import com.study.mangotv.MangoTvApiApplication;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@ActiveProfiles(profiles = {"local"})
@SpringBootTest(classes = {MangoTvApiApplication.class})
class CategoryServiceTest {

    @Autowired
    private CategoryService categoryService;

    @Test
    public void 카테고리_전체() {
        categoryService.getAllCategories().forEach(System.out::println);
    }

    @Test
    public void 특정_카테고리_전체() {
        String code = "B";
        categoryService.getCategories(code).forEach(System.out::println);
    }

    @Test
    public void 특정_한글이름이_들어가는_카테고리() {
        String nameKr = "스";
        categoryService.getCategoriesByNameKrContaining(nameKr).forEach(System.out::println);
    }

    @Test
    public void 특정_영어이름이_들어가는_카테고리() {
        String nameEn = "st";
        categoryService.getCategoriesByNameEnContaining(nameEn).forEach(System.out::println);
    }

    @Test
    public void 특정_Depth의_카테고리() {
        Short depth = (short) 1;
        categoryService.getCategoriesByDepth(depth).forEach(System.out::println);
    }
}