package uz.pdp.cinema.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.cinema.model.MovieAnnouncement;

public interface MovieAnnouncementRepository extends JpaRepository<MovieAnnouncement,Integer> {
}
