package uz.pdp.cinema.repository;

//Asilbek Fayzullayev 16.03.2022 15:39   

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.cinema.model.Genre;

public interface GenreRepository extends JpaRepository<Genre,Integer> {
}
