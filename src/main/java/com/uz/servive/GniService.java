package com.uz.servive;


import com.uz.Entity.Gni;
import com.uz.Entity.Tsj;
import com.uz.Repository.GniRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class GniService {

    @Autowired
    private final GniRepository gniRepository;

    public GniService(GniRepository gniRepository) {
        this.gniRepository = gniRepository;
    }

    public List<Gni> gniList(){
        List<Gni> gniList = gniRepository.findAll();
        return gniList;
    }

    public Gni addGni(Gni gni){

        return gniRepository.save(gni);
    }

}
