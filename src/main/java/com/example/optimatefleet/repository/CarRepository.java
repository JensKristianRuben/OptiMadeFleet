package com.example.optimatefleet.repository;

import com.example.optimatefleet.model.CarModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class CarRepository {
    @Autowired
    private JdbcTemplate carTemplate;

    public void saveNewCarModel (CarModel carModel) {
        String sql = "INSERT INTO car_model (car_model_name, average_rental_time_in_months, " +
                "price_a_month, engine_size, seat_count, door_count, horsepower, make, " +
                "body_type, gear_type, fuel_type) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        carTemplate.update(sql,
                carModel.getCar_model_name(),
                carModel.getAverage_rental_time_in_months(),
                carModel.getPrice_a_month(),
                carModel.getEngine_size(),
                carModel.getSeat_count(),
                carModel.getDoor_count(),
                carModel.getHorsepower(),
                carModel.getMake(),
                carModel.getBody_type(),
                carModel.getGear_type(),
                carModel.getFuel_type()
                );
    }
}
