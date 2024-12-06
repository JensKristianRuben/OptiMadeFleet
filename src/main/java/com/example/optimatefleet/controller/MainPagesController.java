package com.example.optimatefleet.controller;

import com.example.optimatefleet.model.Car;
import com.example.optimatefleet.model.PreSaleContract;
import com.example.optimatefleet.model.RentContract;
import com.example.optimatefleet.service.CarService;
import com.example.optimatefleet.service.PreSaleContractService;
import com.example.optimatefleet.service.RentContractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
@Controller
public class MainPagesController {
    @Autowired
    CarService carService;
    @Autowired
    RentContractService rentContractService;
    @Autowired
    PreSaleContractService preSaleContractService;

    @GetMapping("/DataRegister")
    public String dataRegisterPage(Model model, @RequestParam(defaultValue = "allCars") String sortBy) {
        List<RentContract> rentContractList = rentContractService.showAllRentContracts();
        List<Car> carsList = carService.fetchAllCarsAndSortByParam(sortBy);
        List<PreSaleContract> preSaleContracts = preSaleContractService.fetchAllPreSaleContracts();
        model.addAttribute("sortBy",sortBy);
        model.addAttribute("carsList", carsList);
        model.addAttribute("rentContracts", rentContractList);
        model.addAttribute("preSaleContracts", preSaleContracts);
        return "DataRegister";
    }

    @GetMapping("DamageReportPage")
    public String damageReportPage(Model model, @RequestParam(defaultValue = "returned") String sortBy) {
        List<Car> carsList = carService.fetchAllCarsAndSortByParam(sortBy);
        model.addAttribute("sortBy",sortBy);
        model.addAttribute("carsList", carsList);

        return "DamageReportPage";
    }

    @GetMapping("DamageReportPagePost/{license_plate}/{sortBy}")
    public String damageReportPagePost(Model model, @PathVariable String license_plate, @PathVariable String sortBy) {
        List<Car> carsList = carService.fetchAllCarsAndSortByParam(sortBy);
        model.addAttribute("carToGetDamageReport", carService.findCarByLicensePlate(license_plate));
        model.addAttribute(sortBy);
        model.addAttribute("carsList", carsList);

        return "DamageReportPage";
    }
}
