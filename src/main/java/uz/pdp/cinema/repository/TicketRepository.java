package uz.pdp.cinema.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import uz.pdp.cinema.model.Ticket;
import uz.pdp.cinema.model.enums.TicketStatus;
import uz.pdp.cinema.projection.CustomTicketForCart;

import java.util.List;
import java.util.UUID;

public interface
TicketRepository extends JpaRepository<Ticket, Integer> {

    @Query(nativeQuery = true, value = "select id, ticket_status from tickets\n" +
            "where id=:id")
    CustomTicketForCart getTicketByIdForCart(Integer id);

    @Query(nativeQuery = true,value = "select distinct * from tickets\n" +
            "where movie_session_id=:movieSessionId\n" +
            "and seat_id=:seatId\n" +
            "and ticket_status<>'REFUNDED'")
    CustomTicketForCart checkIfSeatIsAvailable( Integer seatId, Integer movieSessionId);



//    @Query(nativeQuery = true,value = "select * from\n" +
//            "tickets t\n" +
//            "where t.user_id=:userId\n" +
//            "and t.ticket_status = 'NEW'")
//    List<Ticket> findByUserId (Integer userId, TicketStatus status);


    List<Ticket> findByUserIdAndTicketStatus(Integer userId, TicketStatus ticketStatus);

    @Query(nativeQuery = true, value = "select * from tickets where id = :id")
    Ticket getTicketBYId(Integer id);

}
