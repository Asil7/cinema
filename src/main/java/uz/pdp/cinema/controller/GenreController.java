package uz.pdp.cinema.controller;

//Asilbek Fayzullayev 16.03.2022 16:16   

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.cinema.model.Genre;
import uz.pdp.cinema.payload.ApiResponse;
import uz.pdp.cinema.service.GenreService;

@RestController
@RequestMapping("/api/genre")
public class GenreController {

    @Autowired
    GenreService service;

    @GetMapping
    public HttpEntity getAllGenre() {
        ApiResponse allGenres = service.getAllGenres();
        return ResponseEntity.status(allGenres.isSuccess() ? 200 : 204).body(allGenres);
    }

    @GetMapping("/{id}")
    public HttpEntity getById(@PathVariable Integer id) {
        ApiResponse genreById = service.getGenreById(id);
        return ResponseEntity.status(genreById.isSuccess() ? 200 : 409).body(genreById);
    }

    @PostMapping
    public HttpEntity<?> addGenre(@RequestBody Genre genre) {
        ApiResponse apiResponse = service.addGenre(genre);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 409).body(apiResponse);
    }

    @PutMapping("/{id}")
    public HttpEntity<?> editGenre(@PathVariable Integer id, @RequestBody Genre genre) {
        ApiResponse apiResponse = service.editGenre(id, genre);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 409).body(apiResponse);
    }

    @DeleteMapping("/{id}")
    public HttpEntity<?> deleteGenre(@PathVariable Integer id) {
        ApiResponse apiResponse = service.deleteGenreById(id);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 404).body(apiResponse);
    }

}
