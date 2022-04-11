package uz.pdp.cinema.repository;

//Asilbek Fayzullayev 15.03.2022 23:49   

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import uz.pdp.cinema.model.Hall;
import uz.pdp.cinema.projection.CustomHall;
import uz.pdp.cinema.projection.HallAndTimesProjectionForSession;
import uz.pdp.cinema.projection.HallProjection;

import java.util.List;

public interface HallRepository extends JpaRepository<Hall, Integer> {

    @Query(nativeQuery = true, value = "select distinct h.id                  as id,\n" +
            "                h.name                as name,\n" +
            "                ms.start_date_id      as startDateId,\n" +
            "                movie_announcement_id as movieAnnouncementId\n" +
            "from movie_session ms\n" +
            "         join halls h on h.id = ms.hall_id\n" +
            "where movie_announcement_id = :movieAnnouncementId\n" +
            "  and start_date_id = :startDateId")
    List<HallAndTimesProjectionForSession> getHallsAndTimesByMovieAnnouncementIdAndStartDateId(Integer movieAnnouncementId, Integer startDateId);

    @Query(nativeQuery = true, value = "select h.id as id, h.name as name,h.vip_additional_fee_in_percent from halls h\n" +
            "join hall_rows r on h.id = r.hall_id\n" +
            "join seats s on r.id = s.row_id\n" +
            "where s.id = :id")
    List<HallProjection> findBySeatId(Integer id);
}
