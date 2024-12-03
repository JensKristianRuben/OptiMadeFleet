package com.example.optimatefleet.service;

import com.example.optimatefleet.model.PreSaleContract;
import com.example.optimatefleet.repository.PreSaleContractRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PreSaleContractService {
    @Autowired
    PreSaleContractRepository preSaleContractRepository;
    public void createPreSaleContract(PreSaleContract preSaleContract){
        preSaleContractRepository.createPreSaleContract(preSaleContract);
    }

    public List<PreSaleContract> fetchAllPreSaleContracts(){
        return preSaleContractRepository.fetchAllPreSaleContracts();
    }
    public PreSaleContract findPreSaleContractByLicensePlate(String licensePlate){
        List<PreSaleContract> preSaleContractList = fetchAllPreSaleContracts();
        PreSaleContract preSaleContract = null;
        for (PreSaleContract element : preSaleContractList){
            if (element.getLicense_plate().equals(licensePlate)){
                preSaleContract = element;
            }
        }
        return preSaleContract;
    }

    public void deletePreSaleContract(String licensePlate){
        preSaleContractRepository.deletePreSaleContract(licensePlate);
    }
}
