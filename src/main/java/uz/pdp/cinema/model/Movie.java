package uz.pdp.cinema.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

//Asilbek Fayzullayev 14.03.2022 18:37

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String title;
    private String description;
    private int durationInMinutes;
    @OneToOne
    private Attachment coverImage;

    @OneToOne
    private Attachment trailerVideo;

    @ManyToMany
    private List<Director> directors;

    @ManyToMany
    private List<Genre> genres;

    private double minPrice;

    @OneToOne
    private Distributor distributor;

    private double distributorShareInPercent;
    @ManyToMany
    private List<Actor>actors;
}