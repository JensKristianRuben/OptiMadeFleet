package com.example.optimatefleet.service;

import com.example.optimatefleet.model.CarModel;
import com.example.optimatefleet.repository.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CarService {
    @Autowired
    private CarRepository carRepository;

    public void saveNewCarModel (CarModel carModel) {
        carRepository.saveNewCarModel(carModel);
    }
}
