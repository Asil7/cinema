package uz.pdp.cinema.repository;

//Asilbek Fayzullayev 14.03.2022 23:49

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.cinema.model.Distributor;

public interface DistributorRepository extends JpaRepository <Distributor,Integer> {
        boolean existsByName(String name);
}
