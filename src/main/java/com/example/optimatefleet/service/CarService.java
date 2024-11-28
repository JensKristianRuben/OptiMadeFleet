package com.example.optimatefleet.service;

import com.example.optimatefleet.model.Car;
import com.example.optimatefleet.model.CarModel;
import com.example.optimatefleet.model.RentContract;
import com.example.optimatefleet.repository.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarService {
    @Autowired
    private CarRepository carRepository;

    public void createNewCarModel (CarModel carModel) {
        carRepository.createNewCarModel(carModel);
    }

    public void createNewCar(Car car, String car_model_name) {
        car.setCarModel(carRepository.fetchModelByModelName(car_model_name));
        carRepository.createNewCar(car);
    }

    public List<CarModel> fethAllCarModels()  {
        return carRepository.fethAllCarModels();
    }

    public Car findCarByLicensePlate(String licensePlate){
        List<Car> carsList = carRepository.fetchAllCars();
        System.out.println(carsList);
        Car car = null;

        for (Car element : carsList) {
            if(element.getLicense_plate().equals(licensePlate)){
                car = element;
            }
        }
        return car;
    }
}