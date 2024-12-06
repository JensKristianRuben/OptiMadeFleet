package com.example.optimatefleet.controller;

import com.example.optimatefleet.model.Car;
import com.example.optimatefleet.model.DamageReport;
import com.example.optimatefleet.model.PreSaleContract;
import com.example.optimatefleet.service.CarService;
import com.example.optimatefleet.service.DamageReportService;
import com.example.optimatefleet.service.PreSaleContractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@Controller
public class DamageReportController {

    @Autowired
    CarService carService;

    @Autowired
    DamageReportService damageReportService;

    @Autowired
    PreSaleContractService preSaleContractService;

    @PostMapping("saveDamageReport") //Funktionaliteten skal måske ligges i service laget
    public String saveDamageReport(@RequestParam Map<String, String> param, @RequestParam int mileage_over_limit, @RequestParam String license_plate) {
        Car car = carService.findCarByLicensePlate(license_plate);
        carService.updateCarStatusToReady_for_invoice(license_plate);

        damageReportService.createDamageReport(param, mileage_over_limit, license_plate, car);








        //        //Går igennem map "param" men kun det antal gange den finder en der
//        //hedder damage+i og laver obj af Damage med værdierne og ligger dem i liste listOfDamages
//        StringBuilder description = new StringBuilder();
//        int totalPrice = 0;
//        for (int i = 1; param.containsKey("damage" + i); i++) {
//            description.append(param.get("damage" + i) + " " + param.get("price" + i) + " ");
//            String priceString = param.get("price" + i);
//            totalPrice += Integer.parseInt(priceString);
//        }
//
//        DamageReport damageReport = new DamageReport();
//        damageReport.setLicense_plate(license_plate);
//        damageReport.setDamage_desciption(description.toString());
//        damageReport.setDamage_price(totalPrice);
//        damageReport.setMileage_over_limit(mileage_over_limit);
//
//        Car car = carService.findCarByLicensePlate(license_plate);
//
//        car.setDamageReport(damageReport);
//
//        damageReportService.createDamageReport(damageReport);
//
//        //Skal genere en skadesrapport?
//
//        //Henter preSaleContract fra DB og minuser prisen med skadernes pris samt overkørte km.
//        PreSaleContract preSaleContract = preSaleContractService.findPreSaleContractByLicensePlate(license_plate);
//        preSaleContract.setPrice(preSaleContract.getPrice() - damageReport.getDamage_price() - damageReportService.calculateMileage_over_limitPrice(mileage_over_limit));
//
//        preSaleContractService.updatePreSaleContract(preSaleContract);
//
//        carService.updateCarStatusToReady_for_invoice(car.getLicense_plate());

        return "redirect:/DamageReportPage";
    }
}
