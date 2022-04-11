package uz.pdp.cinema.service;

//Asilbek Fayzullayev 03.04.2022 18:31

import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.Refund;
import com.stripe.model.checkout.Session;
import com.stripe.param.RefundCreateParams;
import com.stripe.param.checkout.SessionCreateParams;
import freemarker.template.Configuration;
import freemarker.template.TemplateException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import uz.pdp.cinema.dto.TicketDto;
import uz.pdp.cinema.dto.TicketMailDto;
import uz.pdp.cinema.dto.TicketMailWithAttachmentDto;
import uz.pdp.cinema.model.Ticket;
import uz.pdp.cinema.model.TransactionHistory;
import uz.pdp.cinema.model.User;
import uz.pdp.cinema.model.enums.TicketStatus;
import uz.pdp.cinema.payload.ApiResponse;
import uz.pdp.cinema.projection.CustomTicket;
import uz.pdp.cinema.repository.TicketRepository;
import uz.pdp.cinema.repository.TransactionHistoryRepository;
import uz.pdp.cinema.repository.UserRepository;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.StringWriter;
import java.util.*;

@Service
@RestController
@RequiredArgsConstructor
public class TransactionService {
    private final Configuration configuration;
    private final JavaMailSender mailSender;

    @Value("${STRIPE_APIKEY}")
    String stripeApiKey;

    private final UserRepository userRepository;

    private final TransactionHistoryRepository transactionHistoryRepository;

    private final TicketRepository ticketRepository;

    @GetMapping
    public HttpEntity<?> createStripeSession() {

        Stripe.apiKey = stripeApiKey;

        Optional<User> optionalUser = userRepository.findByFullName("asil");
        User user = optionalUser.get();
// TODO: 06.04.2022
        List<CustomTicket> tickets = userRepository.getTicketsInTheCart(user.getId());

        List<SessionCreateParams.LineItem> lineItems = new ArrayList<>();

        for (CustomTicket ticket : tickets) {
            String movieName = ticket.getMovieName();
            Double price = ticket.getPrice();
            double chargeAmount = (price * 100 + 30) / (1 - 2.9 / 100);


            SessionCreateParams.LineItem.PriceData.ProductData productData = SessionCreateParams.LineItem.PriceData.ProductData
                    .builder()
                    .setName(movieName)
                    .build();


            SessionCreateParams.LineItem.PriceData priceData = SessionCreateParams.LineItem.PriceData
                    .builder()
                    .setProductData(productData)
                    .setCurrency("usd")
                    .setUnitAmount((long) (chargeAmount))
                    .build();

            SessionCreateParams.LineItem lineItem = SessionCreateParams.LineItem
                    .builder()
                    .setPriceData(priceData)
                    .setQuantity(1L)
                    .build();

            lineItems.add(lineItem);

        }

        SessionCreateParams params = SessionCreateParams
                .builder()
                .setMode(SessionCreateParams.Mode.PAYMENT)
                .setCancelUrl("http://localhost:8080/failed")
                .setSuccessUrl("http://localhost:8080/success?userId=" + user.getId())
                .addAllLineItem(lineItems)
                .setClientReferenceId(user.getId().toString())
                .build();

        try {
            Session session = Session.create(params);
            String checkoutUrl = session.getUrl();
            return ResponseEntity.ok(checkoutUrl);

        } catch (StripeException e) {
            e.printStackTrace();
        }
        return ResponseEntity.badRequest().build();
    }


    public HttpEntity<?> firstRefundTicket(List<TicketDto> ticketDtoList) {
        Stripe.apiKey = stripeApiKey;


        TransactionHistory transactionHistoryByTicketId = transactionHistoryRepository
                .findTransactionHistoryByTicketId(ticketDtoList.get(0).getTicketId()).orElseThrow(() ->
                        new IllegalStateException("Not found"));
        String stripePaymentIntent = transactionHistoryByTicketId.getPaymentIntent();
        Refund refund = null;
        double amount = 0;

        List<Ticket> tickets = new ArrayList<>();
        for (TicketDto ticketDto : ticketDtoList) {
            Ticket ticket = ticketRepository.findById(ticketDto.getTicketId()).get();
            ticket.setTicketStatus(TicketStatus.REFUNDED);
            tickets.add(ticket);

//            Double price = ticket.getPrice();
//            amount += price - price * percent / 100;

            amount += ticket.getPrice();
        }

        RefundCreateParams params = RefundCreateParams
                .builder()
                .setPaymentIntent(stripePaymentIntent)
                .setAmount((long) amount)
                .build();

        try {
            refund = Refund.create(params);
            System.out.println(refund.getStatus());
            if (refund.getStatus().equals("succeeded")) {
                TransactionHistory transactionHistory = new TransactionHistory(tickets, null, amount, null, true);
                transactionHistoryRepository.save(transactionHistory);
                return ResponseEntity.ok(new ApiResponse("successfully refund", true));
            }
        } catch (StripeException e) {
            e.printStackTrace();
        }
        return ResponseEntity.ok(refund.getStatus());
    }


    public HttpEntity<?> sendEmailNotification(Integer ticketId, String to) {

        Ticket ticket = ticketRepository.findById(ticketId).get();

        SimpleMailMessage message = new SimpleMailMessage();

        message.setFrom("asil.biohazard@gmail.com");
        message.setTo(to);
        message.setSubject("Good!!!");
        String date = ticket.getMovieSession().getStartDate().getDate().toString();
        String time = ticket.getMovieSession().getStartTime().getTime().toString();
        String name = ticket.getMovieSession().getMovieAnnouncement().getMovie().getTitle();
        String seat = ticket.getSeat().getNumber().toString();
        String hall = ticket.getSeat().getRow().getHall().getName();
        String row = ticket.getSeat().getRow().getNumber().toString();
        String inputMsg = String.format("Your Ticket!\n" +
                        " Date: %s\n" +
                        " Time: %s\n" +
                        " Name: %s\n" +
                        " Seat: %s\n" +
                        " Row: %s\n" +
                        " Hall: %s",
                date, time, name, seat, row, hall);
        message.setText(inputMsg);

        mailSender.send(message);

        return ResponseEntity.ok("Email Successfully!");
    }


    public HttpEntity<?> sendHtmlMassage(TicketMailDto ticketMailDto) throws MessagingException, TemplateException, IOException {
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);
        helper.setSubject(ticketMailDto.getSubject());
        helper.setTo(ticketMailDto.getTo());
        String emailContent = getEmailContent(ticketMailDto);
        helper.setText(emailContent, true);
        mailSender.send(message);
        return ResponseEntity.ok("Success!");
    }


    String getEmailContent(TicketMailDto ticketMailDto) throws IOException, TemplateException {
        StringWriter stringWriter = new StringWriter();
        Map<String, Object> model = new HashMap<>();
        model.put("ticketMailDto", ticketMailDto);
        configuration.getTemplate("email.html").process(model, stringWriter);
        return stringWriter.getBuffer().toString();
    }
}

