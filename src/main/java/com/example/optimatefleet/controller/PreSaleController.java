package com.example.optimatefleet.controller;

import com.example.optimatefleet.model.Car;
import com.example.optimatefleet.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class PreSaleController {
    @Autowired
    CarService carService;

    @GetMapping("/CreatePreSaleContract")
    public String createPreSaleContract(Model model) {
        List<Car> listOfCarsNotSold = carService.fetchAllCarsWithNotSoldStatus();
        model.addAttribute("cars", listOfCarsNotSold);
        return "CreatePreSaleContract";
    }
}
