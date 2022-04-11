package uz.pdp.cinema.controller;

//Asilbek Fayzullayev 31.03.2022 9:27   

import com.stripe.Stripe;
import com.stripe.exception.SignatureVerificationException;
import com.stripe.model.Event;
import com.stripe.model.checkout.Session;
import com.stripe.net.Webhook;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import uz.pdp.cinema.repository.TransactionHistoryRepository;
import uz.pdp.cinema.service.TransactionHistoryService;
import uz.pdp.cinema.service.TicketService;

@RestController
@RequestMapping("/api/stripe-webhook")
@RequiredArgsConstructor
public class StripeEventController {

    public final TicketService ticketService;
    public final TransactionHistoryService transactionHistoryService;

    @Value("${STRIPE_APIKEY}")
    String stripeApiKey;

    @Value("${STRIPE_WEBHOOK}")
    String endpointSecret;

    @PostMapping
    public Object handle(@RequestBody String payload, @RequestHeader(name = "Stripe-Signature") String sigHeader) {

        Stripe.apiKey = stripeApiKey;

        Event event = null;

        try {
            event = Webhook.constructEvent(payload, sigHeader, endpointSecret);
        }  catch (SignatureVerificationException e) {
            e.printStackTrace();
            return "";


        }catch (Exception e){
            e.printStackTrace();
        }


        // Handle the checkout.session.completed event
        if ("checkout.session.completed".equals(event.getType())) {
            Session session = (Session) event.getDataObjectDeserializer().getObject().get();

            // Fulfill the transaction...
            fulfillOrder(session);
        }

        System.out.println("Got payload: " + payload);

        return "";
    }


    public void fulfillOrder(Session session) {
        System.out.println("CurrentUserID: " + session.getClientReferenceId());
        String userId = session.getClientReferenceId();
        String paymentIntent = session.getPaymentIntent();
//        ticketService.changeTicketStatusToTransaction(Integer.valueOf(userId),paymentIntent);
       // ticketService.addTransactionHistory(Integer.valueOf(userId),session.getPaymentIntent());
        ticketService.addTransactionHistory(Integer.valueOf(userId),paymentIntent);
        ticketService.changeTicketStatusToPurchase(Integer.valueOf(userId));
    }
}
