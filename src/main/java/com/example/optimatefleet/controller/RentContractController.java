package com.example.optimatefleet.controller;

import com.example.optimatefleet.model.RentContract;
import com.example.optimatefleet.service.RentContractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class RentContractController {

    @Autowired
    RentContractService rentContractService;

    @GetMapping("/RentContract")
    public String createRentContract(){
        return "RentContract";
    }

    @PostMapping("/RentContract")
    public String createRentContract(@ModelAttribute RentContract rentContract){
        rentContractService.createRentContract(rentContract);
        return "redirect:/DataRegister";
    }

    @GetMapping("/DataRegister")
    public String showAllRentalContractsCarsAndPreSaleContract(Model model){
        List<RentContract> rentContractList = rentContractService.showAllRentContracts();
        model.addAttribute("rentContracts", rentContractList);
        return "DataRegister";
    }
    @GetMapping("/ShowRentContract/{licensePlate}")
    public String showRentContract(@PathVariable String licensePlate, Model model){
        RentContract rentContract = rentContractService.findByLicensePlate(licensePlate);
        model.addAttribute("rentContract", rentContract);
        return "ShowRentContract";
    }
    @PostMapping("/deleteRentContract/{licensePlate}")
    public String deleteRentContract(@PathVariable String licensePlate) {
        rentContractService.deleteByLicensePlate(licensePlate);
        return "redirect:/DataRegister";
    }
}
