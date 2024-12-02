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
    private String license_plate; //skal kobles på et bil objekt udfra nummerplade
    private String delivery_location;
    private int price;

    private int max_km;
}
