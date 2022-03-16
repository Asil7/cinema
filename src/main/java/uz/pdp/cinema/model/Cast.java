package uz.pdp.cinema.model;

//Asilbek Fayzullayev 16.03.2022 14:52   

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import uz.pdp.cinema.model.enums.CastType;

import javax.persistence.*;


@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity(name = "casts")
public class Cast {
    @Id
    @GeneratedValue
    private Integer id;

    @Column(nullable = false)
    private String fullName;

    @OneToOne
    private Attachment photo;

    @Enumerated(EnumType.STRING)
    private CastType castType;
}
