package uz.pdp.cinema.model;

//Asilbek Fayzullayev 14.03.2022 16:08

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;
import net.minidev.json.annotate.JsonIgnore;

import javax.persistence.*;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity(name = "halls")
public class Hall {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String name;


    @JsonIgnore
    @OneToMany(mappedBy = "hall", cascade = CascadeType.ALL)
    private List<Row> rows;

    private Double vipAdditionalFeeInPercent;

    public Hall(String name) {
        this.name = name;
    }

    public Hall(String name, Double vipAdditionalFeeInPercent) {
        this.name = name;
        this.vipAdditionalFeeInPercent = vipAdditionalFeeInPercent;
    }
}
