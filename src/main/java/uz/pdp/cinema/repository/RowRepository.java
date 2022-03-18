package uz.pdp.cinema.repository;

//Asilbek Fayzullayev 16.03.2022 16:30   

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import uz.pdp.cinema.model.Row;

import java.util.List;
import java.util.UUID;

public interface RowRepository extends JpaRepository<Row,Integer> {
    @Query(value = "select cast (r.id as varchar) as id, r.number, h.name as Name from hall_rows r join halls h on h.id = r.hall_id where h.id= :hallId",nativeQuery = true)
    List<Row> findByHall_Id(Integer hallId);
}
