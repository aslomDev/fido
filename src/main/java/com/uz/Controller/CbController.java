package com.uz.Controller;

import com.uz.servive.CbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/cb")
public class CbController {

    @Autowired
    private final CbService cbServices;

    public CbController(CbService cbServices) {
        this.cbServices = cbServices;
    }
}
