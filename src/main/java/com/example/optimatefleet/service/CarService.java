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


    //Bil statusen skal sættes her. Der skal itereres igennem listen af biler, bilen skal findes ud fra nummerplade
    //når bilen er fundet skal den returneres og til carRepository og updateres i databasen.
//    public Car updateCarStatusToRented(String licensePlate){
//        List<Car> listOfCars = carRepository.fetchAllCars();
//        Car car = null;
//        for (Car element : listOfCars){
//            if (element.getLicense_plate().equals(licensePlate)){
//               car = element;
//                car.setCar_status(Car.CarStatus.rentet);
//                break;
//            }
//        }
//        return car;
//    }

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

