package uz.pdp.cinema.controller;

//Asilbek Fayzullayev 16.03.2022 9:43   


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import uz.pdp.cinema.service.MovieServiceImpl;
import uz.pdp.cinema.util.Constant;

@RestController
@RequestMapping("/api/movie")
public class MovieController {

    @Autowired
    MovieServiceImpl movieService;

    @GetMapping
    public HttpEntity getAllMovie(
            @RequestParam(name = "size", defaultValue = Constant.DEFAULT_PAGE_SIZE) int size,
            @RequestParam(name = "page", defaultValue = "1") int page,
            @RequestParam(name = "search", defaultValue = "") String search,
            @RequestParam(name = "sort", defaultValue = "title") String sort
    ){
        return movieService.getAllMovies(page, size, search, sort, true);
    }

}
