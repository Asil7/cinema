package uz.pdp.cinema.projection;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.rest.core.config.Projection;
import uz.pdp.cinema.model.MovieSession;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;
@Projection(types = MovieSession.class)
public interface MovieSessionProjection {

    Integer getMovieAnnouncementId(); // afisha id

    Integer getMovieId();

    String getMovieTitle();

    Integer getMovieCoverImgId();

    Integer getStartDateId();

    LocalDate getStartDate();

    @Value("#{@hallRepository.getHallsAndTimesByMovieAnnouncementIdAndStartDateId(target.movieAnnouncementId, target.startDateId)}")
    List<HallAndTimesProjectionForSession> getHalls();
}

