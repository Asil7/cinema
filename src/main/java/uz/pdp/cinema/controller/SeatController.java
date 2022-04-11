package uz.pdp.cinema.controller;

//Asilbek Fayzullayev 23.03.2022 14:26   

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.cinema.dto.SeatDto;
import uz.pdp.cinema.payload.ApiResponse;
import uz.pdp.cinema.projection.AvailableSeatsProjection;
import uz.pdp.cinema.projection.SeatProjection;
import uz.pdp.cinema.repository.SeatRepository;
import uz.pdp.cinema.service.SeatService;
import uz.pdp.cinema.util.Constant;

import java.util.List;

@RestController
@RequestMapping("/api/seat")
public class SeatController {

    @Autowired
    SeatService service;
    @Autowired
    SeatRepository seatRepository;

    @GetMapping("/getAllSeatByPage")
    public HttpEntity<?> getAllSeatsBuPage(@RequestParam(name = "page") int page, @RequestParam(name = "size", defaultValue = Constant.DEFAULT_PAGE_SIZE) int size) {
        ApiResponse allSeatsByPage = service.getAllSeatsByPage(page, size);
        return ResponseEntity.status(allSeatsByPage.isSuccess() ? 200 : 409).body(allSeatsByPage);
    }

    @GetMapping
    public HttpEntity<?> getAllSeat() {
        ApiResponse allSeats = service.getAllSeats();
        return ResponseEntity.status(allSeats.isSuccess() ? 200 : 409).body(allSeats);
    }

    @GetMapping("/getSeatByRowId/{rowId}")
    public HttpEntity<?> getSeatsByRowId(@PathVariable Integer rowId) {
        ApiResponse seatByRowId = service.getSeatByRowId(rowId);
        return ResponseEntity.status(seatByRowId.isSuccess() ? 200 : 409).body(seatByRowId);
    }

    @GetMapping("/getSeatsByHallId/{hallId}")
    public HttpEntity<?> getSeatsByHallId(@PathVariable Integer hallId) {
        ApiResponse seatByHallId = service.getSeatByHallId(hallId);
        return ResponseEntity.status(seatByHallId.isSuccess() ? 200 : 409).body(seatByHallId);
    }

    @GetMapping("/getSeatById/{seatId}")
    public HttpEntity<?> getSeatById(@PathVariable Integer seatId) {
        ApiResponse seatById = service.getSeatById(seatId);
        return ResponseEntity.status(seatById.isSuccess() ? 200 : 409).body(seatById);
    }

    @PostMapping
    public HttpEntity<?> addSeat(@RequestBody SeatDto seatDto) {
        ApiResponse apiResponse = service.addSeat(seatDto);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 409).body(apiResponse);
    }


    @PutMapping("/seatId")
    public HttpEntity<?> editSeat(@PathVariable Integer seatId, @RequestBody SeatDto seatDto) {
        ApiResponse apiResponse = service.editSeat(seatId, seatDto);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 409).body(apiResponse);
    }

    @DeleteMapping("/seatId")
    public HttpEntity<?> deleteSeat(Integer id) {
        ApiResponse apiResponse = service.deleteSeat(id);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 409).body(apiResponse);
    }



    @GetMapping("/available-seats/{movieSessionId}")
    public HttpEntity<?> getAvailableSeats(@PathVariable Integer movieSessionId){
        ApiResponse availableSeatsByMovieSessionId = service.getAvailableSeatsByMovieSessionId(movieSessionId);
        if (availableSeatsByMovieSessionId.isSuccess()){
            return ResponseEntity.ok(new ApiResponse("success", true, availableSeatsByMovieSessionId));
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse("not found", false));
    }
}
