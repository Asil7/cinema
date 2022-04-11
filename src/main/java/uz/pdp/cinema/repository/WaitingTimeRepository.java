package uz.pdp.cinema.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import uz.pdp.cinema.model.WaitingTransactionTime;

public interface WaitingTimeRepository extends JpaRepository<WaitingTransactionTime, Integer> {
    @Query(nativeQuery = true,
            value = "select minute from waiting_transaction_time\n" +
                    "order by created_at desc\n" +
                    "limit 1")
    Integer getWaitingMinute();
}
