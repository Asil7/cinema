package uz.pdp.cinema.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import uz.pdp.cinema.model.TransactionHistory;
import uz.pdp.cinema.projection.CustomTransactionHistory;
import uz.pdp.cinema.projection.SoldTicket;


import javax.xml.crypto.Data;
import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface TransactionHistoryRepository extends JpaRepository<TransactionHistory,Integer> {

    @Query(nativeQuery = true,value = "select ph.id,\n" +
            "       t.id        as ticketId,\n" +
            "       u.id        as userId,\n" +
            "       u.full_name as userName,\n" +
            "       ph.created_at as createdAt\n" +
            "                      from transaction_history ph\n" +
            "    join tickets t on t.id = ph.ticket_id\n" +
            "    join users u on u.id = ph.user_id")
    List<CustomTransactionHistory> getAllTransactionHistories();

    @Query(nativeQuery = true,value = "select ph.id,\n" +
            "       t.id        as ticketId,\n" +
            "       u.id        as userId,\n" +
            "       u.full_name as userName,\n" +
            "       ph.created_at as createdAt\n" +
            "from transaction_history ph\n" +
            "         join tickets t on t.id = ph.ticket_id\n" +
            "         join users u on u.id = ph.user_id\n" +
            "where u.id=:userId")
    List<CustomTransactionHistory> getAllTransactionHistoryByUserId(Integer userId);




    @Query(nativeQuery = true,value = "select ph.id,\n" +
            "       t.id        as ticketId,\n" +
            "       u.id        as userId,\n" +
            "       u.full_name as userName,\n" +
            "       ph.created_at as createdAt\n" +
            "from transaction_history ph\n" +
            "         join tickets t on t.id = ph.ticket_id\n" +
            "         join users u on u.id = ph.user_id\n" +
            "where ph.created_at::date = :date")
    List<CustomTransactionHistory> getAllTransactionHistoryByDate(LocalDateTime date);


    @Query(nativeQuery = true,value = "select count(*) as amountSoldTickets from transaction_history\n" +
            "join tickets t on t.id = transaction_history.ticket_id\n" +
            "where created_at between :start and :end\n" +
            "and t.ticket_status='TRANSACTIOND'")
    List<SoldTicket> getTicketCount(LocalDate start, LocalDate end);



    @Query(nativeQuery = true,value = "select count(*) as amountSoldTickets\n" +
            "from transaction_history\n" +
            "         join tickets t on t.id = transaction_history.ticket_id\n" +
            "where created_at = :date\n" +
            "    and t.ticket_status = 'TRANSACTIOND'")
    List<SoldTicket> getTicketByDate(LocalDate date);


        @Query(nativeQuery = true,value = "select ph.*\n" +
                "              from\n" +
                "                            transaction_history ph\n" +
                "                                join transactions_histories_tickets pht on ph.id = pht.transaction_history_id\n" +
                "where pht.ticket_id = :ticketId")
        Optional<TransactionHistory> findTransactionHistoryByTicketId(Integer ticketId);

//        @Query(nativeQuery = true,value = "select * from transaction_history th\n" +
//                "join transactions_histories_tickets tht on th.id = tht.transaction_history_id\n" +
//                "where tht.ticket_id=:ticketId\n" +
//                "and th.isRefunded=false")
//            String getPaymentIntentByTicketId(Integer tickedId);

    @Query(nativeQuery = true,value ="\n" +
            "select th.payment_intent as paymentIntent from transaction_history th\n" +
            "join transactions_histories_tickets tht on th.id = tht.transaction_history_id\n" +
            "where tht.ticket_id = :ticketId\n" +
            "and th.is_refunded = false" )
    String getPaymentIntentByTicketId(Integer ticketId);

    @Query(nativeQuery = true,value = "select *\n" +
            "            from transactions_histories_tickets\n" +
            "            where ticket_id = :ticket_id")
        TransactionHistory findByTicketId(Integer ticket_id);
}

