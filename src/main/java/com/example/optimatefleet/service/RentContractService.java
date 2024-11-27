package com.example.optimatefleet.service;

import com.example.optimatefleet.model.RentContract;
import com.example.optimatefleet.repository.RentContractRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RentContractService {

    @Autowired
    RentContractRepository rentContractRepository;

    public void createRentContract(RentContract rentContract){
        rentContractRepository.createRentContract(rentContract);
    }

    public List<RentContract> showAllRentContracts(){

        return rentContractRepository.fetchAllRentContracts();
    }

    public RentContract findByLicensePlate(String licensePlate){
        List<RentContract> rentContractList = rentContractRepository.fetchAllRentContracts();
        RentContract rentContract = null;
        for (RentContract element : rentContractList){
            if (element.getLicense_plate().equals(licensePlate)){
                rentContract = element;
            }
        }

        return rentContract;
    }
    public void deleteByLicensePlate(String licensePlate){
        rentContractRepository.deleteByLicensePlate(licensePlate);
    }
}
