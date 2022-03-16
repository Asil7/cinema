package uz.pdp.cinema.repository;

//Asilbek Fayzullayev 15.03.2022 23:49   

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.cinema.model.Hall;

public interface HallRepository extends JpaRepository<Hall, Integer> {
}
