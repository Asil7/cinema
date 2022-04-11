package uz.pdp.cinema.service;

//Asilbek Fayzullayev 17.03.2022 14:53   

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import uz.pdp.cinema.model.MovieSession;
import uz.pdp.cinema.payload.ApiResponse;
import uz.pdp.cinema.projection.MovieSessionProjection;
import uz.pdp.cinema.repository.MovieSessionRepository;

@Service
public class MovieService {

    @Autowired
    MovieSessionRepository movieSessionRepository;


    public HttpEntity getAllMovies(int page, int size, String search) {
        Pageable pageable = PageRequest.of(
                page-1,
                size
        );
        Page<MovieSessionProjection> allSessionsByPage = movieSessionRepository.findAllSessionsByPage(
                pageable,
                search
        );
        return ResponseEntity.ok(new ApiResponse("Success",true,allSessionsByPage));
    }
}