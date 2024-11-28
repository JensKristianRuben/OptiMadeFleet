package com.example.optimatefleet.service;

import com.example.optimatefleet.model.Car;
import com.example.optimatefleet.model.CarModel;
import com.example.optimatefleet.model.RentContract;
import com.example.optimatefleet.repository.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CarService {
    @Autowired
    private CarRepository carRepository;

    public void createNewCarModel(CarModel carModel) {
        carRepository.createNewCarModel(carModel);
    }

    public void createNewCar(Car car, String car_model_name) {
        car.setCarModel(carRepository.fetchModelByModelName(car_model_name));
        carRepository.createNewCar(car);
    }

    public List<CarModel> fethAllCarModels() {
        return carRepository.fethAllCarModels();
    }
    public List<Car> fetchAllCars(){
        return carRepository.fetchAllCars();
    }

    //Bruger listen af bilen og tjekker hvilke biler der er ledige og tilføjer dem til en ny liste.
    public List<Car> fecthAllCarWithAvailableStatus(){
        List<Car> listOfCars = fetchAllCars();
        List<Car> allAvailableCars = new ArrayList<>();
        System.out.println(listOfCars);
        for (Car element : listOfCars){
            if (element.getCar_status().equals(Car.CarStatus.available.toString())){
                allAvailableCars.add(element);
            }
        }
        System.out.println(allAvailableCars);

        return allAvailableCars;
    }
    public void updateCarStatusToRented(String licensePlate) {
        List<Car> listOfCars = carRepository.fetchAllCars();
        Car car = null;
        for (Car element : listOfCars) {
            if (element.getLicense_plate().equals(licensePlate)) {
                car = element;
                car.setCar_status(Car.CarStatus.rentet);
                break;
            }
        }
        carRepository.updateCarStatusToRented(car);
    }


    public Car findCarByLicensePlate(String licensePlate) {
        List<Car> carsList = carRepository.fetchAllCars();
        System.out.println(carsList);
        Car car = null;

        for (Car element : carsList) {
            if (element.getLicense_plate().equals(licensePlate)) {
                car = element;
            }
        }
        return car;
    }
}

