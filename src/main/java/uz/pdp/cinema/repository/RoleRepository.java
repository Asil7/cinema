package uz.pdp.cinema.repository;

//Asilbek Fayzullayev 16.03.2022 16:30   

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import uz.pdp.cinema.model.Role;
import uz.pdp.cinema.model.Row;
import uz.pdp.cinema.projection.RowProjection;

import java.util.List;

public interface RoleRepository extends JpaRepository<Role, Integer> {


}
