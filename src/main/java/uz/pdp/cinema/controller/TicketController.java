package uz.pdp.cinema.controller;

//Asilbek Fayzullayev 27.03.2022 20:42   

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.cinema.model.User;
import uz.pdp.cinema.payload.ApiResponse;
import uz.pdp.cinema.repository.UserRepository;
import uz.pdp.cinema.service.TicketService;

import java.beans.IntrospectionException;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/ticket")
@RequiredArgsConstructor
public class TicketController {
    private final UserRepository userRepository;
    private final TicketService ticketService;

// @GetMapping
// public HttpEntity<?> getCurrentUserTickets() {
//
//
//
//       Optional<User> currentUser = userRepository.findByFullName("asil");
//       return ticketService.getCurrentUserTickets(currentUser.g());
//     }
//
//   @GetMapping("/{ticketId}")
//   public HttpEntity<?> Ticket(@PathVariable UUID ticketId) {
//       ApiResponse apiResponse = ticketService.Ticket(ticketId);
//       return ResponseEntity.status(apiResponse.isStatus() ? 200 : 204).body(apiResponse);
//   }
//
 }