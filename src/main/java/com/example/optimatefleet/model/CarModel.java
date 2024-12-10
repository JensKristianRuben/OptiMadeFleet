package com.example.optimatefleet.model;

public class CarModel {
    private String car_model_name;
    private int average_rental_time_in_months;
    private double engine_size;
    private int seat_count;
    private int door_count;
    private int horsepower;
    public Make make;
    public Body_type body_type;
    public Gear_type gear_type;
    public Fuel_type fuel_type;

    @Override
    public String toString() {
        return "CarModel{" +
                "car_model_name='" + car_model_name + '\'' +
                ", average_rental_time_in_months=" + average_rental_time_in_months +
                ", engine_size=" + engine_size +
                ", seat_count=" + seat_count +
                ", door_count=" + door_count +
                ", horsepower=" + horsepower +
                ", make=" + make +
                ", body_type=" + body_type +
                ", gear_type=" + gear_type +
                ", fuel_type=" + fuel_type +
                '}';
    }

    public CarModel(String car_model_name, double engine_size, int seat_count, int door_count, int horsepower, Make make, Body_type body_type, Gear_type gear_type, Fuel_type fuel_type) {
        this.car_model_name = car_model_name;
        this.average_rental_time_in_months = 0;
        this.engine_size = engine_size;
        this.seat_count = seat_count;
        this.door_count = door_count;
        this.horsepower = horsepower;
        this.make = make;
        this.body_type = body_type;
        this.gear_type = gear_type;
        this.fuel_type = fuel_type;
    }
    public CarModel()   {}
    public String getCar_model_name() {
        return car_model_name;
    }

    public void setCar_model_name(String car_model_name) {
        this.car_model_name = car_model_name;
    }

    public int getAverage_rental_time_in_months() {
        return average_rental_time_in_months;
    }

    public void setAverage_rental_time_in_months(int average_rental_time_in_months) {
        this.average_rental_time_in_months = average_rental_time_in_months;
    }

    public double getEngine_size() {
        return engine_size;
    }

    public void setEngine_size(double engine_size) {
        this.engine_size = engine_size;
    }

    public int getSeat_count() {
        return seat_count;
    }

    public void setSeat_count(int seat_count) {
        this.seat_count = seat_count;
    }

    public int getDoor_count() {
        return door_count;
    }

    public void setDoor_count(int door_count) {
        this.door_count = door_count;
    }

    public int getHorsepower() {
        return horsepower;
    }

    public void setHorsepower(int horsepower) {
        this.horsepower = horsepower;
    }

    public String getMake() {
        return make.toString();
    }

    public void setMake(Make make) {
        this.make = make;
    }

    public String getBody_type() {
        return body_type.toString();
    }

    public void setBody_type(Body_type body_type) {
        this.body_type = body_type;
    }

    public String getGear_type() {
        return gear_type.toString();
    }

    public void setGear_type(Gear_type gear_type) {
        this.gear_type = gear_type;
    }

    public String getFuel_type() {
        return fuel_type.toString();
    }

    public void setFuel_type(Fuel_type fuel_type) {
        this.fuel_type = fuel_type;
    }

    private enum Make{
        Tesla,
        Volkswagen,
        Peugot,
        Toyota,
        Renault,
        Hyundai,
        Kia,
        Skoda,
        Ford,
        Mercedes,
        Mercedes_Benz,
        BMW,
        Audi,
        Volvo,
        Mazda,
        Nissan,
        Polestar,
        Fiat
    };

    private enum Body_type{
        Hatchback,
        SUV,
        Sedan,
        Smart,
        Cabriolet,
        Station_wagon,
        Coupe,
        Crossover,
        Minivan,
        Roadster,
        Pickup
    };

    private enum Gear_type{
        manual,
        automatic,
        electric_gear
    }

    private enum Fuel_type{
        petrol,
        diesel,
        electricity,
        hybrid
    }
}
