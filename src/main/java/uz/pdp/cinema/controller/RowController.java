package uz.pdp.cinema.controller;

//Asilbek Fayzullayev 16.03.2022 16:32   

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.cinema.model.Row;
import uz.pdp.cinema.payload.ApiResponse;
import uz.pdp.cinema.service.RowService;

@RestController
@RequestMapping("/api/row")
public class RowController {

    @Autowired
    RowService rowService;

    @GetMapping("/getRowsByHallId/{hallId}")
    public HttpEntity<?> getRowsByHallId(@PathVariable Integer hallId) {
        ApiResponse rowsByHallId = rowService.getRowsByHallId(hallId);
        return ResponseEntity.status(rowsByHallId.isSuccess() ? 200 : 204).body(rowsByHallId);
    }

    @GetMapping
    public HttpEntity<?> getAllRows() {
        ApiResponse allRows = rowService.getAllRows();
        return ResponseEntity.status(allRows.isSuccess() ? 200 : 204).body(allRows);
    }

    @GetMapping("/{id}")
    public HttpEntity<?> getRowById(@PathVariable Integer id) {
        ApiResponse rowById = rowService.getRowById(id);
        return ResponseEntity.status(rowById.isSuccess() ? 200 : 204).body(rowById);
    }

    @PostMapping("/{id}")
    public HttpEntity<?> addRows(@PathVariable Integer id, Row row) {
        ApiResponse apiResponse = rowService.addRow(id, row);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 204).body(apiResponse);
    }

    @PutMapping("/{id}")
    public HttpEntity<?> editRow(@PathVariable Integer id, @RequestBody Row row) {
        ApiResponse apiResponse = rowService.editRow(id, row);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 204).body(apiResponse);
    }

    @DeleteMapping("/{id}")
    public HttpEntity<?> deleteRow(@PathVariable Integer id) {
        ApiResponse apiResponse = rowService.deleteRow(id);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 204).body(apiResponse);
    }
}
