package uz.pdp.cinema.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import uz.pdp.cinema.model.Seat;
import uz.pdp.cinema.projection.AvailableSeatsProjection;

import java.util.List;

public interface SeatRepository extends JpaRepository<Seat, Integer> {
    List<Seat> findByRowId(Integer row_id);

    @Query(value = "select * from seats s\n" +
            "join hall_rows r on s.row_id = r.id\n" +
            "join halls h on h.id = r.hall_id\n" +
            "where h.id = :hallId", nativeQuery = true)
    List<Seat> findByHallId(Integer hallId);

    @Query(nativeQuery = true, value = "select s.id as id,\n" +
            "       s.number              as joyRaqami,\n" +
            "       hr.number             as qatorRaqami,\n" +
            "       true                  as available\n" +
            "from seats s\n" +
            "         join hall_rows hr on s.row_id = hr.id\n" +
            "         join halls h on hr.hall_id = h.id\n" +
            "         join movie_session ms on h.id = ms.hall_id\n" +
            "where s.id not in (\n" +
            "    select t.seat_id\n" +
            "    from tickets t\n" +
            "             join movie_session ms on ms.id = t.movie_session_id\n" +
            "    where t.ticket_status <> 'REFUNDED'\n" +
            "      and ms.id = :movieSessionId\n" +
            ")\n" +
            "  and ms.id = :movieSessionId\n" +
            "union\n" +
            "select s.id as id,\n" +
            "       s.number              as joyRaqami,\n" +
            "       hr.number             as qatorRaqami,\n" +
            "       false                 as available\n" +
            "from tickets t\n" +
            "         join seats s on t.seat_id = s.id\n" +
            "         join hall_rows hr on s.row_id = hr.id\n" +
            "         join movie_session ms on ms.id = t.movie_session_id\n" +
            "where t.ticket_status <> 'REFUNDED'\n" +
            "  and ms.id = :movieSessionId\n" +
            "order by qatorRaqami, joyRaqami")
    List<AvailableSeatsProjection> findAvailableSeatsBySessionId(Integer movieSessionId);


    @Query(nativeQuery = true, value = "select distinct (coalesce(pc.additional_fee_in_percent, 0) * m2.min_price / 100) +\n" +
            "                           (coalesce(h.vip_additional_fee_in_percent, 0) * m2.min_price / 100) +\n" +
            "                           m2.min_price as price\n" +
            "                    from seats s\n" +
            "                             join hall_rows r on r.id = s.row_id\n" +
            "                             join halls h on r.hall_id = h.id\n" +
            "                             join movie_session ms on h.id = ms.hall_id\n" +
            "                             join movie_session m on h.id = m.hall_id\n" +
            "                             join movie_announcements ma on ma.id = m.movie_announcement_id\n" +
            "                             join movies m2 on m2.id = ma.movie_id\n" +
            "                             join price_categories pc on s.price_category_id = pc.id\n" +
            "                    where ms.id = :movieSessionId\n" +
            "                      and s.id = :seatId")
    Double getPriceOfSeatBySeatIdAndMovieSessionId(Integer seatId, Integer movieSessionId);
}
