package com.study.mangotv.category.model;

import com.study.mangotv.persistence.category.CategoryEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CategoryResponse {
    private String code;
    private Short depth;
    private Short viewOrder;
    private String iconUrl;
    private String nameKr;
    private String nameEn;

    public CategoryResponse categoryEntityToCategoryResponse(CategoryEntity categoryEntity) {
        return this.builder()
                .code(categoryEntity.getCode())
                .depth(categoryEntity.getDepth())
                .viewOrder(categoryEntity.getViewOrder())
                .iconUrl(categoryEntity.getIconUrl())
                .nameKr(categoryEntity.getNameKr())
                .nameEn(categoryEntity.getNameEn())
                .build();
    }
}
