package uz.pdp.cinema.controller;

//Asilbek Fayzullayev 30.03.2022 11:56

import com.fasterxml.classmate.members.ResolvedParameterizedMember;
import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.Refund;
import com.stripe.model.checkout.Session;
import com.stripe.param.RefundCreateParams;
import com.stripe.param.checkout.SessionCreateParams;
import freemarker.template.TemplateException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.cinema.dto.TicketDto;
import uz.pdp.cinema.dto.TicketMailDto;
import uz.pdp.cinema.dto.TicketMailWithAttachmentDto;
import uz.pdp.cinema.model.Ticket;
import uz.pdp.cinema.model.User;
import uz.pdp.cinema.model.enums.TicketStatus;
import uz.pdp.cinema.projection.CustomTicket;

import uz.pdp.cinema.repository.TicketRepository;
import uz.pdp.cinema.repository.TransactionHistoryRepository;
import uz.pdp.cinema.repository.UserRepository;
import uz.pdp.cinema.service.TransactionHistoryService;
import uz.pdp.cinema.service.TransactionService;

import javax.mail.MessagingException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;


@RestController
@RequiredArgsConstructor
public class TransactionController {

    private final TransactionService transactionService;


    @RequestMapping("/api/purchase")
    public HttpEntity<?> createStripeSession() {
        return transactionService.createStripeSession();
    }

    @RequestMapping(value = "/api/refund",method = RequestMethod.POST)
    public HttpEntity<?> refund(@RequestBody List<TicketDto> ticketDtoList) {
        return transactionService.firstRefundTicket(ticketDtoList);
    }


    @PostMapping("/api/email/{ticketId}")
    public HttpEntity<?> sendEmail( @PathVariable Integer ticketId,String to){
        return transactionService.sendEmailNotification(ticketId,to);
    }
}