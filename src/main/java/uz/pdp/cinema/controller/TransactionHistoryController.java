package uz.pdp.cinema.controller;

//Asilbek Fayzullayev 29.03.2022 11:20   

import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uz.pdp.cinema.service.TransactionHistoryService;


import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDate;

@RestController
@RequestMapping("/api/history")
public class TransactionHistoryController {
    @Autowired
    TransactionHistoryService transactionHistoryService;

    @GetMapping
    public ResponseEntity getAllTransactionHistory(){
       return transactionHistoryService.getAllTransactionHistories();
    }

    @GetMapping("/{userId}")
    public ResponseEntity getTransactionHistoryByUserId(@PathVariable Integer userId){
       return transactionHistoryService.getTransactionHistoryByUserId(userId);
    }

    @GetMapping("/moth")
    public ResponseEntity getTransactionHistoryByMoth(LocalDate start, LocalDate end){
        return transactionHistoryService.getTicketByMoth(start, end);
    }

    @GetMapping("/data")
    public ResponseEntity getTransactionHistoryByData(LocalDate data){
       return transactionHistoryService.getTicketByDate(data);
    }
}
