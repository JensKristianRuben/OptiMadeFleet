package com.example.optimadefleet.controller;

import com.example.optimadefleet.model.Car;
import com.example.optimadefleet.model.Utility;
import com.example.optimadefleet.service.CarService;
import com.example.optimadefleet.service.DamageReportService;
import jakarta.servlet.http.HttpSession;
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

    @Autowired
    Utility utilityService;

    @PostMapping("saveDamageReport")
    public String saveDamageReport(@RequestParam Map<String, String> param, @RequestParam int mileage_over_limit, @RequestParam String license_plate) {
        Car car = carService.findCarByLicensePlate(license_plate);
        carService.updateCarStatusToReady_for_invoice(license_plate);

        damageReportService.createDamageReport(param, mileage_over_limit, license_plate, car);

        return "redirect:/DamageReportPage";
    }

    @GetMapping("DamageReportPagePost/{license_plate}/{sortBy}")
    public String damageReportPagePost(Model model, @PathVariable String license_plate, @PathVariable String sortBy, HttpSession session) {
        if (session.getAttribute("user") == null) {
            return "redirect:/";
        }
        List<Car> carsList = carService.fetchAllCarsAndSortByParam(sortBy);
        model.addAttribute("carToGetDamageReport", carService.findCarByLicensePlate(license_plate));
        model.addAttribute(sortBy);
        model.addAttribute("carsList", carsList);
        model.addAttribute("utilityService", utilityService);
        if (sortBy.equals("ready_for_invoice")) {
            double sumPlusMileage_Over_limit = damageReportService.calculateSumOfDamages(license_plate) + damageReportService.calculateMileage_over_limitPrice(damageReportService.findDamageReportByLicense_plate(license_plate).getMileage_over_limit());
            model.addAttribute("damageReport", damageReportService.findDamageReportByLicense_plate(license_plate));
            model.addAttribute("listOfDamages", damageReportService.getMapOfDescriptionAndPrice(license_plate));
            model.addAttribute("sumOfDamages",utilityService.roundNumber(damageReportService.calculateSumOfDamages(license_plate)));
            model.addAttribute("mileage_over_limit_price", utilityService.roundNumber(damageReportService.calculateMileage_over_limitPrice(damageReportService.findDamageReportByLicense_plate(license_plate).getMileage_over_limit())));
            model.addAttribute("sumPlusMileage_Over_limit", utilityService.roundNumber(sumPlusMileage_Over_limit));
        }
        return "DamageReportPage";
    }

    @PostMapping("deleteDamageReport")
    public String deleteDamageReport(@RequestParam String license_plate) {
        damageReportService.deleteDamageReport(license_plate);
        carService.updateCarStatusReturned(license_plate);
        String sortBy = "returned";
        return "redirect:/DamageReportPagePost/" + license_plate + "/" + sortBy;
    }
}
