package com.example.optimatefleet.controller;

import com.example.optimatefleet.model.Car;
import com.example.optimatefleet.model.PreSaleContract;
import com.example.optimatefleet.service.CarService;
import com.example.optimatefleet.service.PreSaleContractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class PreSaleContractController {
    @Autowired
    CarService carService;
    @Autowired
    PreSaleContractService preSaleContractService;

    @GetMapping("/CreatePreSaleContract")
    public String createPreSaleContract(Model model) {
        List<Car> listOfCarsNotSold = carService.fetchAllCarsWithNotSoldStatus();
        model.addAttribute("cars", listOfCarsNotSold);
        return "CreatePreSaleContract";
    }

    @PostMapping("/CreatePreSaleContract")
    public String createPreSaleContract(@ModelAttribute PreSaleContract preSaleContract) {
        preSaleContractService.createPreSaleContract(preSaleContract);
        carService.updateCarStatusToSold(preSaleContract.getLicense_plate());
        return "redirect:/DataRegister";
    }

    @GetMapping("/updatePreSaleContract/{licensePlate}")
    public String updatePreSaleContract(@PathVariable String licensePlate, Model model) {
        PreSaleContract preSaleContract = preSaleContractService.findPreSaleContractByLicensePlate(licensePlate);
        model.addAttribute("preSaleContract", preSaleContract);
        return "EditPreSaleContract";
    }

    @PostMapping("/deletePreSaleContract/{licensePlate}")
    public String deletePreSaleContract(@PathVariable String licensePlate, @RequestParam("deleteReason") String deleteReason) {
        if ("mistake".equals(deleteReason)) { // skal slette kontrakten og rette bilen til ikke solgt status
            preSaleContractService.deletePreSaleContract(licensePlate);
            carService.updateCarStatusToNotSold(licensePlate);
            return "redirect:/DataRegister";
        } else if ("sold".equals(deleteReason)) { // skal slette kontrakten og sætte bilen status til leveret
            preSaleContractService.deletePreSaleContract(licensePlate);
            carService.updateCarStatusToDelivered(licensePlate);
            return "redirect:/DataRegister";
        } else {
            return "redirect:/DataRegister";
        }
    }
}
