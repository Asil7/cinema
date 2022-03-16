package uz.pdp.cinema.service;

//Asilbek Fayzullayev 16.03.2022 16:30   

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.cinema.repository.RowRepository;


@Service
public class RowService {

    @Autowired
    RowRepository repository;



}
