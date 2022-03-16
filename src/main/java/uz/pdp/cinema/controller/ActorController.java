package uz.pdp.cinema.controller;

import com.sun.corba.se.spi.presentation.rmi.IDLNameTranslator;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import uz.pdp.cinema.model.Actor;
import uz.pdp.cinema.payload.ApiResponse;
import uz.pdp.cinema.service.ActorService;

//Asilbek Fayzullayev 15.03.2022 10:27
@RestController
@RequestMapping("/api/actor")
@RequiredArgsConstructor

public class ActorController {
    @Autowired
    ActorService actorService;

    @GetMapping
    public HttpEntity<?> getAllActors() {
        ApiResponse allActors = actorService.getAllActors();
        return ResponseEntity.status(allActors.isSuccess() ? 200 : 204).body(allActors);
    }

    @GetMapping("/{id}")
    public HttpEntity<?> getActor(@PathVariable Integer id) {
        ApiResponse actorById = actorService.getActorById(id);
        return ResponseEntity.status(actorById.isSuccess() ? 200 : 204).body(actorById);
    }

    @PostMapping()
    public HttpEntity<?> addActor(@RequestPart("file") MultipartFile file, @RequestPart("actor") Actor actor){
        ApiResponse apiResponse = actorService.addActor(file, actor);
        return ResponseEntity.status(apiResponse.isSuccess()?200:409).body(apiResponse);
    }

    @PutMapping("/{id}")
    public HttpEntity<?> editActor(@PathVariable Integer id,@RequestPart("file") MultipartFile file, @RequestPart("actor") Actor actor){
        ApiResponse apiResponse = actorService.editActor(id, file, actor);
        return ResponseEntity.status(apiResponse.isSuccess()?200:409).body(apiResponse);
    }

    @DeleteMapping("/{id}")
    public HttpEntity<?> deleteActor(@PathVariable Integer id){
        ApiResponse apiResponse = actorService.deleteActor(id);
        return ResponseEntity.status(apiResponse.isSuccess()?200:409).body(apiResponse);
    }


}
