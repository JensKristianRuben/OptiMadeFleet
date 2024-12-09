package com.example.optimatefleet.service;

import com.example.optimatefleet.model.RentContract;
import com.example.optimatefleet.repository.RentContractRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class RentContractService {

    @Autowired
    RentContractRepository rentContractRepository;

    public void createRentContract(RentContract rentContract) {
        String rentContractTpye = rentContract.getRent_contract_type();
        LocalDate rentalStartDate = rentContract.getRental_start_date();

        LocalDate rentalEndDate = null;
        if (rentContractTpye.equals("limited")) {
            rentalEndDate = rentalStartDate.plusMonths(5);
        } else if (rentContractTpye.equals("unlimited")) {
            rentalEndDate = rentalStartDate.plusMonths(36);
        }

        System.out.println(rentalEndDate);

        rentContract.setRental_end_date(rentalEndDate);
        rentContractRepository.createRentContract(rentContract);
    }

    public List<RentContract> showAllRentContracts() {

        return rentContractRepository.fetchAllRentContracts();
    }

    public RentContract findContractByLicensePlate(String licensePlate) {
        List<RentContract> rentContractList = rentContractRepository.fetchAllRentContracts();
        RentContract rentContract = null;
        for (RentContract element : rentContractList) {
            if (element.getLicense_plate().equals(licensePlate)) {
                rentContract = element;
            }
        }

        return rentContract;
    }

    public void deleteByLicensePlate(String licensePlate) {
        rentContractRepository.deleteByLicensePlate(licensePlate);
    }

    public void updateRentContract(RentContract rentContract) {
        rentContractRepository.updateRentContract(rentContract);
    }
}
