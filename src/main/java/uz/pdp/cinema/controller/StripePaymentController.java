package uz.pdp.cinema.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//Asilbek Fayzullayev 31.03.2022 8:49
@RestController
@RequiredArgsConstructor
public class StripePaymentController {
    @RequestMapping("/succuss")
    public ResponseEntity<?>getSuccess(){
        return ResponseEntity.ok("To'lov amalga oshirildi");
    }
}
