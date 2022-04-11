package uz.pdp.cinema.model;

//Asilbek Fayzullayev 14.03.2022 16:29

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.minidev.json.annotate.JsonIgnore;
import org.hibernate.annotations.NaturalId;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity(name = "seats")
public class Seat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Integer number;


    @ManyToOne
    private Row row;


    @OneToOne
    private PriceCategory priceCategory;

}
