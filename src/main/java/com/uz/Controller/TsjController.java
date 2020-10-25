package com.uz.Controller;

import com.uz.Entity.Korres;
import com.uz.Entity.Tsj;
import com.uz.servive.TsjService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/tsj")
public class TsjController {

    @Autowired
    private final TsjService tsjService;

    public TsjController(TsjService tsjService) {
        this.tsjService = tsjService;
    }

    @GetMapping
    public String tsj(Model model){
        List<Tsj> tsjs = tsjService.tsjList();
        model.addAttribute("tsj", tsjs);
        return "tsj";
    }

    @GetMapping("/add")
    public String addTsj(Model model){

        return "addTsj";

    }

    @PostMapping("/save")
    public String saveTsj(Tsj tsj, Model model){



        model.addAttribute("tsj", tsjService.addTsj(tsj));

        return "redirect:/tsj";

    }

}
