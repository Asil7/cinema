package uz.pdp.cinema.model;

//Asilbek Fayzullayev 14.03.2022 21:38

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import uz.pdp.cinema.model.enums.TicketStatus;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity(name = "transaction_history")
public class TransactionHistory {
    @Id
    @GeneratedValue
    private Integer id;

    @OrderBy
    @CreationTimestamp
    @Column(nullable = false, updatable = false)
    private Timestamp createdAt;


    @ManyToMany
    @JoinTable(name = "transactions_histories_tickets",
            joinColumns = {@JoinColumn(name = "transaction_history_id")},
            inverseJoinColumns = {@JoinColumn(name = "ticket_id")})
    private List<Ticket> ticketList;

    @OneToOne
    private PayType payType;

    private Double totalAmount;

    private String paymentIntent;

    private boolean isRefunded;

    public TransactionHistory(List<Ticket> ticketList, PayType payType, Double totalAmount,
                               String paymentIntent, boolean isRefunded) {
        this.ticketList = ticketList;
        this.payType = payType;
        this.totalAmount = totalAmount;
        this.paymentIntent = paymentIntent;
        this.isRefunded = isRefunded;
    }
}
