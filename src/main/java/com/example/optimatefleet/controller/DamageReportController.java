package com.example.optimatefleet.controller;

import com.example.optimatefleet.model.Car;
import com.example.optimatefleet.model.Damage;
import com.example.optimatefleet.model.DamageReport;
import com.example.optimatefleet.service.CarService;
import com.example.optimatefleet.service.DamageReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
public class DamageReportController {

    @Autowired
    CarService carService;

    @Autowired
    DamageReportService damageReportService;

    @PostMapping("saveDamageReport") //Funktionaliteten skal måske ligges i service laget
    public String saveDamageReport(@RequestParam Map<String, String> param, @RequestParam int mileageOverLimit, @RequestParam String license_plate) {
        List<Damage> listOfDamages = new ArrayList<>();

        //Går igennem map "param" men kun det antal gange den finder en der
        //hedder damage+i og laver obj af Damage med værdierne og ligger dem i liste listOfDamages
        for (int i = 1; param.containsKey("damage" + i); i++) {
            String damage = param.get("damage" + i);
            String price = param.get("price" + i);

            if (damage != null && price != null) {
                    Damage damageObject = new Damage(damage, Integer.parseInt(price));
                    damageObject.setLicense_plate(license_plate);
                    listOfDamages.add(damageObject);
                }
            }

        System.out.println(listOfDamages); //tjek om damages er oprettet

        DamageReport damageReport = new DamageReport();
        damageReport.setLicense_plate(license_plate);
        damageReport.setListOfDamages(listOfDamages);
        damageReport.setMileageOverLimit(mileageOverLimit);

        System.out.println(damageReport); //tjek om damagereport er oprettet

        Car car = carService.findCarByLicensePlate(license_plate);

        car.setDamageReport(damageReport);

        System.out.println(car); //tjek om car er oprettet


        //gem damagereport metode der gemmer damages også
        //Skal genere en skadesrapport?
        //Skal ændre i prisen på forhåndsaftalen alt efter hvor meget skaderne koster (skader-prisen)

        return "redirect:/DamageReportPage";
    }
}
