package uz.pdp.cinema.service;

//Asilbek Fayzullayev 22.03.2022 22:58   

import jdk.internal.dynalink.support.CompositeGuardingDynamicLinker;
import lombok.RequiredArgsConstructor;
import org.apache.catalina.mbeans.SparseUserDatabaseMBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import sun.java2d.pipe.AAShapePipe;
import uz.pdp.cinema.dto.SeatDto;
import uz.pdp.cinema.model.Seat;
import uz.pdp.cinema.payload.ApiResponse;
import uz.pdp.cinema.projection.AvailableSeatsProjection;
import uz.pdp.cinema.repository.PriceCategoryRepository;
import uz.pdp.cinema.repository.RowRepository;
import uz.pdp.cinema.repository.SeatRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SeatService {
    @Autowired
    RowRepository rowRepository;
    @Autowired
    PriceCategoryRepository priceCategoryRepository;
    @Autowired
    SeatRepository seatRepository;

    public ApiResponse getAllSeatsByPage(int page, int size) {
        Pageable pageable = PageRequest.of(page - 1, size);
        Page<Seat> seatPage = seatRepository.findAll(pageable);
        if (seatPage.getSize() == 0) {
            return new ApiResponse("List empty", false);
        }
        return new ApiResponse("Success", true, seatPage);
    }


    public ApiResponse getAllSeats() {
        List<Seat> allSeats = seatRepository.findAll();
        if (allSeats.size() == 0) {
            return new ApiResponse("Seats not found", false);
        }
        return new ApiResponse("Success", true, allSeats);
    }

    public ApiResponse getSeatByRowId(Integer id) {
        List<Seat> byRowId = seatRepository.findByRowId(id);
        if (byRowId.size() == 0) {
            return new ApiResponse("List empty", false);
        }
        return new ApiResponse("Success", true, byRowId);
    }

    public ApiResponse getSeatByHallId(Integer id) {
        List<Seat> byHallId = seatRepository.findByHallId(id);
        if (byHallId.size() == 0) {
            return new ApiResponse("List empty", false);
        }
        return new ApiResponse("Success", true, byHallId);
    }

    public ApiResponse getSeatById(Integer id) {
        Optional<Seat> seatById = seatRepository.findById(id);
        if (!seatById.isPresent()) {
            return new ApiResponse("Saet not found", false);
        }
        return new ApiResponse("Success", true, seatById);
    }

    public ApiResponse addSeat(SeatDto seatDto) {
        try {
            Seat seat = new Seat();
            seat.setNumber(seatDto.getNumber());
            seat.setRow(rowRepository.getById(seatDto.getRowId()));
            seat.setPriceCategory(priceCategoryRepository.getById(seatDto.getPriceCategoryId()));
            Seat saveSeat = seatRepository.save(seat);
            return new ApiResponse("Successfully added", true, saveSeat);
        } catch (Exception e) {
            return new ApiResponse("Error! Maybe seat already exists", false);
        }
    }

    public ApiResponse editSeat(Integer id, SeatDto seatDto) {
        Optional<Seat> optionalSeat = seatRepository.findById(id);
        if (!optionalSeat.isPresent()) {
            return new ApiResponse("Seat not found", false);
        }
        try {
            Seat editSeat = optionalSeat.get();
            editSeat.setNumber(seatDto.getNumber());
            editSeat.setRow(rowRepository.getById(seatDto.getRowId()));
            editSeat.setPriceCategory(priceCategoryRepository.getById(seatDto.getPriceCategoryId()));
            Seat savedSeat = seatRepository.save(editSeat);
            return new ApiResponse("Successfully", true, savedSeat);
        } catch (Exception e) {
            return new ApiResponse("Error! Maybe seat already exists!", false);
        }
    }

    public ApiResponse deleteSeat(Integer id) {
        try {
            seatRepository.findById(id);
            return new ApiResponse("Successfully deleted", true);
        } catch (Exception e) {
            return new ApiResponse("Seat not found", false);
        }
    }

    public ApiResponse getAvailableSeatsByMovieSessionId(Integer movieSessionId) {
        List<AvailableSeatsProjection> availableSeats = seatRepository.findAvailableSeatsBySessionId(movieSessionId);
        return new ApiResponse("success", true, availableSeats);
    }
}
