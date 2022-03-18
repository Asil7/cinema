package uz.pdp.cinema.service;

//Asilbek Fayzullayev 16.03.2022 22:38   

import com.sun.xml.internal.ws.api.pipe.helper.AbstractPipeImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.cinema.model.PriceCategory;
import uz.pdp.cinema.payload.ApiResponse;
import uz.pdp.cinema.repository.PriceCategoryRepository;

import java.util.List;
import java.util.Optional;

@Service
public class PriceCategoryService {

    @Autowired
    PriceCategoryRepository priceCategoryRepository;

    public ApiResponse getAllPriceCategories() {
        List<PriceCategory> priceCategoryList = priceCategoryRepository.findAll();
        if (priceCategoryList.size() == 0) {
            return new ApiResponse("List empty", false, priceCategoryList);
        }
        return new ApiResponse("Success", true, priceCategoryList);
    }

    public ApiResponse getPriceCategoryById(Integer id) {
        Optional<PriceCategory> optionalPriceCategory = priceCategoryRepository.findById(id);
        if (!optionalPriceCategory.isPresent()) {
            return new ApiResponse("PriceCategory not found", false);
        }
        return new ApiResponse("Success", true, optionalPriceCategory);
    }

    public ApiResponse addPriceCategory(PriceCategory priceCategory) {
        try {
            PriceCategory savePriceCategory = priceCategoryRepository.save(priceCategory);
            return new ApiResponse("Successfully added!", true, savePriceCategory);
        } catch (Exception e) {
            return new ApiResponse("Error", false);
        }
    }

    public ApiResponse editPriceCategory(Integer id, PriceCategory priceCategory) {
        Optional<PriceCategory> optionalPriceCategory = priceCategoryRepository.findById(id);
        if (!optionalPriceCategory.isPresent()) {
            return new ApiResponse("PriceCategory not found", false);
        }
        PriceCategory editPriceCategory = optionalPriceCategory.get();
        editPriceCategory.setName(priceCategory.getName());
        editPriceCategory.setColor(priceCategory.getColor());
        editPriceCategory.setAdditionalFeeInPercent(priceCategory.getAdditionalFeeInPercent());
        PriceCategory save = priceCategoryRepository.save(editPriceCategory);
        return new ApiResponse("Successfully edited", true, save);
    }

    public ApiResponse delete (Integer id){
        Optional<PriceCategory> byId = priceCategoryRepository.findById(id);
        if (!byId.isPresent()){
            return new ApiResponse("PriceCategory",false);
        }
        priceCategoryRepository.deleteById(byId.get().getId());
        return new ApiResponse("Successfully deleted",true);
    }
}

