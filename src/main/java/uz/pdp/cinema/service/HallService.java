package uz.pdp.cinema.service;

//Asilbek Fayzullayev 15.03.2022 23:49   


import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import uz.pdp.cinema.model.Hall;
import uz.pdp.cinema.payload.ApiResponse;
import uz.pdp.cinema.repository.HallRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class HallService {

    @Autowired
    HallRepository hallRepository;

    public ApiResponse getAllHall() {
        List<Hall> hallList = hallRepository.findAll();
        if (hallList.size() == 0) {
            return new ApiResponse("List Empty", false, hallList);
        }
        return new ApiResponse("Success", true, hallList);
    }

    public ApiResponse getHallById(Integer id) {
        Optional<Hall> hallId = hallRepository.findById(id);
        if (!hallId.isPresent()) {
            return new ApiResponse("Hall not found", false);
        }
        return new ApiResponse("Success", true, hallId);
    }

    public ApiResponse addHall(Hall hall) {
        try {
            Hall savedHale = hallRepository.save(hall);
            return new ApiResponse("Successfully added", true, savedHale);
        } catch (Exception e) {
            return new ApiResponse("Error,Maybe hall arlady exist", false);
        }
    }

    public ApiResponse editHall(Integer id, Hall hall) {
        Optional<Hall> optionalHall = hallRepository.findById(id);
        if (!optionalHall.isPresent()) {
            return new ApiResponse("Hall not found", false);
        }
        Hall editHall = optionalHall.get();
        editHall.setName(hall.getName());
        editHall.setVipAdditionalFeeInPercent(hall.getVipAdditionalFeeInPercent());
        Hall savedHall = hallRepository.save(editHall);
        return new ApiResponse("Successfully edited", true, savedHall);
    }

    public ApiResponse deleteHall(Integer id) {
        Optional<Hall> byId = hallRepository.findById(id);
        if (!byId.isPresent()) {
            return new ApiResponse("Hall not found", false);
        }
        hallRepository.deleteById(byId.get().getId());
        return new ApiResponse("Successfully deleted", true);
    }
}















