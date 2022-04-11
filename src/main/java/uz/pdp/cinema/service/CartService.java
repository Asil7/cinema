package uz.pdp.cinema.service;

//Asilbek Fayzullayev 27.03.2022 17:31   

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.ResourceAccessException;
import uz.pdp.cinema.model.*;
import uz.pdp.cinema.model.enums.TicketStatus;
import uz.pdp.cinema.payload.ApiResponse;
import uz.pdp.cinema.projection.CustomTicket;
import uz.pdp.cinema.projection.CustomTicketForCart;
import uz.pdp.cinema.repository.*;

import javax.persistence.criteria.CriteriaBuilder;
import javax.transaction.Transactional;
import java.util.*;

@Service
public class CartService {
    @Autowired
    TicketRepository ticketRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    SeatRepository seatRepository;

    @Autowired
    MovieSessionRepository movieSessionRepository;

    @Autowired
    WaitingTimeRepository waitingTimeRepository;

    @Autowired
    TransactionHistoryRepository transactionHistoryRepository;


    public ResponseEntity addToCart(Integer userId, Integer seatId, Integer movieSessionId) {
        CustomTicketForCart customTicket = ticketRepository.checkIfSeatIsAvailable(seatId, movieSessionId);
        if (customTicket != null){
            return new ResponseEntity("This seat is not available", HttpStatus.CONFLICT);
        }
        User user = userRepository.findById(userId).get();
        Seat seat = seatRepository.findById(seatId).get();
        MovieSession movieSession = movieSessionRepository.findById(movieSessionId).get();
        Ticket ticket = new Ticket(movieSession, seat, null, getPriceOfSeat(seatId, movieSessionId), TicketStatus.NEW, user);
        ticketRepository.save(ticket);
        Integer waitingMinute = waitingTimeRepository.getWaitingMinute();
        if (ticket != null) {
            TimerTask timerTask = new TimerTask() {
                @Override
                public void run() {
                    CustomTicketForCart ticketByIdForCart = ticketRepository.getTicketByIdForCart(ticket.getId());
                    try {
                        if (ticketByIdForCart.getStatus().equals(TicketStatus.NEW)) {
                            ticketRepository.deleteById(ticket.getId());
                            System.out.println("Ticket is deleted " + ticket.getId());
                        }
                    } catch (NullPointerException ignored) {
                    }
                }
            };

            scheduleDeleteTicket(ticket);


            return new ResponseEntity("Successfully added to cart", HttpStatus.CREATED);
        } else
            return ResponseEntity.badRequest().build();
    }


    private Double getPriceOfSeat(Integer seatId, Integer movieSessionId) {
            Double price = seatRepository.getPriceOfSeatBySeatIdAndMovieSessionId(seatId, movieSessionId);
        return price;
    }

    public ApiResponse getTicketInTheCart(Integer userId) {
        List<CustomTicket> tickets = userRepository.getTicketsInTheCart(userId);
        if ( !tickets.isEmpty()) {
            return new ApiResponse("Success", true, tickets);
        }
        return new ApiResponse("No tickets yet", false);
    }

    public ApiResponse getAllTicket(){
        List<CustomTicket> allTicketsInTheCart = userRepository.getAllTicketsInTheCart();
        if (allTicketsInTheCart.isEmpty()){
            return new ApiResponse("No ticket yet",false);
        }
        return new ApiResponse("Success",true,allTicketsInTheCart);
    }


    public ApiResponse clearCart(Integer userId) {
        userRepository.clearCartOfUser(userId);
        return new ApiResponse("Deleted", true);
    }

    public ApiResponse deleteTicketFromCart(Integer userId, Integer ticketId) {
        try {
            userRepository.deleteTicketFromCart(userId, ticketId);
            return new ApiResponse("Successfully deleted from the cart !!!", true);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ApiResponse("Conflict", false);
    }

    public void scheduleDeleteTicket(Ticket ticket) {
        try {
            TimerTask timerTask = new TimerTask() {
                @Override
                public void run() {
                    Ticket ticketFromDb = ticketRepository.findById(ticket.getId()).orElseThrow(() ->
                            new ResourceAccessException("Ticket not found"));
                    if (ticketFromDb.getTicketStatus().equals(TicketStatus.NEW)) {
                        System.out.println(ticketFromDb.getId() + "Ticket deleted..." + new Date());
                        ticketRepository.delete(ticketFromDb);
                    }
                }
            };

            Timer timer = new Timer();
            long delayTime = 6000000;

            System.out.println("Scheduler started..." + new Date());
            System.out.println("Delay: " + delayTime);

            timer.schedule(timerTask, delayTime);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
