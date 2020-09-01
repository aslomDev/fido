package com.uz.servive;

import com.uz.Entity.Courier;
import com.uz.Entity.Korres;
import com.uz.Repository.KorresRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class KorresService {

    @Autowired
    private final KorresRepository korresRepository;

    public KorresService(KorresRepository korresRepository) {
        this.korresRepository = korresRepository;
    }


    public Korres addKor(Korres korres){

        return korresRepository.save(korres);
    }

    public List<Korres> getKorres() {
    List<Korres> korres1 = korresRepository.findAll();
    return korres1;
    }

    public Korres editKorres(Integer id){

        Korres korres = korresRepository.findById(id).get();

        korresRepository.save(korres);

        return korres;

    }

    public Korres delete(Integer id){
        Korres korres = korresRepository.findById(id).get();
        korresRepository.delete(korres);
        return korres;
    }

}
