package uz.pdp.cinema.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import uz.pdp.cinema.model.SessionTime;
import uz.pdp.cinema.projection.SessionTimeProjection;

import java.util.List;

public interface SessionTimeRepository extends JpaRepository<SessionTime, Integer> {


    @Query
            (value =
                    "select distinct st.id  as id,\n" +
                            "                time,\n" +
                            "                 rh.afisha_id" +
                            "from session_time st\n" +
                            "         join reserved_hall rh on st.id = rh.start_time_id\n" +
                            "where rh.hall_id = :hallId\n" +
                            "  and afisha_id = :movieId\n" +
                            "  and rh.start_date_id = :startDateId", nativeQuery = true)
    List<SessionTimeProjection> getTimesByHallIdAndScheduleId(Integer hallId, Integer movieId, Integer startDateId);

}
