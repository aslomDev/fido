package com.uz.Controller;

import com.uz.Entity.Korres;
import com.uz.servive.KorresService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/korres")
public class KorresController {

    @Autowired
    private final KorresService korresService;


    public KorresController(KorresService korresService) {
        this.korresService = korresService;
    }


    @GetMapping("/add")
    public String korres(Korres korres){

        return "addKorres";
    }

    @PostMapping("/save")
    public String saveKorres(Korres korres, Model model){



        model.addAttribute("korr", korresService.addKor(korres));

        return "redirect:/korres";

    }

    @GetMapping
    public String getKorres(Model model){
        List<Korres> korres = korresService.getKorres();
        model.addAttribute("korr", korres);
        return "korres";
    }

    @GetMapping("/edit/{id}")
    public String getOne(@PathVariable Integer id, Model model){
        Korres korres = korresService.editKorres(id);
        model.addAttribute("oneKorres", korres);
        return "editKorres";
    }

    @PostMapping("/update")
    public String updateKorres(Korres korres, Model model){



        Korres update = korresService.addKor(korres);

        model.addAttribute("korresId", update);

        return "redirect:/korres";

    }

    @GetMapping("/delete/{id}")
    public String deleteCourierse(@PathVariable Integer id){
        Korres korres = korresService.delete(id);
        return "redirect:/korres";

    }

}
