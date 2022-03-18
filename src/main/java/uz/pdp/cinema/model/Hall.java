package uz.pdp.cinema.model;

//Asilbek Fayzullayev 14.03.2022 16:08

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity(name = "halls")
public class Hall {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String name;

    @OneToMany(mappedBy = "hall", cascade = CascadeType.ALL)
    private List<Row> rows;

    private Integer vipAdditionalFeeInPercent;

    public Hall(String s) {
    }

    public Hall(String s, double v) {
    }
}
