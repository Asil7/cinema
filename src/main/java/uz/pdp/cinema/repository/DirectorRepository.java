package uz.pdp.cinema.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.cinema.controller.DirectorController;
import uz.pdp.cinema.model.Director;

public interface DirectorRepository extends JpaRepository<Director, Integer> {
}
