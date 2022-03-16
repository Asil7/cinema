package uz.pdp.cinema.controller;

//Asilbek Fayzullayev 15.03.2022 23:49   

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.cinema.model.Hall;
import uz.pdp.cinema.payload.ApiResponse;
import uz.pdp.cinema.service.HallService;

@RestController
@RequestMapping("/api/hall")
@RequiredArgsConstructor
public class HallController {

    @Autowired
    HallService hallService;

    @GetMapping
    public HttpEntity<?> getAllHalls() {
        ApiResponse apiResponse = hallService.getAllHall();
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 204).body(apiResponse);
    }

    @GetMapping("/{id}")
    public HttpEntity<?> getHall(@PathVariable Integer id) {
        ApiResponse apiResponse = hallService.getHallById(id);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 409).body(apiResponse);
    }

    @PostMapping
    public HttpEntity<?> addHall(@RequestBody Hall hall) {
        ApiResponse apiResponse = hallService.addHall(hall);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 409).body(apiResponse);
    }

    @PutMapping("/{id}")
    public HttpEntity<?> editHall(@PathVariable Integer id, @RequestBody Hall hall) {
        ApiResponse apiResponse = hallService.editHall(id, hall);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 409).body(apiResponse);
    }

    @DeleteMapping("/{id}")
    public HttpEntity<?> deleteHall(@PathVariable Integer id) {
        ApiResponse apiResponse = hallService.deleteHall(id);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 404).body(apiResponse);
    }
}

