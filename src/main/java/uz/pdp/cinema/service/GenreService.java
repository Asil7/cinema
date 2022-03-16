package uz.pdp.cinema.service;

//Asilbek Fayzullayev 16.03.2022 15:38   

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.cinema.model.Genre;
import uz.pdp.cinema.payload.ApiResponse;
import uz.pdp.cinema.repository.GenreRepository;

import java.util.List;
import java.util.Optional;

@Service
public class GenreService {

    @Autowired
    GenreRepository genreRepository;

    public ApiResponse getAllGenres() {
        List<Genre> genreList = genreRepository.findAll();
        if (genreList.size() == 0) {
            return new ApiResponse("List empty ", false);
        }
        return new ApiResponse("Success", true, genreList);
    }

    public ApiResponse getGenreById(Integer id) {
        Optional<Genre> optionalGenre = genreRepository.findById(id);
        if (optionalGenre.isPresent()) {
            return new ApiResponse("Success", true, optionalGenre);
        }
        return new ApiResponse("Error", false);
    }

    public ApiResponse addGenre(Genre genre) {
        try {
            Genre newGenre = new Genre();
            newGenre.setName(genre.getName());
            Genre save = genreRepository.save(newGenre);
            return new ApiResponse("Successfully added", true, save);

        } catch (Exception e) {
            return new ApiResponse("Error", false);
        }
    }

    public ApiResponse editGenre(Integer id, Genre genre) {
        Optional<Genre> optionalGenre = genreRepository.findById(id);
        if (optionalGenre.isPresent()) {
            try {
                Genre editGenre = optionalGenre.get();
                editGenre.setName(genre.getName());
                Genre save = genreRepository.save(editGenre);
                return new ApiResponse("Successfully edited", true, save);
            } catch (Exception e) {
                return new ApiResponse("Error! Maybe genre already exists!", false);
            }
        }
        return new ApiResponse("Genre not found", false);
    }

    public ApiResponse deleteGenreById(Integer id) {
        Optional<Genre> optionalGenre = genreRepository.findById(id);
        if (optionalGenre.isPresent()) {
            genreRepository.deleteById(id);
            return new ApiResponse("Deleted", true);
        }
        return new ApiResponse("Genre not found", false);
    }
}
