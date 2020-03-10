package com.study.mangotv.persistence.streamingviewernum;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Builder
@Getter
@ToString
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "streaming_viewer_num")
public class StreamingViewerNumEntity {

    @Id
    @Column(name = "streaming_info_no", nullable = false)
    private String streamingInfoNo;

    @Column(name = "mobile_num", nullable = false)
    private int mobileNum = 0;

    @Column(name = "web_num", nullable = false)
    private int webNum = 0;
}
