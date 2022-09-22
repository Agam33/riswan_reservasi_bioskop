package com.ra.bioskop.util;

public interface RepoQuery {
    String SCHEDULE_START_TIME_AND_SHOW_AT = "SELECT * FROM schedule s where s.startTime = :startTime AND s.showAt = :showAt";

}
