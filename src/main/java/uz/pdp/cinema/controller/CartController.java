package uz.pdp.cinema.controller;

//Asilbek Fayzullayev 27.03.2022 20:11   

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.cinema.payload.ApiResponse;
import uz.pdp.cinema.service.CartService;

@RestController
@RequestMapping("/api/cart")
public class CartController {
    @Autowired
    CartService cartService;

    @GetMapping("/{userId}")
    public HttpEntity showTicketsInTheCart(@PathVariable Integer userId) {
        ApiResponse ticketInTheCart = cartService.getTicketInTheCart(userId);
        return ResponseEntity.status(ticketInTheCart.isSuccess() ? 200 : 409).body(ticketInTheCart);
    }

    @PostMapping("/{userId}")
    public ResponseEntity addTicketToCart(@PathVariable Integer userId, Integer seatId,  Integer movieSessionId) {
        return cartService.addToCart(userId, seatId, movieSessionId);
    }

    @DeleteMapping("/{userId}/{ticketId}")
    public HttpEntity deleteTicketFromCart(@PathVariable Integer userId, @PathVariable Integer ticketId) {
        ApiResponse apiResponse = cartService.deleteTicketFromCart(userId, ticketId);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 409).body(apiResponse);
    }

    @DeleteMapping("/clear/{userId}")
    public HttpEntity clearCart(@PathVariable Integer userId) {
        ApiResponse apiResponse = cartService.clearCart(userId);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 409).body(apiResponse);
    }


    @GetMapping
    public HttpEntity allTicket(){
        ApiResponse allTicket = cartService.getAllTicket();
        return ResponseEntity.status(allTicket.isSuccess()?200:400).body(allTicket);
    }




}
