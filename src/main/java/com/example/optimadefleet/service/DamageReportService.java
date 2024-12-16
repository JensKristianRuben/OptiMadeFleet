package com.example.optimadefleet.service;

import com.example.optimadefleet.model.Car;
import com.example.optimadefleet.model.DamageReport;
import com.example.optimadefleet.repository.DamageReportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
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
        damageReport.setDescription(description.toString());
        damageReport.setDamage_price(totalPrice);
        damageReport.setMileage_over_limit(mileage_over_limit);

        car.setDamageReport(damageReport);

        //Skal genere en skadesrapport?

        damageReportRepository.createDamageReport(damageReport);
    }

    public double calculateMileage_over_limitPrice (int mileage_over_limit) {
        return mileage_over_limit * 0.75;
    }

    public DamageReport findDamageReportByLicense_plate(String license_plate)    {
        List<DamageReport> damageReportList = damageReportRepository.getListOfDamageReports();
        DamageReport damageReportToReturn = null;

        for(DamageReport damageReport : damageReportList) {
            if(damageReport.getLicense_plate().equals(license_plate)) {
                damageReportToReturn = damageReport;
            }
        }

        return damageReportToReturn;
    }

    public Map<String, String> getMapOfDescriptionAndPrice(String license_plate)  {
        Map<String, String> mapOfDescriptionAndPrice = new HashMap<>();
        DamageReport damageReport = findDamageReportByLicense_plate(license_plate);
        String[] description = damageReport.getDescription().split(" ");

        for(int i = 0; i < description.length; i+=2) {
            mapOfDescriptionAndPrice.put(description[i], description[i+1]);
        }

        return mapOfDescriptionAndPrice;
    }

    public void deleteDamageReport(String license_plate)    {
        damageReportRepository.deleteDamageReport(license_plate);
    }

    public int calculateSumOfDamages (String license_plate) {
        Map<String, String> damageList = getMapOfDescriptionAndPrice(license_plate);
        int sum = 0;

        for(String description : damageList.values()) {
            if(description != null) {
                sum += Integer.parseInt(description);
            }
        }

        return sum;
    }
}
