package uz.pdp.cinema.projection;

import java.time.LocalTime;

public interface SessionTimeProjection {
    Integer getId();

    LocalTime getTime();

    Integer getSessionId();

}