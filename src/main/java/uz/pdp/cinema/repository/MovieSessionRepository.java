package uz.pdp.cinema.repository;

//Asilbek Fayzullayev 17.03.2022 14:54   

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import uz.pdp.cinema.model.MovieSession;
import uz.pdp.cinema.projection.MovieSessionProjection;

public interface
MovieSessionRepository extends JpaRepository<MovieSession, Integer> {

    @Query(nativeQuery = true, value = "select distinct ma.id as movieAnnouncementId,\n" +
            "       ma.movie_id as movieId,\n" +
            "       m.title as movieTitle,\n" +
            "       m.cover_img_id as movieCoverImgId,\n" +
            "       sd.id as startDateId,\n" +
            "       sd.date as startDate\n" +
            "from movie_session ms\n" +
            "join movie_announcements ma on ma.id = ms.movie_announcement_id\n" +
            "join session_date sd on sd.id = ms.start_date_id\n" +
            "join movies m on ma.movie_id = m.id\n" +
            "where lower(m.title) like lower(concat('%',:search,'%')) and sd.date >= cast(now() as date)\n" +
            "            order by sd.date")
    Page<MovieSessionProjection> findAllSessionsByPage(Pageable pageable, String search);

    @Query(nativeQuery = true, value = "select distinct ma.id as movieAnnouncementId,\n" +
            "       ma.movie_id as movieId,\n" +
            "       m.title as movieTitle,\n" +
            "       m.cover_img_id as movieCoverImgId,\n" +
            "       sd.id as startDateId,\n" +
            "       sd.date as startDate\n" +
            "from movie_session ms\n" +
            "join movie_announcements ma on ma.id = ms.movie_announcement_id\n" +
            "join session_date sd on sd.id = ms.start_date_id\n" +
            "join movies m on ma.movie_id = m.id\n" +
            "where ms.id=:movieSessionId" +
            "            order by sd.date")
    MovieSessionProjection findSessionById(Integer movieSessionId);
}
