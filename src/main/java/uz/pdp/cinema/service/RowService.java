package uz.pdp.cinema.service;

//Asilbek Fayzullayev 16.03.2022 16:30   

import com.sun.xml.internal.ws.api.pipe.helper.AbstractPipeImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.cinema.model.Hall;
import uz.pdp.cinema.model.Row;
import uz.pdp.cinema.payload.ApiResponse;
import uz.pdp.cinema.repository.HallRepository;
import uz.pdp.cinema.repository.RowRepository;

import java.util.List;
import java.util.Optional;


@Service
public class RowService {

    @Autowired
    RowRepository rowrepository;
    @Autowired
    HallRepository hallRepository;
}
