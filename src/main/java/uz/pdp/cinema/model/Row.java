package uz.pdp.cinema.model;

//Asilbek Fayzullayev 14.03.2022 16:22

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.minidev.json.annotate.JsonIgnore;

import javax.persistence.*;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity(name = "hall_rows")
public class Row {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Integer number;


    @ManyToOne
    private Hall hall;

    @JsonIgnore
    @OneToMany(mappedBy = "row",cascade = CascadeType.ALL)
    private List<Seat> seats;

    public Row(int i, Hall zal1) {
    }
}
