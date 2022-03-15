package uz.pdp.cinema.model;

//Asilbek Fayzullayev 14.03.2022 21:36

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity(name = "carts")
public class Cart {
    @Id
    @GeneratedValue
    private Integer id;

    @OneToOne
    private User user;

}
