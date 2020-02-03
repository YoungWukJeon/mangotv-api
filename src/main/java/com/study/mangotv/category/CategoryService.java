package com.study.mangotv.category;

import com.study.mangotv.category.model.CategoryResponse;
import com.study.mangotv.persistence.category.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;

    public List<CategoryResponse> getAllCategories() {
        return categoryRepository.findAll()
                .stream()
                .map(entity -> new CategoryResponse().categoryEntityToCategoryResponse(entity))
                .collect(Collectors.toList());
    }

    public List<CategoryResponse> getCategories(String code) {
        return categoryRepository.findByCodeStartingWith(code)
                .stream()
                .map(entity -> new CategoryResponse().categoryEntityToCategoryResponse(entity))
                .collect(Collectors.toList());
    }

    public List<CategoryResponse> getCategoriesByNameKrContaining(String nameKr) {
        return categoryRepository.findByNameKrContaining(nameKr)
                .stream()
                .map(entity -> new CategoryResponse().categoryEntityToCategoryResponse(entity))
                .collect(Collectors.toList());
    }

    public List<CategoryResponse> getCategoriesByNameEnContaining(String nameEn) {
        return categoryRepository.findByNameEnContaining(nameEn)
                .stream()
                .map(entity -> new CategoryResponse().categoryEntityToCategoryResponse(entity))
                .collect(Collectors.toList());
    }

    public List<CategoryResponse> getCategoriesByDepth(Short depth) {
        return categoryRepository.findByDepth(depth)
                .stream()
                .map(entity -> new CategoryResponse().categoryEntityToCategoryResponse(entity))
                .collect(Collectors.toList());
    }
}
