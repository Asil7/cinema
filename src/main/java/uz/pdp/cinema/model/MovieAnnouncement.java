package uz.pdp.cinema.model;

//Asilbek Fayzullayev 18.03.2022 15:40   

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity(name = "movie_announcements")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class MovieAnnouncement {
    @Id
    @GeneratedValue
    private Integer id;

    @OneToOne
    private Movie movie;

    private Boolean isActive;

    public MovieAnnouncement(Movie movie1, boolean b) {
    }
}
