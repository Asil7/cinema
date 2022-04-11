package uz.pdp.cinema.model;

//Asilbek Fayzullayev 14.03.2022 15:09

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity(name = "permissions")
public class Permission {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String name;

    @ManyToMany
    private Set<Role> role ;

    public Permission(String name, Set<Role> role) {
        this.name = name;
        this.role = role;
    }
}
