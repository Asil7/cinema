package uz.pdp.cinema.controller;

//Asilbek Fayzullayev 18.03.2022 16:09   

import lombok.experimental.UtilityClass;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import uz.pdp.cinema.model.MovieSession;
import uz.pdp.cinema.service.MovieService;
import uz.pdp.cinema.util.Constant;

@RestController
@RequestMapping("/api/movie-session")
public class MovieSessionController {

    @Autowired
    MovieService movieService;

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
}
