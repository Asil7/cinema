package uz.pdp.cinema.projection;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.rest.core.config.Projection;
import uz.pdp.cinema.model.Seat;

@Projection(types = Seat.class)
public interface SeatProjection {
    Integer getId();

    Integer getNumber();

    Integer getRowId();

    Integer getRowNumber();

//    @Value("#{@rowRepository.findBySeatId(target.id)}")
//    RowProjection getRow();
//
//    @Value("#{@hallRepository.findBySeatId(target.id)}")
//    HallProjection getHall();
}
