package com.example.optimatefleet.model;

import java.time.LocalDate;

public class PreSaleContract {
    private String company_name;
    private int cvr;
    private String company_phonenumber;
    private String email;
    private int zip_code;
    private String street_name;
    private int street_number;
    private String city_name;
    private String license_plate;
    private String delivery_location;
    private double price;
    private int max_km;

    public PreSaleContract() {
    }

    public String getCompany_name() {
        return company_name;
    }

    public void setCompany_name(String company_name) {
        this.company_name = company_name;
    }

    public int getCvr() {
        return cvr;
    }

    public void setCvr(int cvr) {
        this.cvr = cvr;
    }

    public String getCompany_phonenumber() {
        return company_phonenumber;
    }

    public void setCompany_phonenumber(String company_phonenumber) {
        this.company_phonenumber = company_phonenumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getZip_code() {
        return zip_code;
    }

    public void setZip_code(int zip_code) {
        this.zip_code = zip_code;
    }

    public String getStreet_name() {
        return street_name;
    }

    public void setStreet_name(String street_name) {
        this.street_name = street_name;
    }

    public int getStreet_number() {
        return street_number;
    }

    public void setStreet_number(int street_number) {
        this.street_number = street_number;
    }

    public String getCity_name() {
        return city_name;
    }

    public void setCity_name(String city_name) {
        this.city_name = city_name;
    }

    public String getLicense_plate() {
        return license_plate;
    }

    public void setLicense_plate(String license_plate) {
        this.license_plate = license_plate;
    }

    public String getDelivery_location() {
        return delivery_location;
    }

    public void setDelivery_location(String delivery_location) {
        this.delivery_location = delivery_location;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getMax_km() {
        return max_km;
    }

    public void setMax_km(int max_km) {
        this.max_km = max_km;
    }

    @Override
    public String toString() {
        return "PreSaleContract{" +
                "company_name='" + company_name + '\'' +
                ", cvr=" + cvr +
                ", company_phonenumber='" + company_phonenumber + '\'' +
                ", email='" + email + '\'' +
                ", zip_code=" + zip_code +
                ", street_name='" + street_name + '\'' +
                ", street_number=" + street_number +
                ", city_name='" + city_name + '\'' +
                ", license_plate='" + license_plate + '\'' +
                ", delivery_location='" + delivery_location + '\'' +
                ", price=" + price +
                ", max_km=" + max_km +
                '}';
    }
}
