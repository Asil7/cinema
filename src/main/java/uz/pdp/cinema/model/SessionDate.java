package uz.pdp.cinema.model;

//Asilbek Fayzullayev 14.03.2022 18:28

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class SessionDate {
    @Id
    @GeneratedValue
    private Integer id;
    @ManyToOne
    private MovieSession movieSession;
    private Date date;

}
