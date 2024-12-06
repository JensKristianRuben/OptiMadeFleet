package com.example.optimatefleet.service;

import com.example.optimatefleet.model.Car;
import com.example.optimatefleet.model.DamageReport;
import com.example.optimatefleet.model.PreSaleContract;
import com.example.optimatefleet.repository.DamageReportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class DamageReportService {

    @Autowired
    private DamageReportRepository damageReportRepository;

    public void createDamageReport (Map<String, String> param, int mileage_over_limit, String license_plate, Car car) {
        //Går igennem map "param" men kun det antal gange den finder en der
        //hedder damage+i og laver obj af Damage med værdierne og ligger dem i liste listOfDamages
        StringBuilder description = new StringBuilder();
        int totalPrice = 0;
        for (int i = 1; param.containsKey("damage" + i); i++) {
            String temp = param.get("price" + i);
            if(Integer.parseInt(temp) != 0) {
                description.append(param.get("damage" + i) + " " + param.get("price" + i) + " ");
                String priceString = param.get("price" + i);
                totalPrice += Integer.parseInt(priceString);
            }
        }

        DamageReport damageReport = new DamageReport();
        damageReport.setLicense_plate(license_plate);
        damageReport.setDamage_desciption(description.toString());
        damageReport.setDamage_price(totalPrice);
        damageReport.setMileage_over_limit(mileage_over_limit);

        car.setDamageReport(damageReport);

        //Skal genere en skadesrapport?

        damageReportRepository.createDamageReport(damageReport);
    }

    public double calculateMileage_over_limitPrice (int mileage_over_limit) {
        return mileage_over_limit * 0.75;
    }

}
