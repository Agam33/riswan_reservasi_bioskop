package com.ra.bioskop.repository.query;

public interface ScheduleQuery {

//    String SCHEDULE_DETAIL_BY_DATE = "SELECT " +
//            "f.title, st.studio_name, s.start_time, s.end_time, s.show_at, s.price " +
//            "FROM schedule s " +
//            "INNER JOIN (SELECT f.film_code, f.title FROM Film f) f "+
//            "ON s.film_code = f.film_code " +
//            "INNER JOIN (SELECT st.studio_id, st.studio_name FROM Studio st) st "+
//            "ON s.studio_id = st.studio_id " +
//            "WHERE s.show_at = :showAt";

    String SCHEDULE_DETAIL_BY_DATE = "SELECT " +
            "new com.ra.bioskop.dto.model.film.DetailScheduleDTO(f.title, st.name, s.startTime, s.endTime, s.showAt, s.price) "+
            "FROM Schedule AS s JOIN s.film AS f "+
            "JOIN s.studio AS st "+
            "WHERE s.showAt = :showAt";
}
