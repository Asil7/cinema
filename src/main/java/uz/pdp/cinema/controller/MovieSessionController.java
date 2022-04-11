package uz.pdp.cinema.controller;

//Asilbek Fayzullayev 18.03.2022 16:09   

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.cinema.model.MovieSession;
import uz.pdp.cinema.payload.ApiResponse;
import uz.pdp.cinema.projection.MovieSessionProjection;
import uz.pdp.cinema.repository.MovieSessionRepository;
import uz.pdp.cinema.service.MovieService;
import uz.pdp.cinema.util.Constant;

import java.util.Optional;

@RestController
@RequestMapping("/api/movie-session")
public class MovieSessionController {

    @Autowired
    MovieService movieService;

    @Autowired
    MovieSessionRepository movieSessionRepository;

    @GetMapping
    public HttpEntity getAllMovieSessions(
            @RequestParam(name = "size", defaultValue = Constant.DEFAULT_PAGE_SIZE) int size,
            @RequestParam(name = "page", defaultValue = "1") int page,
            @RequestParam(name = "search", defaultValue = "") String search
    ) {
        return movieService.getAllMovies(
                page,
                size,
                search
        );
    }

    @GetMapping("/{id}")
    public HttpEntity<?> getMovieById(@PathVariable Integer id) {
        MovieSessionProjection sessionById = movieSessionRepository.findSessionById(id);
        if (sessionById== null) {
            return ResponseEntity.status(404).body(new ApiResponse("Movie not found", false));
        }
        return ResponseEntity.status(200).body(new ApiResponse("success",
                true, sessionById));
    }
}
