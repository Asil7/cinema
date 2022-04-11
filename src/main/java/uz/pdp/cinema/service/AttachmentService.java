package uz.pdp.cinema.service;

//Asilbek Fayzullayev 15.03.2022 10:33

import com.sun.org.apache.bcel.internal.generic.INEG;
import com.sun.xml.internal.ws.message.ByteArrayAttachment;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import uz.pdp.cinema.model.Attachment;
import uz.pdp.cinema.model.AttachmentContent;
import uz.pdp.cinema.payload.ApiResponse;
import uz.pdp.cinema.repository.AttachmentContentRepository;
import uz.pdp.cinema.repository.AttachmentRepository;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class AttachmentService {

    @Autowired
    AttachmentRepository attachmentRepository;
    @Autowired
    AttachmentContentRepository attachmentContentRepository;


    public ApiResponse fileUpload(MultipartFile file) {
        try {
            Attachment saveAttachment = attachmentRepository.save(new Attachment(file.getOriginalFilename(), file.getContentType(), file.getSize()));
            attachmentContentRepository.save(new AttachmentContent(file.getBytes(), saveAttachment));
            return new ApiResponse("Successfully uploaded", true);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new ApiResponse("Error", false);
    }

    public ResponseEntity<ByteArrayResource> fileDownload(Integer attachmentId) throws IOException {
        AttachmentContent attachmentContent = attachmentContentRepository.findByAttachmentId(attachmentId);
        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(attachmentContent.getAttachment().getContentType()))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=\"" + attachmentContent.getAttachment().getName() + "\"")
                .body(new ByteArrayResource(attachmentContent.getData()));
    }


    public ApiResponse getAllAttachment() {
        List<Attachment> attachmentList = attachmentRepository.findAll();
        if (attachmentList.size() != 0) {
            return new ApiResponse("Success", true);
        }
        return new ApiResponse("List Empty", false);
    }

    public ApiResponse getAttachmentById(Integer id) {
        Optional<Attachment> byId = attachmentRepository.findById(id);
        if (!byId.isPresent()) {
            return new ApiResponse("Attachment not found", false);
        }
        return new ApiResponse("Success", true, byId);

    }


    public ApiResponse editAttachment(Integer id, MultipartFile file) {
        Optional<Attachment> optionalAttachment = attachmentRepository.findById(id);
        if (!optionalAttachment.isPresent()) {
            return new ApiResponse("Attachment not found!", false);
        }
        try {
            Attachment editingAttachment = optionalAttachment.get();
            editingAttachment.setContentType(file.getContentType());
            editingAttachment.setName(file.getOriginalFilename());
            editingAttachment.setSize(file.getSize());
            Attachment attachment = attachmentRepository.save(editingAttachment);

            AttachmentContent editingAttachmentContent = attachmentContentRepository.getById(editingAttachment.getId());
            editingAttachmentContent.setAttachment(attachment);
            editingAttachmentContent.setData(file.getBytes());
            return new ApiResponse("Successfully edited!!", true);
        } catch (Exception e) {
            return new ApiResponse("Error!!!", false);
        }
    }

    public ApiResponse deleteAttachment(Integer id) {
        Optional<Attachment> optionalAttachment = attachmentRepository.findById(id);
        if (!optionalAttachment.isPresent()) {
            return new ApiResponse("Attachment not found", false);
        }
        AttachmentContent attachmentContent = attachmentContentRepository.getById(id);
        attachmentContentRepository.deleteById(attachmentContent.getId());
        attachmentRepository.deleteById(id);
        return new ApiResponse("Successfully deleted", true);
    }

    public Attachment saveAttachment(MultipartFile file) {
        try {
            Attachment attachment = new Attachment();
            attachment.setName(file.getName());
            attachment.setContentType(file.getContentType());
            attachment.setSize(file.getSize());
            attachmentRepository.save(attachment);
            attachmentContentRepository.save(new AttachmentContent(file.getBytes(), attachment));
            return attachment;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

}
