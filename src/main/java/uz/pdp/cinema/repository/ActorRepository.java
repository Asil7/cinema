package uz.pdp.cinema.repository;

//Asilbek Fayzullayev 15.03.2022 10:25

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.cinema.model.Actor;

public interface ActorRepository extends JpaRepository<Actor,Integer> {

}
