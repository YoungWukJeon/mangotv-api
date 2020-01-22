package com.study.mangotv.category;

import com.study.mangotv.category.model.CategoryResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/category")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @GetMapping(value = "")
    public List<CategoryResponse> getCategories(
            @RequestParam(required = false) String code,
            @RequestParam(required = false) String nameKr,
            @RequestParam(required = false) String nameEn) {
        System.out.println("gg->" + SecurityContextHolder.getContext().getAuthentication().getAuthorities());
        if (code != null) {
            return categoryService.getCategories(code);
        }

        if (nameKr != null) {
            return categoryService.getCategoriesByNameKrContaining(nameKr);
        }

        if (nameEn != null) {
            return categoryService.getCategoriesByNameEnContaining(nameEn);
        }
        return categoryService.getAllCategories();
    }

    @GetMapping(value = "/depth/{depth}")
    public List<CategoryResponse> getCategoriesByDepth(@PathVariable Short depth) {
        return categoryService.getCategoriesByDepth(depth);
    }
}
