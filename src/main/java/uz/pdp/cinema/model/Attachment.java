package uz.pdp.cinema.model;

//Asilbek Fayzullayev 14.03.2022 17:05

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity(name = "attachments")
public class Attachment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private Long size;
    private String contentType;


    public Attachment(String name, Long size, String contentType) {
        this.name = name;
        this.size = size;
        this.contentType = contentType;
    }
}
