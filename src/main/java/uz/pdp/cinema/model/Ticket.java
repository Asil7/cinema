package uz.pdp.cinema.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import uz.pdp.cinema.model.enums.TicketStatus;

import javax.persistence.*;

//Asilbek Fayzullayev 14.03.2022 21:34

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity(name = "tickets")
public class Ticket {
    @Id
    @GeneratedValue
    private Integer id;

    @OneToOne
    private MovieSession movieSession;

    @OneToOne
    private Seat seat;

    @OneToOne
    private Attachment qrCode;

    private double price;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private TicketStatus ticketStatus;

    @ManyToOne
    User user;

    public Ticket(MovieSession movieSession, Seat seat, Attachment qrCode, double price, TicketStatus ticketStatus, User user) {
        this.movieSession = movieSession;
        this.seat = seat;
        this.qrCode = qrCode;
        this.price = price;
        this.ticketStatus = ticketStatus;
        this.user = user;
    }
}
