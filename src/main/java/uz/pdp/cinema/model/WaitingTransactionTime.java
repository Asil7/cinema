package uz.pdp.cinema.model;

//Asilbek Fayzullayev 14.03.2022 21:40

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class WaitingTransactionTime {
    @Id
    @GeneratedValue
    private Integer id;

    private int minute;

    @OrderBy
    @CreationTimestamp
    @Column(nullable = false, updatable = false)
    private Timestamp createdAt;

}
