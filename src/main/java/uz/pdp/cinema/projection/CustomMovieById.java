package uz.pdp.cinema.projection;

//Asilbek Fayzullayev 18.03.2022 15:58   

import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.rest.core.config.Projection;
import uz.pdp.cinema.model.Movie;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Projection(types = Movie.class)
public interface CustomMovieById {

    Integer getId();

    String getTitle();

    Integer getCoverImgId();

    LocalDate getReleaseDate();


    // todo get more fields

    @Value("#{@genreRepository.getGenresByMovieId(target.id)}")
    List<GenreProjection> getGenres();
}
