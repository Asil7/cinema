package uz.pdp.cinema.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import uz.pdp.cinema.model.Movie;
import uz.pdp.cinema.projection.CustomMovie;

public interface MovieRepository extends JpaRepository<Movie,Integer> {

    @Query(value = "select\n" +
            "       cast(m.id as varchar) as id,\n" +
            "       title,\n" +
            "       cast(release_date as date)as releaseDate,\n" +
            "       cast(m.cover_img_id as varchar) as coverImgId\n" +
            "from movie m\n" +
            "where lower(title) like lower(concat('%', :search, '%'))", nativeQuery = true)
    Page<CustomMovie> findAllByPage(Pageable pageable, @Param("search") String search);

}
