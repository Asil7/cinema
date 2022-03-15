package uz.pdp.cinema.model;

//Asilbek Fayzullayev 14.03.2022 17:08

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.security.SecureRandom;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity(name = "attachment_content")
public class AttachmentContent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    byte[] data;
    @OneToOne
    private Attachment attachment;

    public AttachmentContent(byte[] data, Attachment attachment) {
        this.data = data;
        this.attachment = attachment;
    }
}
