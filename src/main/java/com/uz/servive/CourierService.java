package com.uz.servive;

import com.uz.Entity.Courier;
import com.uz.Entity.FileUpload;
import com.uz.Repository.CourierRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@Service
public class CourierService {

    @Autowired
    private final CourierRepository courierRepository;


    public CourierService(CourierRepository courierRepository) {
        this.courierRepository = courierRepository;
    }


    public Courier addCourier(Courier courier){





        return  courierRepository.save(courier);

    }

    public List<Courier>getCourierList(){

        List<Courier> couriers = courierRepository.findAll();

        return couriers;
    }

    public Courier editCourier(Integer id){

        Courier courier = courierRepository.findById(id).get();

        courierRepository.save(courier);

        return courier;

    }

    public Courier delete(Integer id){
        Courier courier = courierRepository.findById(id).get();
        courierRepository.delete(courier);
        return courier;
    }

    public Courier getOne(Integer id){
        Courier courier = courierRepository.findById(id).get();
        return courier;
    }
}
