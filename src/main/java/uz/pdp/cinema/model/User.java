package uz.pdp.cinema.model;

//Asilbek Fayzullayev 14.03.2022 15:03

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import uz.pdp.cinema.model.enums.Gender;

import javax.persistence.*;
import java.security.SecureRandom;
import java.time.LocalDate;
import java.util.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity(name = "users")
public class User implements UserDetails {
    @Id
    @GeneratedValue
    private Integer id;

    @Column(nullable = false)
    private String fullName;

    @Column(nullable = false, unique = true)
    private String userName;

    @Column(nullable = false)
    private String password;

    private LocalDate dateOfBirth;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    @ManyToMany(cascade = CascadeType.MERGE)
    private Set<Role> role;

//    @ManyToMany(cascade = CascadeType.ALL)
//    private Set<Permission> permission;

//    @Override
//    public Collection<? extends GrantedAuthority> getAuthorities() {
//        Set<String> permissions = new HashSet<>();
//        for (Role role : this.role) {
//            role.getPermissionList().forEach(permission -> permissions.add(permission.getName()));
//        }
//        this.permission.forEach(permission -> permissions.add(permission.getName()));
//        Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
//        permissions.forEach(permission -> grantedAuthorities.add(new SimpleGrantedAuthority(permission)));
//        return grantedAuthorities;
//    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Set<GrantedAuthority> grantedAuthorities = new HashSet<>();

        for (Role role : this.role) {
            grantedAuthorities.add(new SimpleGrantedAuthority(role.getRole()));
        }
        return grantedAuthorities;
    }

    @Override
    public String getUsername() {
        return this.userName;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public User(String fullName, String userName, String password, LocalDate dateOfBirth, Gender gender, Set<Role> role) {
        this.fullName = fullName;
        this.userName = userName;
        this.password = password;
        this.dateOfBirth = dateOfBirth;
        this.gender = gender;
        this.role = role;

    }

    public User(String fullName, String userName, String password) {
        this.fullName = fullName;
        this.userName = userName;
        this.password = password;
    }
}
