package uz.pdp.cinema.repository;

//Asilbek Fayzullayev 16.03.2022 16:30   

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import uz.pdp.cinema.model.Row;
import uz.pdp.cinema.projection.RowProjection;

import java.util.List;

public interface RowRepository extends JpaRepository<Row, Integer> {


    @Query(value = "select r.id as id, r.number, h.name as Name\n" +
            "from hall_rows r\n" +
            "         join halls h on h.id = r.hall_id\n" +
            "where h.id= :hallId", nativeQuery = true)
    List<Row> findByHall_Id(Integer hallId);



    @Query(nativeQuery = true, value = "select r.id as id, r.number as number\n" +
            "from hall_rows r\n" +
            "         join seats s on r.id = s.row_id\n" +
            "where s.id = :id")
    List<RowProjection> findBySeatId (Integer id);
}
