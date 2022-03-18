package uz.pdp.cinema.model;

//Asilbek Fayzullayev 14.03.2022 16:22

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity(name = "rows")
public class Row {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Integer number;

    @ManyToOne
    private Hall hall;

    @OneToMany(mappedBy = "row",cascade = CascadeType.ALL)
    private List<Seat> seats;

    public Row(int i, Hall zal1) {
    }
}
