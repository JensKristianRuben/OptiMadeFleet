package com.example.optimatefleet.model;

public class Damage {
    private String license_plate;
    private String damage;
    private int price;

    public Damage(){}
    public Damage(String damage, int price) {
        this.damage = damage;
        this.price = price;
    }

    public String getLicense_plate() {
        return license_plate;
    }

    public void setLicense_plate(String license_plate) {
        this.license_plate = license_plate;
    }

    public String getDamage() {
        return damage;
    }

    public void setDamage(String damage) {
        this.damage = damage;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Damage{" +
                "license_plate='" + license_plate + '\'' +
                ", damage='" + damage + '\'' +
                ", price=" + price +
                '}';
    }
}
