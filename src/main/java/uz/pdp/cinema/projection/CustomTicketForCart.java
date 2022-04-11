package uz.pdp.cinema.projection;

import org.springframework.data.rest.core.config.Projection;
import uz.pdp.cinema.model.Ticket;


@Projection(types = Ticket.class)
public interface CustomTicketForCart {
    Integer getId();

    Ticket getStatus();
}
