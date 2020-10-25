package com.uz.servive;


import com.uz.Entity.Korres;
import com.uz.Entity.Tsj;
import com.uz.Repository.TsjRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TsjService {

    @Autowired
    private final TsjRepository tsjRepository;

    public TsjService(TsjRepository tsjRepository) {
        this.tsjRepository = tsjRepository;
    }


    public List<Tsj> tsjList(){
        String name = "kredit";
        List<Tsj> tsjs = tsjRepository.findByTsjNameIsNotLike(name);
        return tsjs;
    }

    public Tsj addTsj(Tsj tsj){

        return tsjRepository.save(tsj);
    }


}
