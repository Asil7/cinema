package uz.pdp.cinema.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;

//Asilbek Fayzullayev 14.03.2022 18:23

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Actor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String name;

    @OnDelete(action = OnDeleteAction.CASCADE)
    @OneToOne
    private Attachment photo;


    public Actor(String name, Attachment photo) {
        this.name = name;
        this.photo = photo;
    }
}