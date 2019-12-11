package com.study.mangotv.common.util;

import java.time.Duration;
import java.time.LocalDateTime;

public class DateTimeUtil {
    public long timeGapToSeconds(LocalDateTime dateTime, LocalDateTime baseDateTime) {
        return Duration.between(dateTime, baseDateTime).getSeconds();
    }
}
