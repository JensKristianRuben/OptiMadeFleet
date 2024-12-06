package com.example.optimatefleet.model;

import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.Date;

public class RentContract {
    private String renter_first_name;
    private String renter_last_name;
    private String renter_phonenumber;
    private String email;
    private LocalDate date_of_birth;
    private int zip_code;
    private String street_name;
    private int street_number;
    private String city_name;
    private int drivers_license_number;
    private String license_plate;
    private int max_km;
    private LocalDate rental_start_date;
    private LocalDate rental_end_date;
    private String pickup_location;
    private String return_location;
    private String rent_contract_type;

    public RentContract() {}

    public String getRenter_first_name() {
        return renter_first_name;
    }

    public void setRenter_first_name(String renter_first_name) {
        this.renter_first_name = renter_first_name;
    }

    public String getRenter_last_name() {
        return renter_last_name;
    }

    public void setRenter_last_name(String renter_last_name) {
        this.renter_last_name = renter_last_name;
    }

    public String getRenter_phonenumber() {
        return renter_phonenumber;
    }

    public void setRenter_phonenumber(String renter_phonenumber) {
        this.renter_phonenumber = renter_phonenumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDate getDate_of_birth() {
        return date_of_birth;
    }

    public void setDate_of_birth(LocalDate date_of_birth) {
        this.date_of_birth = date_of_birth;
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

    public int getDrivers_license_number() {
        return drivers_license_number;
    }

    public void setDrivers_license_number(int drivers_license_number) {
        this.drivers_license_number = drivers_license_number;
    }

    public String getLicense_plate() {
        return license_plate;
    }

    public void setLicense_plate(String license_plate) {
        this.license_plate = license_plate;
    }

    public int getMax_km() {
        return max_km;
    }

    public void setMax_km(int max_km) {
        this.max_km = max_km;
    }

    public LocalDate getRental_start_date() {
        return rental_start_date;
    }

    public void setRental_start_date(LocalDate rental_start_date) {
        this.rental_start_date = rental_start_date;
    }

    public LocalDate getRental_end_date() {
        return rental_end_date;
    }

    public void setRental_end_date(LocalDate rental_end_date) {
        this.rental_end_date = rental_end_date;
    }

    public String getPickup_location() {
        return pickup_location;
    }

    public void setPickup_location(String pickup_location) {
        this.pickup_location = pickup_location;
    }

    public String getReturn_location() {
        return return_location;
    }

    public void setReturn_location(String return_location) {
        this.return_location = return_location;
    }

    public String getRent_contract_type() {
        return rent_contract_type;
    }

    public void setRent_contract_type(String rent_contract_type) {
        this.rent_contract_type = rent_contract_type;
    }

    @Override
    public String toString() {
        return "RentalInfo{" +
                "renter_first_name='" + renter_first_name + '\'' +
                ", renter_last_name='" + renter_last_name + '\'' +
                ", renter_phonenumber='" + renter_phonenumber + '\'' +
                ", email='" + email + '\'' +
                ", date_of_birth=" + date_of_birth +
                ", zip_code=" + zip_code +
                ", street_name='" + street_name + '\'' +
                ", street_number=" + street_number +
                ", city_name='" + city_name + '\'' +
                ", drivers_license_number=" + drivers_license_number +
                ", license_plate='" + license_plate + '\'' +
                ", max_km=" + max_km +
                ", rental_start_date=" + rental_start_date +
                ", rental_end_date=" + rental_end_date +
                ", pickup_location='" + pickup_location + '\'' +
                ", return_location='" + return_location + '\'' +
                '}';
    }

}
