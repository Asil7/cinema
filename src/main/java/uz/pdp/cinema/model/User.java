package uz.pdp.cinema.model;

//Asilbek Fayzullayev 14.03.2022 15:03

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.security.SecureRandom;
import java.util.Date;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity (name = "users")
public class User {
    @Id
    @GeneratedValue
    private Integer id;

    @Column(nullable = false)
    public String fullName;

    @Column(nullable = false,unique = true)
    public String  userName;

    @Column(nullable = false)
    public String password;

    public Date date_of_birth;


    @ManyToMany(cascade = CascadeType.ALL)
    private List<Role> role;

    @ManyToMany(cascade = CascadeType.ALL)
    private List<Permission> permission;

}
