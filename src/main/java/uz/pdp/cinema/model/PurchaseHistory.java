package uz.pdp.cinema.model;

//Asilbek Fayzullayev 14.03.2022 21:38

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class PurchaseHistory {
    @Id
    @GeneratedValue
    private Integer id;

    @ManyToOne
    private User user;

    @OneToOne
    private Ticket ticket;

    private Date date;

    @OneToOne
    private PayType payType;

}
