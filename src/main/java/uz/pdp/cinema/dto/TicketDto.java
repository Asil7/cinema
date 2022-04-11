package uz.pdp.cinema.dto;

//Asilbek Fayzullayev 01.04.2022 1:09   

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor

public class TicketDto {

    private Integer ticketId;

    private Integer movieSessionId;

    private Integer seatId;
}
