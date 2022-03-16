package uz.pdp.cinema.model;

import com.sun.xml.internal.ws.server.ServerRtException;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
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


    @Column(nullable = false, length = 50)
    private String title;

    @Column(columnDefinition = "text")
    private String description;

    private int durationInMin;

    private double minPrice;

    @OneToOne
    private Attachment coverImg;

    @Column(nullable = false)
    private String trailerVideoUrl;

    @Column(nullable = false)
    private Date releaseDate;

    private Double budget;

    @ManyToOne
    private Distributor distributor;

    @Column(nullable = false)
    private Double distributorShareInPercentage;

    @ManyToMany
    private List<Cast> casts;

    @ManyToMany
    private List<Genre> genres;


    @ManyToMany
    private List<Actor>actors;
}