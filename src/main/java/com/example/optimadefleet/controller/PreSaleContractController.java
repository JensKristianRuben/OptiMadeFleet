package com.example.optimadefleet.controller;

import com.example.optimadefleet.model.Car;
import com.example.optimadefleet.model.PreSaleContract;
import com.example.optimadefleet.model.Utility;
import com.example.optimadefleet.service.CarService;
import com.example.optimadefleet.service.PreSaleContractService;
import jakarta.servlet.http.HttpSession;
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
    @Autowired
    Utility utilityService;

    @GetMapping("/CreatePreSaleContract")
    public String createPreSaleContract(Model model, HttpSession session) {
        if (session.getAttribute("user") == null) {
            return "redirect:/";
        }
        List<Car> listOfCarsNotSold = carService.fetchAllCarsWithNotSoldStatus();
        model.addAttribute("cars", listOfCarsNotSold);
        return "CreatePreSaleContract";
    }

    @PostMapping("/CreatePreSaleContract")
    public String createPreSaleContract(@ModelAttribute PreSaleContract preSaleContract) {
        preSaleContract.setCar_delivered(false);
        preSaleContractService.createPreSaleContract(preSaleContract);
        carService.updateCarStatusToSold(preSaleContract.getLicense_plate());
        return "redirect:/DataRegister";
    }

    @GetMapping("/updatePreSaleContract/{licensePlate}")
    public String updatePreSaleContract(@PathVariable String licensePlate, Model model) {
        PreSaleContract preSaleContract = preSaleContractService.findPreSaleContractByLicensePlate(licensePlate);
        String priceInEuro = utilityService.roundNumber(preSaleContract.calculatePriceToEuro());
        model.addAttribute("preSaleContract", preSaleContract);
        model.addAttribute("priceInEuro", priceInEuro);
        return "EditPreSaleContract";
    }

    @PostMapping("/deletePreSaleContract/{licensePlate}")
    public String deletePreSaleContract(@PathVariable String licensePlate, @RequestParam("deleteReason") String deleteReason) {
        if ("mistake".equals(deleteReason)) { // skal slette kontrakten og rette bilen til ikke solgt status
            preSaleContractService.deletePreSaleContract(licensePlate);
            carService.updateCarStatusToNotSold(licensePlate);
            return "redirect:/DataRegister";
        } else if ("sold".equals(deleteReason)) { // skal slette kontrakten og sætte bilen status til leveret
            preSaleContractService.updatePreSaleContractToDilevered(licensePlate);
            carService.updateCarStatusToDelivered(licensePlate);
            return "redirect:/DataRegister";
        } else {
            return "redirect:/DataRegister";
        }
    }

    @PostMapping("/UpdatePreSaleContract")
    public String updatePreSaleContract(@ModelAttribute PreSaleContract preSaleContract){
        preSaleContractService.updatePreSaleContract(preSaleContract);
        return "redirect:/DataRegister";
    }
}
