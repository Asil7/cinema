package uz.pdp.cinema.controller;

//Asilbek Fayzullayev 14.03.2022 23:50

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uz.pdp.cinema.model.Distributor;
import uz.pdp.cinema.repository.DistributorRepository;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/distributor")
public class DistributorController {

    @Autowired
    DistributorRepository distributorRepository;

    // Add Distributor
    @PostMapping()
    public String addDistributor(@RequestBody Distributor distributor){
        boolean b = distributorRepository.existsByName(distributor.getName());
        if (b){
            return "This distributor already exist";
        }
        distributor.setName(distributor.getName());
        distributor.setDescription(distributor.getDescription());
        distributorRepository.save(distributor);
        return "Distributor added";
    }


    // Read Distributor
    @GetMapping()
    public List<Distributor> getDistributor(){
        return distributorRepository.findAll();
    }


    // Edit Distributor
    @PutMapping("/{id}")
    public String editDistributor(@PathVariable Integer id,@RequestBody Distributor distributor){
        Optional<Distributor> byId = distributorRepository.findById(id);
        if (byId.isPresent()){
            Distributor distributor1 =byId.get();
            distributor1.setName(distributor.getName());
            distributor1.setDescription(distributor.getDescription());
            distributorRepository.save(distributor1);
            return "Distributor edited";
        }
        return "Distributor not found";
    }


    // Read by id
    @GetMapping("/{id}")
    public Optional<Distributor> getById(@PathVariable Integer id){
        Optional<Distributor> byId = distributorRepository.findById(id);
        return byId;
    }


    // Delete Distributor
    @DeleteMapping("/{id}")
    public String deleteDistributor(@PathVariable Integer id){
        distributorRepository.deleteById(id);
        return "Distributor deleted";
    }
}
