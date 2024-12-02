package com.example.optimatefleet.controller;

import com.example.optimatefleet.model.Car;
import com.example.optimatefleet.model.RentContract;
import com.example.optimatefleet.service.CarService;
import com.example.optimatefleet.service.RentContractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
@Controller
public class MainPagesController {
    @Autowired
    CarService carService;
    @Autowired
    RentContractService rentContractService;

    @GetMapping("/DataRegister")
    public String dataRegisterPage(Model model, @RequestParam(defaultValue = "allCars") String sortBy) {
        List<RentContract> rentContractList = rentContractService.showAllRentContracts();
        List<Car> carsList = carService.fetchAllCarsAndSortByParam(sortBy);
        model.addAttribute("sortBy",sortBy);
        model.addAttribute("carsList", carsList);
        model.addAttribute("rentContracts", rentContractList);
        return "DataRegister";
    }
}
