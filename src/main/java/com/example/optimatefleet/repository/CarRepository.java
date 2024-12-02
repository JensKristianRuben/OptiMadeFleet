package com.example.optimatefleet.repository;

import com.example.optimatefleet.model.Car;
import com.example.optimatefleet.model.CarModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CarRepository {
    @Autowired
    private JdbcTemplate carTemplate;

    public void createNewCarModel (CarModel carModel) {
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

    public void createNewCar (Car car) {
        String sql = "INSERT INTO car (license_plate, car_model_name, vin_number, original_price, " +
                "registration_tax_pr_month, car_status, odometer, damage_report_id, is_pre_sold, " +
                "sale_price, alert_damages_not_fixed, year_of_manufactoring, color)" +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        carTemplate.update(sql,
                car.getLicense_plate(),
                car.getCarModel().getCar_model_name(),
                car.getVin_number(),
                car.getOriginal_price(),
                car.getRegistration_tax_pr_month(),
                car.getCar_status(),
                car.getOdometer(),
                null,
                car.isIs_pre_sold(),
                car.getSale_price(),
                car.isAlert_damages_not_fixed(),
                car.getYear_of_manufactoring(),
                car.getColor()
                );
    }

    public List<CarModel> fethAllCarModels()  {
        return carTemplate.query("SELECT * FROM car_model", new BeanPropertyRowMapper<>(CarModel.class));
    }

    public CarModel fetchModelByModelName(String car_model_name) { //Skal laves om så det sker i servicelaget
        return carTemplate.queryForObject("SELECT * FROM car_model WHERE car_model_name=?",  new Object[]{car_model_name},new BeanPropertyRowMapper<>(CarModel.class));
    }

    public List<Car> fetchAllCars() {
        //Tag med til eksamen? - Kompleks kode
        List<Car> carsList = carTemplate.query("SELECT * FROM car", new BeanPropertyRowMapper<>(Car.class));
        List<CarModel> carModelList = fethAllCarModels();

        for(Car car : carsList){
            for(CarModel carModel : carModelList){
                if(car.getCar_model_name().equals(carModel.getCar_model_name())){
                    car.setCarModel(carModel);
                }
            }
        }
        return carsList;
    }
    //Opdaterer bilen status som udlejet
    public void updateCarStatusToRented(Car car){
        String updateSql = "UPDATE car SET car_status = ? WHERE license_plate = ?";

        carTemplate.update(updateSql, car.getCar_status(), car.getLicense_plate());

    }

    public void updateCar (Car car) {
        System.out.println(car);
        String sql = "UPDATE car SET license_plate = ?, car_model_name = ?, vin_number = ?, original_price = ?, " +
                "registration_tax_pr_month = ?, car_status = ?, odometer = ?, damage_report_id = ?, is_pre_sold = ?, " +
                "sale_price = ?, alert_damages_not_fixed = ?, year_of_manufactoring = ?, color = ? " +
                "WHERE license_plate = ?";

        carTemplate.update(sql,
                car.getLicense_plate(),
                car.getCar_model_name(),
                car.getVin_number(),
                car.getOriginal_price(),
                car.getRegistration_tax_pr_month(),
                car.getCar_status(),
                car.getOdometer(),
                null,
                car.isIs_pre_sold(),
                car.getSale_price(),
                car.isAlert_damages_not_fixed(),
                car.getYear_of_manufactoring(),
                car.getColor(),
                car.getLicense_plate());
    }

    public void DeleteCarFromDB(String license_plate) {
        String sql = "DELETE FROM car WHERE license_plate = ?";

        carTemplate.update(sql, license_plate);
    }

}
