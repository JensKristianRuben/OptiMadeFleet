package com.example.optimatefleet.controller;

import com.example.optimatefleet.model.Car;
import com.example.optimatefleet.service.CarService;
import com.example.optimatefleet.service.DamageReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

@Controller
public class DamageReportController {

    @Autowired
    CarService carService;

    @Autowired
    DamageReportService damageReportService;

    @PostMapping("saveDamageReport") //Funktionaliteten skal måske ligges i service laget
    public String saveDamageReport(@RequestParam Map<String, String> param, @RequestParam int mileage_over_limit, @RequestParam String license_plate) {
        Car car = carService.findCarByLicensePlate(license_plate);
        carService.updateCarStatusToReady_for_invoice(license_plate);

        damageReportService.createDamageReport(param, mileage_over_limit, license_plate, car);

        return "redirect:/DamageReportPage";
    }

    @GetMapping("DamageReportPagePost/{license_plate}/{sortBy}")
    public String damageReportPagePost(Model model, @PathVariable String license_plate, @PathVariable String sortBy) {
        List<Car> carsList = carService.fetchAllCarsAndSortByParam(sortBy);
        model.addAttribute("carToGetDamageReport", carService.findCarByLicensePlate(license_plate));
        model.addAttribute(sortBy);
        model.addAttribute("carsList", carsList);
        if (sortBy.equals("ready_for_invoice")) {
            model.addAttribute("damageReport", damageReportService.findDamageReportByLicense_plate(license_plate));
            model.addAttribute("listOfDamages", damageReportService.mapOfDescriptionAndPrice(license_plate));
        }
        return "DamageReportPage";
    }

    @PostMapping("deleteDamageReport")
    public String deleteDamageReport(@RequestParam String license_plate) {
        return "redirect:/DamageReportPagePost/" + license_plate + "/returned";
    }
}
