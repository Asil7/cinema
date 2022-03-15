package uz.pdp.cinema.controller;

//Asilbek Fayzullayev 14.03.2022 22:26

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import uz.pdp.cinema.payload.ApiResponse;
import uz.pdp.cinema.service.AttachmentService;

import java.io.IOException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/attachment")

public class AttachmentController {

    @Autowired
    AttachmentService attachmentService;

    @GetMapping
    public HttpEntity<?> getAllAttachment(){
        ApiResponse allAttachment = attachmentService.getAllAttachment();
        return ResponseEntity.status(allAttachment.isSuccess() ? 200 : 204).body(allAttachment);
    }

    @GetMapping("/{id}")
    public HttpEntity<?> getAttachmentById(@PathVariable Integer id){
        ApiResponse attachmentById = attachmentService.getAttachmentById(id);
        return ResponseEntity.status(attachmentById.isSuccess()? 200 : 409).body(attachmentById);
    }


    @GetMapping("/download/{attachmentId}")
    public HttpEntity<?> getAttachmentFile(@PathVariable Integer attachmentId) throws IOException {
        return attachmentService.fileDownload(attachmentId);
    }

    @PostMapping
    public HttpEntity<?> fileUpload(@RequestParam MultipartFile file) {
        ApiResponse apiResponse = attachmentService.fileUpload(file);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 409).body(apiResponse);
    }

    @PutMapping("/{id}")
    public HttpEntity<?> editAttachment(@PathVariable Integer id, @RequestParam MultipartFile file) {
        ApiResponse apiResponse = attachmentService.editAttachment(id, file);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 409).body(apiResponse);
    }

    @DeleteMapping("/{id}")
    public HttpEntity<?> deleteAttachment(@PathVariable Integer id) {
        ApiResponse apiResponse = attachmentService.deleteAttachment(id);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 404).body(apiResponse);
    }

}





















