package com.example.optimatefleet.service;

import com.example.optimatefleet.model.Car;
import com.example.optimatefleet.model.RentContract;
import com.example.optimatefleet.repository.CarRepository;
import com.example.optimatefleet.repository.RentContractRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

@Service
public class RentContractService {

    @Autowired
    RentContractRepository rentContractRepository;
    @Autowired
    CarRepository carRepository;

    public void createRentContract(RentContract rentContract) {
        String rentContractTpye = rentContract.getRent_contract_type();
        LocalDate rentalStartDate = rentContract.getRental_start_date();

        LocalDate rentalEndDate = null;
        if (rentContractTpye.equals("limited")) {
            rentalEndDate = rentalStartDate.plusMonths(5);
        } else if (rentContractTpye.equals("unlimited")) {
            rentalEndDate = rentalStartDate.plusMonths(36);
        }

        rentContract.setRental_end_date(rentalEndDate);
        rentContractRepository.createRentContract(rentContract);
    }

    public List<RentContract> fetchAllOngoingRentContracts(){
        List<RentContract> rentContractList = rentContractRepository.fetchAllRentContracts();
        List<RentContract> ongoingContractList = new ArrayList<>();

        for (RentContract element : rentContractList){
            if (!element.isContract_terminated()){
                ongoingContractList.add(element);
            }
        }
        return ongoingContractList;
    }
    public double calculateAverageRentalTime(){
        List<RentContract> rentContractList = rentContractRepository.fetchAllRentContracts();

        int months = 0;
        int counter = 0;

        for (RentContract element : rentContractList){
            if (element.isContract_terminated()){

                LocalDate startDate = element.getRental_start_date();
                LocalDate endDate = element.getRental_end_date();

                int monthsBetweenStartDateAndEndDate = (int) ChronoUnit.MONTHS.between(startDate, endDate);

                months += monthsBetweenStartDateAndEndDate;
                counter++;
            }
        }
        if (counter > 0){
            return (double) months / counter;
        }else {
            return 0.0;
        }
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

    public void deleteRentContractByLicensePlate(String licensePlate) {
        rentContractRepository.deleteRentContractByLicensePlate(licensePlate);
    }

    public void updateRentContract(RentContract rentContract) {
        rentContractRepository.updateRentContract(rentContract);
    }

    public int calculateMonthlyIncome(){
        List<Car> listOfCars = carRepository.fetchAllCars();
        int monthlyContractIncome = 0;

        for (Car element : listOfCars){
            if (element.getCar_status().equals("rentet")){
                monthlyContractIncome += (int) Double.parseDouble(element.calculateMonthlyPrice());
            }
        }
        return monthlyContractIncome;
    }
}
