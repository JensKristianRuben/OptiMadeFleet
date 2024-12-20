package com.example.optimadefleet.service;

import com.example.optimadefleet.model.Car;
import com.example.optimadefleet.model.PreSaleContract;
import com.example.optimadefleet.repository.CarRepository;
import com.example.optimadefleet.repository.PreSaleContractRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
    //Looper gennem PreSaleContractList og finder de kontrakter der har samme nummerplade som i bil listen
    //Hvis der er match og bil statussen ikke er leveret returneres listen af igangværende kontrakter.
    public List<PreSaleContract> fetchAllOngoingPreSaleContracts() {
        List<PreSaleContract> PreSaleContractList = fetchAllPreSaleContracts();
        List<Car> listOfCars = carRepository.fetchAllCars();
        List<PreSaleContract> ongoingPreSaleContractList = new ArrayList<>();
        for (PreSaleContract preSaleelement : PreSaleContractList) {
            for (Car carElement : listOfCars) {
                if (preSaleelement.getLicense_plate().equals(carElement.getLicense_plate()) && !carElement.getCar_status().equals("delivered")) {
                    ongoingPreSaleContractList.add(preSaleelement);
                }
            }
        }

        return ongoingPreSaleContractList;
    }

    public void deletePreSaleContract(String licensePlate) {
        preSaleContractRepository.deletePreSaleContract(licensePlate);
    }

    public void updatePreSaleContract(PreSaleContract preSaleContract) {
        preSaleContractRepository.updatePreSaleContract(preSaleContract);
    }

    public void updatePreSaleContractToDilevered(String licensePlate) {
        preSaleContractRepository.updatePreSaleContractToDelivered(licensePlate);
    }

    public int soldCarsIncome() {
        List<PreSaleContract> preSaleContractList = fetchAllPreSaleContracts();
        int soldCarsSum = 0;

        for (PreSaleContract preSaleelement : preSaleContractList) {
            if (preSaleelement.isCar_delivered()) {
                soldCarsSum += preSaleelement.getPrice();
            }
        }
        return soldCarsSum;
    }
}
