package uz.pdp.cinema.repository;

//Asilbek Fayzullayev 16.03.2022 22:39   

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.cinema.model.PriceCategory;
import uz.pdp.cinema.service.PriceCategoryService;

public interface PriceCategoryRepository extends JpaRepository<PriceCategory, Integer> {
}
