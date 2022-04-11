package uz.pdp.cinema.repository;

//Asilbek Fayzullayev 26.03.2022 11:20   

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import uz.pdp.cinema.model.Ticket;
import uz.pdp.cinema.model.User;
import uz.pdp.cinema.model.enums.TicketStatus;
import uz.pdp.cinema.projection.CustomTicket;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {

    @Query(nativeQuery = true, value = "select distinct t.id,\n" +
            "                s.number  as seatNumber,\n" +
            "                hr.number as rowNumber,\n" +
            "                h.name    as hallName,\n" +
            "                (coalesce(pc.additional_fee_in_percent, 0) * m.min_price / 100) +\n" +
            "                (coalesce(h.vip_additional_fee_in_percent, 0) * m.min_price / 100) +\n" +
            "                m.min_price as price, \n" +
            "                ms.id as movieSessionId,\n" +
            "                m.title as movieName,\n" +
            "                sd.date as startDate,\n" +
            "                st.time as startTime\n" +
            "from tickets t\n" +
            "         join seats s on t.seat_id = s.id\n" +
            "         join hall_rows hr on hr.id = s.row_id\n" +
            "         join halls h on h.id = hr.hall_id\n" +
            "         join movie_session ms on t.movie_session_id = ms.id\n" +
            "         join movie_announcements ma on ms.movie_announcement_id = ma.id\n" +
            "         join movies m on m.id = ma.movie_id\n" +
            "         join price_categories pc on pc.id = s.price_category_id\n" +
            "         join session_date sd on sd.id = ms.start_date_id\n" +
            "         join session_time st on st.id = ms.start_time_id\n" +
            "where t.ticket_status = 'NEW' and\n" +
            "      t.user_id = :userId")
    List<CustomTicket> getTicketsInTheCart(Integer userId);

    @Query(nativeQuery = true, value = "delete\n" +
            "from tickets\n" +
            "where user_id = :userId\n" +
            "and ticket_status = 'NEW'")
    void clearCartOfUser(Integer userId);


    @Query(nativeQuery = true, value = "delete\n" +
            "from tickets t\n" +
            "where ticket_status = 'NEW'\n" +
            "and user_id = :userId\n" +
            "and id = :ticketId ")
    void deleteTicketFromCart(Integer userId, Integer ticketId);


    @Query(nativeQuery = true, value = "select distinct t.id,\n" +
            "                s.number  as seatNumber,\n" +
            "                hr.number as rowNumber,\n" +
            "                h.name    as hallName,\n" +
            "                (coalesce(pc.additional_fee_in_percent, 0) * m.min_price / 100) +\n" +
            "                (coalesce(h.vip_additional_fee_in_percent, 0) * m.min_price / 100) +\n" +
            "                m.min_price as price, \n" +
            "                ms.id as movieSessionId,\n" +
            "                m.title as movieName,\n" +
            "                sd.date as startDate,\n" +
            "                st.time as startTime\n" +
            "from tickets t\n" +
            "         join seats s on t.seat_id = s.id\n" +
            "         join hall_rows hr on hr.id = s.row_id\n" +
            "         join halls h on h.id = hr.hall_id\n" +
            "         join movie_session ms on t.movie_session_id = ms.id\n" +
            "         join movie_announcements ma on ms.movie_announcement_id = ma.id\n" +
            "         join movies m on m.id = ma.movie_id\n" +
            "         join price_categories pc on pc.id = s.price_category_id\n" +
            "         join session_date sd on sd.id = ms.start_date_id\n" +
            "         join session_time st on st.id = ms.start_time_id\n")
    List<CustomTicket> getAllTicketsInTheCart();




  Optional<User>   findByFullName(String userName);

}
