package com.example.optimatefleet.model;

public class DamageReport {
    private String license_plate;
    private String damage_desciption;
    private int damage_price;
    private int mileage_over_limit;

    public DamageReport(){}

    public String getDamage_desciption() {
        return damage_desciption;
    }

    public void setDamage_desciption(String damage_desciption) {
        this.damage_desciption = damage_desciption;
    }

    public int getDamage_price() {
        return damage_price;
    }

    public void setDamage_price(int damage_price) {
        this.damage_price = damage_price;
    }

    public String getLicense_plate() {
        return license_plate;
    }

    public void setLicense_plate(String license_plate) {
        this.license_plate = license_plate;
    }


    public int getMileage_over_limit() {
        return mileage_over_limit;
    }

    public void setMileage_over_limit(int mileage_over_limit) {
        this.mileage_over_limit = mileage_over_limit;
    }

    @Override
    public String toString() {
        return "DamageReport{" +
                "license_plate='" + license_plate + '\'' +
                ", damage_desciption='" + damage_desciption + '\'' +
                ", damage_price=" + damage_price +
                ", mileage_over_limit=" + mileage_over_limit +
                '}';
    }
}
