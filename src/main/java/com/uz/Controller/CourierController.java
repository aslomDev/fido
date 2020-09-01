package com.uz.Controller;


import com.uz.Entity.Courier;
import com.uz.servive.CourierService;
import com.uz.servive.MapValidatorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collection;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/courier")
public class CourierController {

    @Autowired
    private final CourierService courierService;

    public CourierController(CourierService courierService) {
        this.courierService = courierService;
    }

    @PostMapping("/save")
    public String saveCourier(@Valid Courier courier, BindingResult result, Model model){

        if (result.hasErrors()){
            Map<String, String> errorsMap = MapValidatorService.getErrors(result);
            model.mergeAttributes(errorsMap);
            model.addAttribute("errors", courier);
            System.out.println(errorsMap);
            return "addCourier";
        }




        model.addAttribute("courier", courierService.addCourier(courier));

        return "redirect:/courier";

    }

    @GetMapping("/add")
    public String addCourier(Courier courier){
        return "addCourier";
    }

    @GetMapping
    public String getCourier(Model model){
        List<Courier> couriers = courierService.getCourierList();
        model.addAttribute("couriers", couriers);
        return "courier";
    }

    @GetMapping("/delete/{id}")
    public String deleteCourierse(@PathVariable Integer id){
        Courier courier = courierService.delete(id);
        return "redirect:/courier";

    }

    @GetMapping("/edit/{id}")
    public String getOne(@PathVariable Integer id, Model model){
        Courier courier = courierService.editCourier(id);
        model.addAttribute("oneCourier", courier);
        return "editCourier";
    }

    @PostMapping("/update")
    public String updateCourier(@Valid Courier courier, BindingResult result, Model model){

        if (result.hasErrors()){
            Map<String, String> errorsMap = MapValidatorService.getErrors(result);
            model.mergeAttributes(errorsMap);
            model.addAttribute("errors", courier);
            System.out.println(errorsMap);
            return "editCourier";
        }


        Courier update = courierService.addCourier(courier);

        model.addAttribute("courierId", update);

        return "redirect:/courier";

    }


}
