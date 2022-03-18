package uz.pdp.cinema.dto;

//Asilbek Fayzullayev 16.03.2022 9:45   

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;
import uz.pdp.cinema.model.Cast;
import uz.pdp.cinema.model.Distributor;
import uz.pdp.cinema.model.Genre;

import java.util.Date;
import java.util.List;


@AllArgsConstructor
@NoArgsConstructor
@Data
public class MovieDto {

    private String title;

    private String description;

    private int durationInMin;

    private MultipartFile coverImgId;

    private MultipartFile trailerVideoUrl;

    private List<Integer> genresId;

    private double minPrice;

    private Integer distributorIds;

    private Date releaseDate;

    private Double distributorShareInPercentage;

    private List<Integer> actorIds;

    private Double budget;


}
