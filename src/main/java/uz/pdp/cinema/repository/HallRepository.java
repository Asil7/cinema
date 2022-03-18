package uz.pdp.cinema.repository;

//Asilbek Fayzullayev 15.03.2022 23:49   

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import uz.pdp.cinema.model.Hall;
import uz.pdp.cinema.projection.CustomHall;

import java.util.List;

public interface HallRepository extends JpaRepository<Hall, Integer> {

    @Query(nativeQuery = true, value = "select h.id,h.name\n" +
            "from afisha\n" +
            "join halls h on h.id = afisha.hall_id\n" +
            "where movie_id = :movieId")
    List<CustomHall> getAllHallByMovieId(Integer movieId);


}
