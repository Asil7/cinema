package uz.pdp.cinema.projection;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.rest.core.config.Projection;
import uz.pdp.cinema.model.Hall;

import java.util.List;
import java.util.UUID;

@Projection(types = Hall.class)
public interface HallAndTimesProjectionForSession {
    Integer getId();

    String getName();

//
//    Integer getMovieAnnouncementId();
//
//    UUID getStartDateId();

    @Value("#{@sessionTimeRepository.getTimesByHallIdAndAnnouncementIdAndStartDateId(target.id, target.movieAnnouncementId, target.startDateId)}")
    List<SessionTimeProjection> getTimes();


}
