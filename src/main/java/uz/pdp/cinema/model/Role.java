package uz.pdp.cinema.model;

//Asilbek Fayzullayev 14.03.2022 15:13

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import uz.pdp.cinema.model.enums.RoleEnum;

import javax.persistence.*;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity(name = "roles")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String role;

    @ManyToMany
    List<Permission> permissionList;


    public Role(String role, List<Permission> permissionList) {
        this.role = role;
        this.permissionList = permissionList;
    }
}
