package com.example.optimatefleet.controller;

import com.example.optimatefleet.model.Car;
import com.example.optimatefleet.model.CarModel;
import com.example.optimatefleet.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class CarController {
    @Autowired
    private CarService carService;

    @GetMapping("CreateCarAndCarModel")
    public String createCar (Model model)  {
        model.addAttribute("car_models",carService.fethAllCarModels());
        return "CreateCarAndCarModel";
    }

    @PostMapping("CreateCarAndCarModel")
    public String createCar (@ModelAttribute Car car, @RequestParam("car_model_name") String car_model_name)  {
        carService.createNewCar(car, car_model_name);
        //Skal måske redirecte til dataregister siden?
        return "redirect:/";
    }

    @PostMapping("CreateNewCarModel")
    public String saveNewCarModel (@ModelAttribute CarModel carModel)    {
        System.out.println(carModel);
        carService.createNewCarModel(carModel);

        return "redirect:/CreateCarAndCarModel";
    }
}
