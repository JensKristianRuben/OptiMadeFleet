package com.example.optimatefleet.controller;

import com.example.optimatefleet.model.Car;
import com.example.optimatefleet.model.CarModel;
import com.example.optimatefleet.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class CarController {
    @Autowired
    private CarService carService;

    @GetMapping("CreateCarAndCarModel")
    public String createCar(Model model) {
        model.addAttribute("car_models", carService.fethAllCarModels());
        return "CreateCarAndCarModel";
    }

    @PostMapping("CreateCarAndCarModel")
    public String createCar(@ModelAttribute Car car, @RequestParam("car_model_name") String car_model_name) {
        car.setRegistration_tax(car.calculateMonthlyRegistrationTax());

        if(carService.findCarByLicensePlate(car.getLicense_plate()) != null){
            return "redirect:/CreateCarAndCarModel";
        }else {
            carService.createNewCar(car, car_model_name);
            return "redirect:/DataRegister";
        }
    }

    @PostMapping("CreateNewCarModel")
    public String saveNewCarModel(@ModelAttribute CarModel carModel) {
        carService.createNewCarModel(carModel);

        return "redirect:/CreateCarAndCarModel";
    }

    @GetMapping("UpdateCarAndCarModel/{license_plate}") 
    public String updateCarAndCarModel(Model model, @PathVariable String license_plate) {
        //Husk at map dataregister html siden til denne controller
        model.addAttribute("car", carService.findCarByLicensePlate(license_plate));
        model.addAttribute("car_models", carService.fethAllCarModels());
        return "EditCar";
    }

    @PostMapping("UpdateCar")
    public String updateCar(@ModelAttribute Car car) {
        carService.updateCar(car);
        return "redirect:/DataRegister";
    }

    @PostMapping("DeleteCarFromDB/{license_plate}")
    public String deleteCarFromDB(@PathVariable String license_plate) {
        carService.DeleteCarFromDB(license_plate);
        return "redirect:/DataRegister";
    }

}
