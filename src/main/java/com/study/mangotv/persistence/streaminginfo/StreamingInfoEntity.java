package com.study.mangotv.persistence.streaminginfo;

import com.study.mangotv.persistence.category.CategoryEntity;
import com.study.mangotv.persistence.user.UserEntity;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@ToString
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "streaming_info",
        indexes = @Index(name = "title_index", columnList = "title")
)
public class StreamingInfoEntity {
    @Id
    @Column(name = "no", length = 20)
    private String no;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE, targetEntity = UserEntity.class)
    @JoinColumn(name = "streamer_srl", nullable = false)
    private UserEntity userEntity;

    @Column(name = "is_secret", nullable = false)
    private boolean isSecret = false;

    @Column(name = "password")
    private String password;

    @Column(name = "adult_restriction", nullable = false)
    private boolean adultRestriction = false;

    @Column(name = "title", length = 40, nullable = false)
    private String title;

    @Column(name = "bps", length = 10, nullable = false)
    private String bps;

    @Column(name = "resolution", length = 15, nullable = false)
    private String resolution;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.REFRESH, targetEntity = CategoryEntity.class)
    @JoinColumn(name = "category_code", nullable = false)
    private CategoryEntity categoryEntity;

    @Column(name = "platform", nullable = false)
    @Enumerated(EnumType.STRING)
    private StreamingPlatform platform;

    @Column(name = "start_date", nullable = false)
    private LocalDateTime startDate;

    @Builder
    public StreamingInfoEntity(String no, UserEntity userEntity, boolean isSecret,
                               String password, boolean adultRestriction,
                               String title, String bps, String resolution,
                               CategoryEntity categoryEntity, StreamingPlatform platform,
                               LocalDateTime startDate) {
        this.no = no;
        this.userEntity = userEntity;
        this.isSecret = isSecret;
        this.password = password;
        this.adultRestriction = adultRestriction;
        this.title = title;
        this.bps = bps;
        this.resolution = resolution;
        this.categoryEntity = categoryEntity;
        this.platform = platform;
        this.startDate = startDate;
    }
}
