package uz.pdp.cinema.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.cinema.model.SessionDate;

public interface SessionDateRepository extends JpaRepository<SessionDate,Integer> {
}
