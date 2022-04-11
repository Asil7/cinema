package uz.pdp.cinema.service;

//Asilbek Fayzullayev 16.03.2022 16:30   

import com.sun.xml.internal.ws.api.pipe.helper.AbstractPipeImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import uz.pdp.cinema.model.Hall;
import uz.pdp.cinema.model.Row;
import uz.pdp.cinema.payload.ApiResponse;
import uz.pdp.cinema.repository.HallRepository;
import uz.pdp.cinema.repository.RowRepository;

import java.util.List;
import java.util.Optional;


@Service
public class RowService {

    @Autowired
    RowRepository rowrepository;
    @Autowired
    HallRepository hallRepository;

    public ApiResponse getRowsByHallId(Integer id) {
        Optional<Hall> optionalHall = hallRepository.findById(id);
        if (!optionalHall.isPresent()) {
            return new ApiResponse("Hall not found", false);
        }
        List<Row> rowsByHallId = rowrepository.findByHall_Id(id);
        if (rowsByHallId.size() == 0) {
            return new ApiResponse("List empty", false);
        }
        return new ApiResponse("Success", true, rowsByHallId);
    }

    public ApiResponse getAllRows() {
        List<Row> rowList = rowrepository.findAll();
        if (rowList.size() == 0) {
            return new ApiResponse("List empty", false);
        }
        return new ApiResponse("Success", true, rowList);
    }

    public ApiResponse getRowById(Integer id) {
        Optional<Row> optionalRow = rowrepository.findById(id);
        if (!optionalRow.isPresent()) {
            return new ApiResponse("Row not found", false);
        }
        return new ApiResponse("Success", true, optionalRow);
    }

    public ApiResponse addRow(Integer id, Row row) {
        Optional<Hall> optionalHall = hallRepository.findById(id);
        if (!optionalHall.isPresent()) {
            return new ApiResponse("Hall not found", false);
        }
        try {
            Row save = rowrepository.save(new Row(row.getNumber(), optionalHall.get()));
            return new ApiResponse("Row saved", true, save);
        } catch (Exception e) {
            return new ApiResponse("Error! Maybe row already exists!!", false);
        }
    }

    public ApiResponse editRow(Integer id, Row row) {
        Optional<Row> rowOptional = rowrepository.findById(id);
        if (!rowOptional.isPresent()) {
            return new ApiResponse("Row not found", false);
        }
        try {
            Row row1 = rowOptional.get();
            row1.setNumber(row.getNumber());
            Row save = rowrepository.save(row1);
            return new ApiResponse("Successfully edited", true, save);
        } catch (Exception e) {
            return new ApiResponse("Row not found", false);
        }
    }

    public ApiResponse deleteRow(Integer id) {
        Optional<Row> byId = rowrepository.findById(id);
        if (!byId.isPresent()) {
            return new ApiResponse("Row not found", false);
        }
        hallRepository.deleteById(byId.get().getId());
        return new ApiResponse("Successfully deleted", true);
    }
}