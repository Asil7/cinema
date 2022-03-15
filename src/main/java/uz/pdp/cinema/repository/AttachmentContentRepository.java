package uz.pdp.cinema.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.cinema.model.AttachmentContent;

import java.util.Optional;

public interface AttachmentContentRepository extends JpaRepository<AttachmentContent, Integer> {

    AttachmentContent findByAttachmentId(Integer attachment_id);
}
