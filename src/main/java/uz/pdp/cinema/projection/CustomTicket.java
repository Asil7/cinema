package uz.pdp.cinema.projection;

//Asilbek Fayzullayev 27.03.2022 16:04   

import org.springframework.data.rest.core.config.Projection;

import java.time.LocalDate;
import java.time.LocalTime;

public interface CustomTicket {
    Integer getId();

    Integer getSeatNumber();

    Integer getRowNumber();

    String getHallName();

    Double getPrice();

    Integer getMovieSessionId();

    String getMovieName();

    LocalDate getStartDate();

    LocalTime getStartTime();
}
