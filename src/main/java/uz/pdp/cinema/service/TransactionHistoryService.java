package uz.pdp.cinema.service;

//Asilbek Fayzullayev 29.03.2022 9:47   

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import uz.pdp.cinema.projection.CustomTransactionHistory;
import uz.pdp.cinema.projection.SoldTicket;
import uz.pdp.cinema.repository.TransactionHistoryRepository;
import uz.pdp.cinema.repository.TicketRepository;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.List;

@Service
public class TransactionHistoryService {
    @Autowired
    TransactionHistoryRepository transactionHistoryRepository;
    @Autowired
    TicketRepository ticketRepository;

    public ResponseEntity getAllTransactionHistories(){
        List<CustomTransactionHistory> allTransactionHistories = transactionHistoryRepository.getAllTransactionHistories();
        return ResponseEntity.ok(allTransactionHistories);
    }

    public ResponseEntity getTransactionHistoryByUserId(Integer userId){
        List<CustomTransactionHistory> allTransactionHistoryByUserId = transactionHistoryRepository.getAllTransactionHistoryByUserId(userId);
        return ResponseEntity.ok(allTransactionHistoryByUserId);
    }

    public ResponseEntity getTicketByMoth(LocalDate start, LocalDate end){
        List<SoldTicket> ticketCount = transactionHistoryRepository.getTicketCount(start, end);
        return ResponseEntity.ok(ticketCount);
    }

    public ResponseEntity getTicketByDate(LocalDate date){
        List<SoldTicket> ticketByDate = transactionHistoryRepository.getTicketByDate(date);
        return ResponseEntity.ok(ticketByDate);
    }

}
