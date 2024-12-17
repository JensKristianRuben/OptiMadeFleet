package com.example.optimadefleet.repository;

import com.example.optimadefleet.model.Car;
import com.example.optimadefleet.model.CarModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CarRepository {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void createNewCarModel (CarModel carModel) {
        String sql = "INSERT INTO car_model (car_model_name, average_rental_time_in_months, " +
                "engine_size, seat_count, door_count, horsepower, make, " +
                "body_type, gear_type, fuel_type) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        jdbcTemplate.update(sql,
                carModel.getCar_model_name(),
                carModel.getAverage_rental_time_in_months(),
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

    public void createNewCar (Car car) {
        String sql = "INSERT INTO car (license_plate, car_model_name, vin_number, original_price, " +
                "registration_tax, car_status, odometer, is_pre_sold, " +
                "sale_price, alert_damages_not_fixed, year_of_manufactoring, color)" +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        jdbcTemplate.update(sql,
                car.getLicense_plate(),
                car.getCarModel().getCar_model_name(),
                car.getVin_number(),
                car.getOriginal_price(),
                car.getRegistration_tax(),
                car.getCar_status(),
                car.getOdometer(),
                car.isIs_pre_sold(),
                car.getSale_price(),
                car.isAlert_damages_not_fixed(),
                car.getYear_of_manufactoring(),
                car.getColor()
                );
    }

    public List<CarModel> fetchAllCarModels()  {
        return jdbcTemplate.query("SELECT * FROM car_model", new BeanPropertyRowMapper<>(CarModel.class));
    }

    public CarModel fetchModelByModelName(String car_model_name) {
        return jdbcTemplate.queryForObject("SELECT * FROM car_model WHERE car_model_name=?",  new Object[]{car_model_name},new BeanPropertyRowMapper<>(CarModel.class));
    }

    //Looper igennem carsList og sætter carModel på tilhørende bil
    public List<Car> fetchAllCars() {
        List<Car> carsList = jdbcTemplate.query("SELECT * FROM car", new BeanPropertyRowMapper<>(Car.class));
        List<CarModel> carModelList = fetchAllCarModels();

        for(Car car : carsList){
            for(CarModel carModel : carModelList){
                if(car.getCar_model_name().equals(carModel.getCar_model_name())){
                    car.setCarModel(carModel);
                }
            }
        }
        return carsList;
    }

    public void updateCar (Car car) {
        String sql = "UPDATE car SET license_plate = ?, car_model_name = ?, vin_number = ?, original_price = ?, " +
                "registration_tax = ?, car_status = ?, odometer = ?, is_pre_sold = ?, " +
                "sale_price = ?, alert_damages_not_fixed = ?, year_of_manufactoring = ?, color = ? " +
                "WHERE license_plate = ?";

        jdbcTemplate.update(sql,
                car.getLicense_plate(),
                car.getCar_model_name(),
                car.getVin_number(),
                car.getOriginal_price(),
                car.getRegistration_tax(),
                car.getCar_status(),
                car.getOdometer(),
                car.isIs_pre_sold(),
                car.getSale_price(),
                car.isAlert_damages_not_fixed(),
                car.getYear_of_manufactoring(),
                car.getColor(),
                car.getLicense_plate());
    }

    public void DeleteCarFromDB(String license_plate) {
        String sql = "DELETE FROM car WHERE license_plate = ?";

        jdbcTemplate.update(sql, license_plate);
    }

}
