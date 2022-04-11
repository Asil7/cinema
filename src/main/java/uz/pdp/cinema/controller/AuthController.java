package uz.pdp.cinema.controller;

//Asilbek Fayzullayev 06.04.2022 8:56   

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uz.pdp.cinema.dto.UserDto;

@Controller
@RequestMapping("/auth")
public class AuthController {
    
    @PostMapping
    public String login(@RequestBody UserDto userDto){
        return "";
        // TODO: 06.04.2022  
    }
}
