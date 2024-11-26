package com.example.optimatefleet.controller;

import com.example.optimatefleet.model.CarModel;
import com.example.optimatefleet.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class CarController {
    @Autowired
    private CarService carService;

    @GetMapping("CreateCar")
    public String createCar ()  {
        return "CreateCar";
    }

    @PostMapping("CreateCar")
    public String createCar (Model model)  {
        //Skal måske redirecte til dataregister siden?
        return "redirect:/";
    }

    @PostMapping("SaveNewCarModel")
    public String saveNewCarModel (@ModelAttribute CarModel carModel)    {
        System.out.println(carModel);
        carService.saveNewCarModel(carModel);

        return "redirect:/CreateCar";
    }
}
