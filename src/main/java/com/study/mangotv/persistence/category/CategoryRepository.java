package com.study.mangotv.persistence.category;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<CategoryEntity, String> {
    List<CategoryEntity> findByCodeStartingWith(String code);
    List<CategoryEntity> findByNameKrContaining(String nameKr);
    List<CategoryEntity> findByNameEnContaining(String nameEn);
    List<CategoryEntity> findByDepth(Short depth);
}