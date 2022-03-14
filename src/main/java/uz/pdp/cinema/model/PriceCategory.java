package uz.pdp.cinema.model;

//Asilbek Fayzullayev 14.03.2022 16:12

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity(name = "price_categories")
public class PriceCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    public String name;


    public Integer additionalFeeInPercent;

    @Column(nullable = false)
    public String color;
}
