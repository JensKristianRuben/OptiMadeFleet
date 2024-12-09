package com.example.optimatefleet.service;

import com.example.optimatefleet.model.Car;
import com.example.optimatefleet.model.PreSaleContract;
import com.example.optimatefleet.repository.CarRepository;
import com.example.optimatefleet.repository.PreSaleContractRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PreSaleContractService {
    @Autowired
    PreSaleContractRepository preSaleContractRepository;
    @Autowired
    CarRepository carRepository;

    public void createPreSaleContract(PreSaleContract preSaleContract) {
        preSaleContractRepository.createPreSaleContract(preSaleContract);
    }

    public List<PreSaleContract> fetchAllPreSaleContracts() {
        return preSaleContractRepository.fetchAllPreSaleContracts();
    }

    public PreSaleContract findPreSaleContractByLicensePlate(String licensePlate) {
        List<PreSaleContract> preSaleContractList = fetchAllPreSaleContracts();
        PreSaleContract preSaleContract = null;
        for (PreSaleContract element : preSaleContractList) {
            if (element.getLicense_plate().equals(licensePlate)) {
                preSaleContract = element;
            }
        }
        return preSaleContract;
    }

    public void deletePreSaleContract(String licensePlate) {
        preSaleContractRepository.deletePreSaleContract(licensePlate);
    }

    public void updatePreSaleContract(PreSaleContract preSaleContract) {
        preSaleContractRepository.updatePreSaleContract(preSaleContract);
    }
    public void updatePreSaleContractToDilevered(String licensePlate){
        preSaleContractRepository.updatePreSaleContractToDilevered(licensePlate);
    }

    public int soldCarsIncome() {
        List<Car> listOfCars = carRepository.fetchAllCars();
        List<PreSaleContract> preSaleContractList = fetchAllPreSaleContracts();
        int soldCarsSum = 0;

        for (PreSaleContract preSaleelement : preSaleContractList) {
            for (Car carElement : listOfCars) {
                if (preSaleelement.getLicense_plate().equals(carElement.getLicense_plate()) && carElement.getCar_status().equals("delivered")) {

                    soldCarsSum += preSaleelement.getPrice();
                }
            }
        }
        return soldCarsSum;
    }
}
