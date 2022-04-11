package uz.pdp.cinema.projection;

//Asilbek Fayzullayev 17.03.2022 16:47   

import org.springframework.data.rest.core.config.Projection;
import uz.pdp.cinema.model.Hall;

import java.util.UUID;

@Projection(types = Hall.class)
public interface CustomHall {

    Integer getId();
    String getName();

    Integer getMovieAnnouncementId();
//
//    UUID getStartDateId();

//    @Value("#{@sessionTimeRepository.getTimesByHallIdAndScheduleId(target.id, target.movieAnnouncementId, target.startDateId)}")
//    List<CustomHall> getTimes();



}
