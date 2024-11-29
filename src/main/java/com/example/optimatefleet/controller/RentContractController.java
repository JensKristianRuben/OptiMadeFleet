package com.example.optimatefleet.controller;

import com.example.optimatefleet.model.RentContract;
import com.example.optimatefleet.service.CarService;
import com.example.optimatefleet.service.RentContractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class RentContractController {

    @Autowired
    RentContractService rentContractService;
    @Autowired
    CarService carService;

    @GetMapping("/CreateRentContract")
    public String createRentContract(Model model) {
        model.addAttribute("cars", carService.fecthAllCarWithAvailableStatus());
        return "CreateRentContract";
    }

    @PostMapping("/RentContract")
    public String createRentContract(@ModelAttribute RentContract rentContract) {
        rentContractService.createRentContract(rentContract);
        carService.updateCarStatusToRented(rentContract.getLicense_plate());
        System.out.println(rentContract);
        return "redirect:/DataRegister";
    }

    @GetMapping("/DataRegister")
    public String showAllRentalContractsCarsAndPreSaleContract(Model model) {
        List<RentContract> rentContractList = rentContractService.showAllRentContracts();
        model.addAttribute("rentContracts", rentContractList);
        return "DataRegister";
    }

    @GetMapping("/ShowRentContract/{licensePlate}")
    public String showRentContract(@PathVariable String licensePlate, Model model) {
        RentContract rentContract = rentContractService.findContractByLicensePlate(licensePlate);
        model.addAttribute("rentContract", rentContract);
        return "EditRentContract";
    }

    @PostMapping("/deleteRentContract/{licensePlate}")
    public String deleteRentContract(@PathVariable String licensePlate) {
        System.out.println(licensePlate);
        rentContractService.deleteByLicensePlate(licensePlate);
        return "redirect:/DataRegister";
    }

    @PostMapping("/updateRentContract")
    public String updateRentContract(@ModelAttribute RentContract rentContract){
        rentContractService.updateRentContract(rentContract);
        return "redirect:/DataRegister";
    }
}
