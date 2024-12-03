package com.example.optimatefleet.model;

import com.example.optimatefleet.service.CarService;

import java.util.List;

public class DamageReport {
    private String license_plate;
    private List<Damage> listOfDamages;
    private int mileageOverLimit;

    public DamageReport(){}

    public String getLicense_plate() {
        return license_plate;
    }

    public void setLicense_plate(String license_plate) {
        this.license_plate = license_plate;
    }

    public List<Damage> getListOfDamages() {
        return listOfDamages;
    }

    public void setListOfDamages(List<Damage> listOfDamages) {
        this.listOfDamages = listOfDamages;
    }

    public int getMileageOverLimit() {
        return mileageOverLimit;
    }

    public void setMileageOverLimit(int mileageOverLimit) {
        this.mileageOverLimit = mileageOverLimit;
    }

    @Override
    public String toString() {
        return "DamageReport{" +
                "license_plate='" + license_plate + '\'' +
                ", listOfDamages=" + listOfDamages +
                ", mileageOverLimit=" + mileageOverLimit +
                '}';
    }
}
