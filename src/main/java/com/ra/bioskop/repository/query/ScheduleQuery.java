package com.ra.bioskop.repository.query;

public interface ScheduleQuery {

    String SCHEDULE_DETAIL_BY_DATE = "SELECT " +
            "new com.ra.bioskop.dto.model.film.DetailScheduleDTO(f.title, st.name, s.startTime, s.endTime, s.showAt, s.price) "+
            "FROM Schedule AS s JOIN s.film AS f "+
            "JOIN s.studio AS st "+
            "WHERE s.showAt = :showAt";
}
