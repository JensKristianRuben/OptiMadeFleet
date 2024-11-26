package com.example.optimatefleet.model;

public class Car {
    private String license_plate;
    CarModel carModel;
    private String vin_number;
    private double original_price;
    private double registration_tax_pr_month;
    private CarStatus car_status;
    private int odometer;
    private DamageReport damageReport;
    private boolean is_pre_sold;
    private int sale_price;
    private boolean alert_damages_not_fixed;
    private int year_of_manufactoring;
    private String color;

    public Car(String license_plate, CarModel carModel, String vin_number, double original_price, double registration_tax_pr_month, CarStatus car_status, int odometer, boolean is_pre_sold, int sale_price, int year_of_manufactoring, String color) {
        this.license_plate = license_plate;
        this.carModel = carModel;
        this.vin_number = vin_number;
        this.original_price = original_price;
        this.registration_tax_pr_month = registration_tax_pr_month;
        this.car_status = car_status;
        this.odometer = odometer;
        this.damageReport = new DamageReport();
        this.is_pre_sold = is_pre_sold;
        this.sale_price = sale_price;
        this.alert_damages_not_fixed = false;
        this.year_of_manufactoring = year_of_manufactoring;
        this.color = color;
    }

    public void setCar_status(CarStatus car_Car_status){
        this.car_status = car_Car_status;
    }

    public CarStatus getCar_status(){
        return car_status;
    }

    public String getLicense_plate() {
        return license_plate;
    }

    public void setLicense_plate(String license_plate) {
        this.license_plate = license_plate;
    }

    public CarModel getCarModel() {
        return carModel;
    }

    public void setCarModel(CarModel carModel) {
        this.carModel = carModel;
    }

    public String getVin_number() {
        return vin_number;
    }

    public void setVin_number(String vin_number) {
        this.vin_number = vin_number;
    }

    public double getOriginal_price() {
        return original_price;
    }

    public void setOriginal_price(double original_price) {
        this.original_price = original_price;
    }

    public double getRegistration_tax_pr_month() {
        return registration_tax_pr_month;
    }

    public void setRegistration_tax_pr_month(double registration_tax_pr_month) {
        this.registration_tax_pr_month = registration_tax_pr_month;
    }

    public int getOdometer() {
        return odometer;
    }

    public void setOdometer(int odometer) {
        this.odometer = odometer;
    }

    public DamageReport getDamageReport() {
        return damageReport;
    }

    public void setDamageReport(DamageReport damageReport) {
        this.damageReport = damageReport;
    }

    public boolean isIs_pre_sold() {
        return is_pre_sold;
    }

    public void setIs_pre_sold(boolean is_pre_sold) {
        this.is_pre_sold = is_pre_sold;
    }

    public int getSale_price() {
        return sale_price;
    }

    public void setSale_price(int sale_price) {
        this.sale_price = sale_price;
    }

    public boolean isAlert_damages_not_fixed() {
        return alert_damages_not_fixed;
    }

    public void setAlert_damages_not_fixed(boolean alert_damages_not_fixed) {
        this.alert_damages_not_fixed = alert_damages_not_fixed;
    }

    public int getYear_of_manufactoring() {
        return year_of_manufactoring;
    }

    public void setYear_of_manufactoring(int year_of_manufactoring) {
        this.year_of_manufactoring = year_of_manufactoring;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public enum CarStatus {
        available,
        rentet,
        returned,
        under_repair
    }
}


