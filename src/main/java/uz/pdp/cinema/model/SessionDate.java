package uz.pdp.cinema.model;

//Asilbek Fayzullayev 14.03.2022 18:28

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class SessionDate {
    @Id
    @GeneratedValue
    private Integer id;

    LocalDate date;

    public SessionDate(LocalDate of) {
    }
}
