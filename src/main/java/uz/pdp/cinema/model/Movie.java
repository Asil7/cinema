package uz.pdp.cinema.model;

import com.sun.xml.internal.ws.server.ServerRtException;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

//Asilbek Fayzullayev 14.03.2022 18:37

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity(name = "movies")
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

    @OneToOne
    private Attachment trailerVideoUrl;

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

    public Movie(String title, String description, int durationInMin, double minPrice, Attachment coverImg, Attachment trailerVideoUrl, Date releaseDate, Double budget, Distributor distributor, Double distributorShareInPercentage) {
        this.title = title;
        this.description = description;
        this.durationInMin = durationInMin;
        this.minPrice = minPrice;
        this.coverImg = coverImg;
        this.trailerVideoUrl = trailerVideoUrl;
        this.releaseDate = releaseDate;
        this.budget = budget;
        this.distributor = distributor;
        this.distributorShareInPercentage = distributorShareInPercentage;
    }
}