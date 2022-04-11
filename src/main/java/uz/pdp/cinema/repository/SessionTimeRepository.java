package uz.pdp.cinema.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import uz.pdp.cinema.model.SessionTime;
import uz.pdp.cinema.projection.SessionTimeProjection;

import java.util.List;

public interface SessionTimeRepository extends JpaRepository<SessionTime, Integer> {


    @Query
            (value =
                    "select distinct st.id                    as id,\n" +
                            "                st.time                  as time,\n" +
                            "                ms.movie_announcement_id as ann\n" +
                            "from session_time st\n" +
                            "         join movie_session ms on st.id = ms.start_time_id\n" +
                            "where ms.hall_id = :hallId\n" +
                            "  and ms.movie_announcement_id = :movieAnnouncementId\n" +
                            "  and ms.start_date_id = :startDateId", nativeQuery = true)
    List<SessionTimeProjection> getTimesByHallIdAndAnnouncementIdAndStartDateId(Integer hallId, Integer movieAnnouncementId, Integer startDateId);
}
