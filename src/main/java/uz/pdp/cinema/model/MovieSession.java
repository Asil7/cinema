package uz.pdp.cinema.model;

//Asilbek Fayzullayev 14.03.2022 18:23

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity(name = "movie_session")
public class MovieSession {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    private MovieAnnouncement movieAnnouncement;

    @ManyToOne
    private Hall hall;

    @ManyToOne
    private SessionDate startDate;

    @ManyToOne
    private SessionTime startTime;

    @ManyToOne
    private SessionTime endTime;

    public MovieSession(MovieAnnouncement movieAnnouncement, Hall hall, SessionDate startDate, SessionTime startTime, SessionTime endTime) {
        this.movieAnnouncement = movieAnnouncement;
        this.hall = hall;
        this.startDate = startDate;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    @Override
    public String toString() {
        return "MovieSession{" +
                "id=" + id +
                ", hall=" + hall +
                ", startDate=" + startDate +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                '}';
    }
}
