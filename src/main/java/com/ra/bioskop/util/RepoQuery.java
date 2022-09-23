package com.ra.bioskop.util;

public class RepoQuery {
    public static final String SCHEDULE_START_TIME_AND_SHOW_AT = "SELECT * FROM schedule s WHERE s.startTime = :startTime AND s.showAt = :showAt";
}
