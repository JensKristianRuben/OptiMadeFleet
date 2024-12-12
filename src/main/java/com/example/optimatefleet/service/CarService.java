package com.example.optimatefleet.service;

import com.example.optimatefleet.model.Car;
import com.example.optimatefleet.model.CarModel;
import com.example.optimatefleet.repository.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
        return carRepository.fetchAllCarModels();
    }
    public List<Car> fetchAllCars(){
        return carRepository.fetchAllCars();
    }

    //Bruger listen af bilen og tjekker hvilke biler der er ledige og tilføjer dem til en ny liste.
    public List<Car> fecthAllCarWithAvailableStatus(){ //rename til "cars" og stavefejl i "fetch"
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

    public List<Car> fetchAllCarsWithNotSoldStatus(){
        List<Car> listOfCars = fetchAllCars();
        List<Car> listOfCarsNotSold = new ArrayList<>();

        for (Car element : listOfCars){
            if (!element.isIs_pre_sold()){
                listOfCarsNotSold.add(element);
            }
        }
        return listOfCarsNotSold;
    }
    public List<Car> fetchAllCarsWithSoldStatus(){
        List<Car> listOfCars = fetchAllCars();
        List<Car> listOfCarsNotSold = new ArrayList<>();

        for (Car element : listOfCars){
            if (element.isIs_pre_sold() && !element.getCar_status().equals("delivered")){
                listOfCarsNotSold.add(element);
            }
        }
        return listOfCarsNotSold;
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
        carRepository.updateCar(car);
    }


    public Car findCarByLicensePlate(String licensePlate) {
        List<Car> carsList = carRepository.fetchAllCars();
        Car car = null;

        for (Car element : carsList) {
            if (element.getLicense_plate().equals(licensePlate)) {
                car = element;
            }
        }
        return car;
    }

    public void updateCar (Car car) {
        carRepository.updateCar(car);
    }

    public void DeleteCarFromDB(String license_plate) {
        carRepository.DeleteCarFromDB(license_plate);
    }

    public List<Car> fetchAllCarsAndSortByParam(String sortBy){
        List<Car> listOfCars = carRepository.fetchAllCars();

        switch (sortBy) {
            case "available":
                List<Car> allAvailableCars = new ArrayList<>();
                for (Car element : listOfCars){
                    if (element.getCar_status().equals("available")){
                        allAvailableCars.add(element);
                    }
                }
                return allAvailableCars;
            case "rentet":
                List<Car> allRentedCars = new ArrayList<>();
                for (Car element : listOfCars){
                    if (element.getCar_status().equals("rentet")){
                        allRentedCars.add(element);
                    }
                }
                return allRentedCars;
            case "returned":
                List<Car> allReturnedCars = new ArrayList<>();
                for (Car element : listOfCars){
                    if (element.getCar_status().equals("returned")){
                        allReturnedCars.add(element);
                    }
                }
                return allReturnedCars;
            case "ready_for_invoice":
                List<Car> allUnder_repairCars = new ArrayList<>();
                for (Car element : listOfCars){
                    if (element.getCar_status().equals("ready_for_invoice")){
                        allUnder_repairCars.add(element);
                    }
                }
                return allUnder_repairCars;
            case "delivered":
                List<Car> allDilevered_cars = new ArrayList<>();
                for (Car element : listOfCars){
                    if (element.getCar_status().equals("delivered")){
                        allDilevered_cars.add(element);
                    }
                }
                return allDilevered_cars;
            default:
                return listOfCars;
        }
    }

    public void updateCarStatusToSold(String licensePlate){
        List<Car> listOfCars = fetchAllCars();
        Car car = null;
        for (Car element : listOfCars){
            if (element.getLicense_plate().equals(licensePlate)){
                car = element;
                car.setIs_pre_sold(true);
            }
        }
        carRepository.updateCar(car);
    }
    public void updateCarStatusToNotSold(String licensePlate){
        List<Car> listOfCars = fetchAllCars();
        Car car = null;
        for (Car element : listOfCars){
            if (element.getLicense_plate().equals(licensePlate)){
                car = element;
                car.setIs_pre_sold(false);
            }
        }
        carRepository.updateCar(car);
    }
    public void updateCarStatusToDelivered(String licensePlate){
        List<Car> listOfCars = carRepository.fetchAllCars();
        Car car = null;
        for (Car element : listOfCars) {
            if (element.getLicense_plate().equals(licensePlate)) {
                car = element;
                car.setCar_status(Car.CarStatus.delivered);
                break;
            }
        }
        carRepository.updateCar(car);
    }

    public void updateCarStatusToReady_for_invoice(String licensePlate){
        List<Car> listOfCars = carRepository.fetchAllCars();
        Car car = null;
        for (Car element : listOfCars) {
            if (element.getLicense_plate().equals(licensePlate)) {
                car = element;
                car.setCar_status(Car.CarStatus.ready_for_invoice);
                break;
            }
        }
        carRepository.updateCar(car);
    }

    public void updateCarStatusReturned(String licensePlate){
        List<Car> listOfCars = carRepository.fetchAllCars();
        Car car = null;
        for (Car element : listOfCars) {
            if (element.getLicense_plate().equals(licensePlate)) {
                car = element;
                car.setCar_status(Car.CarStatus.returned);
                break;
            }
        }
        carRepository.updateCar(car);
    }

    public Map<CarModel, Integer> fetchAllCarsWithLowStock() {
        List<Car> cars = fetchAllCarsAndSortByParam("available");
        Map<String, Integer> carModelsCounter = new HashMap<>();
        List<CarModel> carModels = carRepository.fetchAllCarModels();

        for (Car element : cars){
            if(carModelsCounter.containsKey(element.getCar_model_name()))  {
                carModelsCounter.put(element.getCar_model_name(), carModelsCounter.get(element.getCar_model_name()) + 1);
            } else {
                carModelsCounter.put(element.getCar_model_name(), 1);
            }
        }

        Map<CarModel, Integer> lowStockCarModels = new HashMap<>();

        for (Map.Entry<String, Integer> entry : carModelsCounter.entrySet()) {
            if(entry.getValue() < 2) {
                lowStockCarModels.put(carRepository.fetchModelByModelName(entry.getKey()), entry.getValue());
            }
        }

        for (CarModel carModel : carModels) {
            if (!carModelsCounter.containsKey(carModel.getCar_model_name())) {
                lowStockCarModels.put(carModel, 0);
            }
        }

        return lowStockCarModels;
    }
}

