package uz.pdp.cinema.repository;

//Asilbek Fayzullayev 14.03.2022 22:37


import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.cinema.model.Attachment;

public interface AttachmentRepository extends JpaRepository <Attachment,Integer> {

}
