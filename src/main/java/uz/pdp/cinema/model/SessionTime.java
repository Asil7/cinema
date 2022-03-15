package uz.pdp.cinema.model;

import jdk.jfr.Enabled;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.sql.Timestamp;

//Asilbek Fayzullayev 14.03.2022 18:48
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class SessionTime {
    @Id
    @GeneratedValue
    private Integer id;

    private Timestamp time;

    @ManyToOne
    private SessionDate sessionDate;
}
