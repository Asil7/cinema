package uz.pdp.cinema.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import uz.pdp.cinema.model.Actor;
import uz.pdp.cinema.model.Attachment;
import uz.pdp.cinema.model.AttachmentContent;
import uz.pdp.cinema.payload.ApiResponse;
import uz.pdp.cinema.repository.ActorRepository;
import uz.pdp.cinema.repository.AttachmentContentRepository;
import uz.pdp.cinema.repository.AttachmentRepository;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

//Asilbek Fayzullayev 15.03.2022 10:27

@Service
@RequiredArgsConstructor
public class ActorService {
    @Autowired
    AttachmentRepository attachmentRepository;
    @Autowired
    AttachmentContentRepository attachmentContentRepository;
    @Autowired
    ActorRepository actorRepository;

    public ApiResponse getAllActors() {
        List<Actor> actorList = actorRepository.findAll();
        if (actorList.size() == 0) {
            return new ApiResponse("List empty", false);
        }
        return new ApiResponse("Success", true);
    }

    public ApiResponse getActorById(Integer id) {
        Optional<Actor> actorRepositoryById = actorRepository.findById(id);
        if (!actorRepositoryById.isPresent()) {
            return new ApiResponse("Actor not found", false);
        }
        return new ApiResponse("Success", true, actorRepositoryById.get());
    }

    public ApiResponse addActor(MultipartFile file, Actor actor) {
        Attachment attachment = attachmentRepository.save(new Attachment(file.getContentType(),  file.getOriginalFilename(),file.getSize()));
        try {
            attachmentContentRepository.save(new AttachmentContent(file.getBytes(), attachment));
            Actor actor1 = new Actor(actor.getName(), attachment);
            Actor save = actorRepository.save(actor1);
            return new ApiResponse("Successfully added", true, save);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new ApiResponse("Error", false);
    }

    public ApiResponse editActor(Integer id, MultipartFile file, Actor actor) {
        Optional<Actor> optionalActor = actorRepository.findById(id);
        if (!optionalActor.isPresent()) {
            return new ApiResponse("Actor not found", false);
        }
        Actor editActor = optionalActor.get();
        editActor.setName(actor.getName());
        if (file.isEmpty()) {
            Actor saveActor = actorRepository.save(editActor);
            return new ApiResponse("Successfully edited", true, saveActor);
        }
        Attachment attachment = editActor.getPhoto();
        attachment.setContentType(file.getContentType());
        attachment.setName(file.getName());
        attachment.setSize(file.getSize());
        Attachment saveAttachment = attachmentRepository.save(attachment);

        AttachmentContent attachmentContent = attachmentContentRepository.findByAttachmentId(saveAttachment.getId());
        try {
            attachmentContent.setData(file.getBytes());
            attachmentContent.setAttachment(saveAttachment);
            attachmentContentRepository.save(attachmentContent);
            actor.setPhoto(saveAttachment);
            Actor save = actorRepository.save(actor);
            return new ApiResponse("Successfully edited!", true, save);

        } catch (IOException e) {
            return new ApiResponse("Error!!", false);
        }
    }

    public ApiResponse deleteActor(Integer id) {
        Optional<Actor> optionalActor = actorRepository.findById(id);
        if (!optionalActor.isPresent()) {
            return new ApiResponse("Actor not found!!", false);
        }
        try {
            Actor actor = optionalActor.get();
            Attachment attachment = actor.getPhoto();
            AttachmentContent attachmentContent = attachmentContentRepository.findByAttachmentId(attachment.getId());
            attachmentContentRepository.deleteById(attachmentContent.getId());
            attachmentRepository.deleteById(attachment.getId());
            actorRepository.delete(actor);
            return new ApiResponse("Successfully deleted!", true);
        } catch (Exception e) {
            return new ApiResponse("Error!!", false);
        }
    }
}




















