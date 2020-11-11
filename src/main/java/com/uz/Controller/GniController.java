package com.uz.Controller;

import com.uz.Entity.Gni;
import com.uz.Entity.Tsj;
import com.uz.servive.GniService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/gni")
public class GniController {

    @Autowired
    private final GniService gniService;


    public GniController(GniService gniService) {
        this.gniService = gniService;
    }

    @GetMapping
    public String gni(Model model){
        List<Gni> gniList = gniService.gniList();
        model.addAttribute("gni", gniList);
        return "gni";
    }

    @GetMapping("/add")
    public String addTsj(Model model){

        return "addGni";

    }

    @PostMapping("/save")
    public String saveTsj(Gni gni, Model model){



        model.addAttribute("gni", gniService.addGni(gni));

        return "redirect:/gni";

    }

}
