package com.example.optimadefleet.controller;

import com.example.optimadefleet.model.Car;
import com.example.optimadefleet.model.CarModel;
import com.example.optimadefleet.model.Utility;
import com.example.optimadefleet.service.CarService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class CarController {
    @Autowired
    private CarService carService;
    @Autowired
    private Utility utilityService;

    @GetMapping("CreateCarAndCarModel")
    public String createCar(Model model, HttpSession session) {
        if (session.getAttribute("user") == null) {
            return "redirect:/";
        }
        model.addAttribute("car_models", carService.fethAllCarModels());
        return "CreateCarAndCarModel";
    }

    @PostMapping("CreateCarAndCarModel")
    public String createCar(@ModelAttribute Car car, @RequestParam("car_model_name") String car_model_name) {
        car.setRegistration_tax(car.calculateRegistrationTax());

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
        Car car = carService.findCarByLicensePlate(license_plate);
        double fullPriceOfCar = car.calculateRegistrationTax() + car.getOriginal_price();
        model.addAttribute("util", utilityService);
        model.addAttribute("car", car);
        model.addAttribute("car_models", carService.fethAllCarModels());
        model.addAttribute("fullPriceOfCar", utilityService.roundNumber(fullPriceOfCar));
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

    //test//
}
