package uz.pdp.cinema.projection;

import java.time.LocalTime;
import java.util.UUID;

public interface SessionTimeProjection {
    UUID getId();

    LocalTime getTime();

    UUID getSessionId();

}