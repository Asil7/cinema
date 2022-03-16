package uz.pdp.cinema.repository;

//Asilbek Fayzullayev 16.03.2022 16:30   

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.cinema.model.Row;

public interface RowRepository extends JpaRepository<Row,Integer> {
}
