package com.study.mangotv.persistence.category;

import lombok.Getter;
import lombok.ToString;

import javax.persistence.*;

@Getter
@ToString
@Entity
@Table(name = "category",
        indexes = {
            @Index(name = "name_kr_index", columnList = "name_kr"),
            @Index(name = "name_en_index", columnList = "name_en")
        }
)
public class CategoryEntity {
    @Id
    @Column(name = "code", length = 9)
    private String code;

    @Column(name = "depth", nullable = false)
    private Short depth;

    @Column(name = "view_order")
    private Short viewOrder = 999;

    @Column(name = "icon_url")
    private String iconUrl;

    @Column(name = "name_kr", length = 20, nullable = false)
    private String nameKr;

    @Column(name = "name_en", length = 40, nullable = false)
    private String nameEn;
}
