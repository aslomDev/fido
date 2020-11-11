package com.uz.servive;


import com.uz.Entity.Cb;
import com.uz.Repository.CbRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CbService {

    @Autowired
    private final CbRepository cbRepository;

    public CbService(CbRepository cbRepository) {
        this.cbRepository = cbRepository;
    }

    public List<Cb> cbListList(){
        List<Cb> cbList = cbRepository.findAll();
        return cbList;
    }

    public Cb addCb(Cb cb){

        return cbRepository.save(cb);
    }


}
