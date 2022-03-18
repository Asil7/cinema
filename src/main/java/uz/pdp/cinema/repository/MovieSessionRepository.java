package uz.pdp.cinema.repository;

//Asilbek Fayzullayev 17.03.2022 14:54   

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import uz.pdp.cinema.model.MovieSession;

public interface MovieSessionRepository extends JpaRepository<MovieSession, Integer> {

    @Query(nativeQuery = true, value = "select ma.id as movieAnnouncementId,\n" +
            "       ma.movie_id as movieId,\n" +
            "       m.title as movieTitle,\n" +
            "       m.cover_img_id as movieCoverImgId,\n" +
            "       sd.id as startedDateId,\n" +
            "       sd.date as startedDate\n" +
            "from movie_session ms\n" +
            "join movie_announcements ma on ma.id = ms.movie_announcement_id\n" +
            "join session_date sd on sd.id = ms.start_date_id\n" +
            "join movies m on ma.movie_id = m.id\n" +
            "where lower(m.title) like lower(concat('%',:search,'%')) and sd.date >= cast(now() as date)\n" +
            "            order by sd.date")
    Page<MovieSession> findAllSessionsByPage(Pageable pageable, String search);
}
