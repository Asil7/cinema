package uz.pdp.cinema.service;

//Asilbek Fayzullayev 16.03.2022 9:43   

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import uz.pdp.cinema.Interface.MovieService;
import uz.pdp.cinema.dto.MovieDto;
import uz.pdp.cinema.payload.ApiResponse;
import uz.pdp.cinema.projection.CustomMovie;
import uz.pdp.cinema.repository.MovieRepository;


@Service
public class MovieServiceImpl implements MovieService {

    @Autowired
    MovieRepository movieRepository;

    @Override
    public HttpEntity getAllMovies(int page, int size, String search, String sort, boolean direction) {
        Pageable pageable = PageRequest.of(
                page - 1,
                size,
                direction ? Sort.Direction.ASC : Sort.Direction.DESC,
                sort
        );

        try {
            Page<CustomMovie> all = movieRepository.findAllByPage(
                    pageable,
                    search
            );
            return ResponseEntity.ok(new ApiResponse("success", true, all));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(new ApiResponse("error", false, null));
        }
    }



    @Override
    public HttpEntity getMovieById(Integer id) {

        return null;
    }

    @Override
    public HttpEntity saveMovie(MovieDto movieDto) {
        return null;
    }

    @Override
    public HttpEntity deleteMovie(Integer id) {
        return null;
    }
}
