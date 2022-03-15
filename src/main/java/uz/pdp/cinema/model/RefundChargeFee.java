package uz.pdp.cinema.model;

//Asilbek Fayzullayev 14.03.2022 21:41

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class RefundChargeFee {
    @Id
    @GeneratedValue
    private Integer id;

    private int intervalInMinutes;

    private double percentage;

}
