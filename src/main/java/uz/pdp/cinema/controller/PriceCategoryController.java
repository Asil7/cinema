package uz.pdp.cinema.controller;

//Asilbek Fayzullayev 16.03.2022 22:40   

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.cinema.model.PriceCategory;
import uz.pdp.cinema.payload.ApiResponse;
import uz.pdp.cinema.service.PriceCategoryService;

@RestController
@RequestMapping("/api/priceCategory")
public class PriceCategoryController {

    @Autowired
    PriceCategoryService priceCategoryService;

    @GetMapping
    public HttpEntity<?> getAllPriceCategory() {
        ApiResponse allPriceCategories = priceCategoryService.getAllPriceCategories();
        return ResponseEntity.status(allPriceCategories.isSuccess() ? 200 : 204).body(allPriceCategories);
    }

    @GetMapping("/{id}")
    public HttpEntity<?> getAllPriceCategoryById(@PathVariable Integer id) {
        ApiResponse priceCategoryById = priceCategoryService.getPriceCategoryById(id);
        return ResponseEntity.status(priceCategoryById.isSuccess() ? 200 : 204).body(priceCategoryById);
    }

    @PostMapping
    public HttpEntity<?> addPriceCategory(@RequestBody PriceCategory priceCategory) {
        ApiResponse apiResponse = priceCategoryService.addPriceCategory(priceCategory);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 204).body(apiResponse);
    }

    @PutMapping("/{id}")
    public HttpEntity<?> editPriceCategory(@PathVariable Integer id,@RequestBody PriceCategory priceCategory) {
        ApiResponse apiResponse = priceCategoryService.editPriceCategory(id, priceCategory);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 202).body(apiResponse);
    }

    @DeleteMapping("/{id}")
    public HttpEntity<?> delete( @PathVariable Integer id) {
        ApiResponse delete = priceCategoryService.delete(id);
        return ResponseEntity.status(delete.isSuccess() ? 200 : 202).body(delete);
    }
}
