package uz.pdp.cinema.service;

//Asilbek Fayzullayev 25.03.2022 23:46   

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.ResourceAccessException;
import uz.pdp.cinema.model.*;
import uz.pdp.cinema.model.enums.TicketStatus;
import uz.pdp.cinema.payload.ApiResponse;
import uz.pdp.cinema.projection.CustomTicket;
import uz.pdp.cinema.repository.*;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class TicketService {

    @Autowired
    TicketRepository ticketRepository;

    @Autowired
    AttachmentContentRepository attachmentContentRepository;

    @Autowired
    AttachmentRepository attachmentRepository;

    @Autowired
    TransactionHistoryRepository transactionHistoryRepository;

    @Autowired
    UserRepository userRepository;


    public byte[] getQRCodeImage(String text, int width, int height) throws WriterException, IOException {
        QRCodeWriter qrCodeWriter = new QRCodeWriter();
        BitMatrix bitMatrix = qrCodeWriter.encode(text, BarcodeFormat.QR_CODE, width, height);
        ByteArrayOutputStream pngOutputStream = new ByteArrayOutputStream();
        MatrixToImageWriter.writeToStream(bitMatrix, "PNG", pngOutputStream);
        byte[] pngData = pngOutputStream.toByteArray();
        return pngData;
    }

//    public ResponseEntity transactionTicket(Integer ticketId) {
//        Optional<Ticket> byId = ticketRepository.findById(ticketId);
//        if (byId.isPresent()) {
//
//
//            Ticket ticket = byId.get();
//            User user = ticket.getUser();
//
//            Attachment attachment = getQrCodeAttachment(ticketId);
//            ticket.setQrCode(attachment);
//            ticket.setTicketStatus(TicketStatus.TRANSACTIOND);
//            TransactionHistory transactionHistory=new TransactionHistory();
//            transactionHistory.setTicket(ticket);
//            transactionHistory.setUser(user);
//
//            ticketRepository.save(ticket);
//            transactionHistoryRepository.save(transactionHistory);
//
//            return new ResponseEntity("Successfully transactiond " + attachment.getId(), HttpStatus.CREATED);
//
//        }
//        return new ResponseEntity("Ticket not found", HttpStatus.NOT_FOUND);
//    }


    public HttpEntity<?> getCurrentUserTickets(Integer userId) {
        List<CustomTicket> allTickets = userRepository.getTicketsInTheCart(userId);
        return ResponseEntity.ok(allTickets);
    }



    public Attachment getQrCodeAttachment(Ticket ticket) {
        try {
            byte[] qrCodeImage = new byte[0];
            qrCodeImage = getQRCodeImage(ticket.getId().toString(), 200, 200);
            Attachment attachment = new Attachment();
            attachment.setName(ticket.getId().toString());
            attachment.setContentType("image/png");
            AttachmentContent attachmentContent = new AttachmentContent(qrCodeImage, attachment);
            attachmentContentRepository.save(attachmentContent);
            Attachment savedQrCode = attachmentRepository.save(attachment);
            return savedQrCode;
        } catch (WriterException | IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void addTransactionHistory(Integer userId, String paymentIntent) {
        List<Ticket> ticketList = ticketRepository.findByUserIdAndTicketStatus(userId, TicketStatus.NEW);


        Double totalAmount = ticketList.stream().map(ticket -> ticket.getPrice()).
                collect(Collectors.toList()).stream().mapToDouble(value -> value).sum();

        boolean isRefunded = false;
        TransactionHistory transactionHistory = new TransactionHistory(
                ticketList, null, totalAmount,  paymentIntent, isRefunded);
        transactionHistoryRepository.save(transactionHistory);
    }

    public boolean changeTicketStatusToPurchase(Integer userId) {
        // TODO: 3/31/2022 user id boyicha tiketlar olinadi va statusi ozgartiriladi

        try {
            List<Ticket> ticketList = ticketRepository.findByUserIdAndTicketStatus(userId, TicketStatus.NEW);
            for (Ticket ticket : ticketList) {
                ticket.setTicketStatus(TicketStatus.PURCHASED);
                Attachment attachment = getQrCodeAttachment(ticket);
                ticket.setQrCode(attachment);
            }
            ticketRepository.saveAll(ticketList);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

}
