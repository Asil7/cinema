package uz.pdp.cinema.controller;

//Asilbek Fayzullayev 30.03.2022 16:12   

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/success")
public class Success {

    @GetMapping
    public String getSuccess()
    {
        return "success";
    }
}
