package uz.pdp.cinema.dto;

//Asilbek Fayzullayev 05.04.2022 8:12   


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class TicketMailWithAttachmentDto {
    private String to;
    private String message;
    private String subject;
    private Integer ticketId;
}
