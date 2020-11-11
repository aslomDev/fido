package com.uz.Controller;

import com.uz.Entity.Cb;
import com.uz.Entity.Gni;
import com.uz.servive.CbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/cb")
public class CbController {

    @Autowired
    private final CbService cbServices;

    public CbController(CbService cbServices) {
        this.cbServices = cbServices;
    }



    @GetMapping
    public String cb(Model model){
        List<Cb> cbList = cbServices.cbListList();
        model.addAttribute("cb", cbList);
        return "cb";
    }

    @GetMapping("/add")
    public String addcb(Model model){

        return "addCb";

    }

    @PostMapping("/save")
    public String saveCb(Cb cb, Model model){



        model.addAttribute("cb", cbServices.addCb(cb));

        return "redirect:/cb";

    }



}
