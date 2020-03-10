package com.study.mangotv.streaminginfo.model;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class StreamingInfoResponse {
    private String no;
    private String streamerId;
    private String streamerNickname;
    private String title;
    private String bps;
    private String resolution;
    private String thumbnailLink;
    private LocalDateTime stateDate;
    private StreamingViewer streamingViewer;
}
