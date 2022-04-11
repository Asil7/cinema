package uz.pdp.cinema.controller;

//Asilbek Fayzullayev 16.03.2022 9:43   


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import uz.pdp.cinema.dto.MovieDto;
import uz.pdp.cinema.model.Movie;
import uz.pdp.cinema.payload.ApiResponse;
import uz.pdp.cinema.repository.MovieRepository;
import uz.pdp.cinema.service.MovieServiceImpl;
import uz.pdp.cinema.util.Constant;

import java.util.Optional;

@RestController
@RequestMapping("/api/movie")
public class MovieController {

    @Autowired
    MovieServiceImpl movieService;

    @Autowired
    MovieRepository movieRepository;

    @GetMapping
    public HttpEntity getAllMovie(
            @RequestParam(name = "size", defaultValue = Constant.DEFAULT_PAGE_SIZE) int size,
            @RequestParam(name = "page", defaultValue = "1") int page,
            @RequestParam(name = "search", defaultValue = "") String search,
            @RequestParam(name = "sort", defaultValue = "title") String sort
    ) {
        return movieService.getAllMovies(page, size, search, sort, true);
    }


    @GetMapping("/{id}")
    public HttpEntity<?> getMovieById(@PathVariable Integer id) {
        Optional<Movie> optionalMovie = movieRepository.findById(id);
        if (!optionalMovie.isPresent()) {
            return new ResponseEntity(new ApiResponse("Movie not found", false, null), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity(new ApiResponse("success",
                true, optionalMovie.get()), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public HttpEntity<?> deleteMovieById(@PathVariable Integer id) {
        return movieService.deleteMovie(id);
    }

    @PostMapping(consumes = {MediaType.MULTIPART_FORM_DATA_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity saveMovie(
            @RequestPart(name="json")  MovieDto movie,
            @RequestPart (name = "file")MultipartFile file
            ) {
        System.out.println(movie);
        movieService.saveMovie(movie);
        return new ResponseEntity("save", HttpStatus.OK);
    }

}

