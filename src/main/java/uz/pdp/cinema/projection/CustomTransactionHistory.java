package uz.pdp.cinema.projection;

import java.time.LocalDateTime;

public interface CustomTransactionHistory {
    Integer getId();

    Integer getTicketId();

    String getUserName();

    Integer getUserId();

    LocalDateTime getCreatedAt();
}
