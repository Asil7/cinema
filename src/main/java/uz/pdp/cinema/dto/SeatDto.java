package uz.pdp.cinema.dto;

//Asilbek Fayzullayev 23.03.2022 14:08   

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class SeatDto {

    private Integer number;

    private Integer rowId;

    private Integer  priceCategoryId;
}
