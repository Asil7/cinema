package uz.pdp.cinema.Interface;

import org.springframework.http.HttpEntity;
import uz.pdp.cinema.dto.MovieDto;

public interface MovieService {
    HttpEntity getAllMovies(int page, int size, String search, String sort, boolean direction);

    HttpEntity getMovieById(Integer id);

    HttpEntity saveMovie(MovieDto movieDto);

    HttpEntity deleteMovie(Integer id);
}
