package uz.pdp.cinema.dto;

//Asilbek Fayzullayev 16.03.2022 9:45   

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import uz.pdp.cinema.model.Cast;
import uz.pdp.cinema.model.Distributor;
import uz.pdp.cinema.model.Genre;

import java.util.Date;
import java.util.List;


@AllArgsConstructor
@NoArgsConstructor
@Data
public class MovieDto {

    private Integer id;

    private String title;

    private String description;

    private int durationInMin;

    private double minPrice;

    private Integer coverImgId;

    private String trailerVideoUrl;

    private Date releaseDate;

    private Double budget;

    private Distributor distributor;

    private Double distributorShareInPercentage;

    private List<Cast> casts;

    private List<Genre> genres;
}
