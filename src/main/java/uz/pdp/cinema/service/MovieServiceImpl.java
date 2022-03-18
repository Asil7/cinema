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
import uz.pdp.cinema.controller.DistributorController;
import uz.pdp.cinema.dto.MovieDto;
import uz.pdp.cinema.model.*;
import uz.pdp.cinema.payload.ApiResponse;
import uz.pdp.cinema.projection.CustomMovie;
import uz.pdp.cinema.repository.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
public class MovieServiceImpl implements MovieService {

    @Autowired
    MovieRepository movieRepository;
    @Autowired
    ActorRepository actorRepository;
    @Autowired
    AttachmentRepository attachmentRepository;
    @Autowired
    DirectorRepository directorRepository;
    @Autowired
    DistributorRepository distributorRepository;
    @Autowired
    GenreRepository genreRepository;
    @Autowired
    AttachmentService attachmentService;

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
        Movie movie = new Movie();

        Optional<Distributor> distributorOptional = distributorRepository.findById(movieDto.getDistributorIds());
        if (!distributorOptional.isPresent()) {
            return new ResponseEntity(new ApiResponse("wrong",
                    false, null), HttpStatus.BAD_REQUEST);
        }

        ArrayList<Genre> genres = new ArrayList<>();
        for (Integer genreId : movieDto.getGenresId()) {
            if (!genreRepository.findById(genreId).isPresent()) {
                return new ResponseEntity(new ApiResponse("wrong",
                        false, null), HttpStatus.BAD_REQUEST);
            }
            genres.add(genreRepository.findById(genreId).get());
        }
        ArrayList<Actor> actors = new ArrayList<>();
        for (Integer actorId : movieDto.getGenresId()) {
            if (!actorRepository.findById(actorId).isPresent()) {
                return new ResponseEntity(new ApiResponse("wrong",
                        false, null), HttpStatus.BAD_REQUEST);
            }
            actors.add(actorRepository.findById(actorId).get());
        }

        movie.setTitle(movieDto.getTitle());
        movie.setDescription(movieDto.getDescription());
        movie.setDurationInMin(movieDto.getDurationInMin());
        movie.setMinPrice(movieDto.getMinPrice());
        movie.setDistributorShareInPercentage(movieDto.getDistributorShareInPercentage());
        movie.setCoverImg(attachmentService.saveAttachment(movieDto.getCoverImgId()));
        movie.setTrailerVideoUrl(attachmentService.saveAttachment(movieDto.getTrailerVideoUrl()));
        movie.setGenres(genres);
        movie.setActors(actors);
        movie.setDistributor(distributorOptional.get());
        movie.setBudget(movieDto.getBudget());
        movie.setReleaseDate(movieDto.getReleaseDate());
        Movie save = movieRepository.save(movie);

        return new ResponseEntity(new ApiResponse("success",
                true, save), HttpStatus.OK);
    }


    @Override
    public HttpEntity deleteMovie(Integer id) {
        List<Movie> movieList = movieRepository.findAll();
        for (Movie movie : movieList) {
            if (movie.getId().equals(id)) {
                movieRepository.deleteById(id);
                return new ResponseEntity(new ApiResponse("success",
                        true, true), HttpStatus.OK);
            }
        }
        return new ResponseEntity(new ApiResponse("wrong",
                false, false), HttpStatus.NOT_FOUND);
    }

    @Override
    public HttpEntity editMovie(Integer id) {
        return null;
    }

}


