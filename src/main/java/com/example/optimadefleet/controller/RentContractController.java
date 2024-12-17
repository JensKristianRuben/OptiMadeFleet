package com.example.optimadefleet.controller;

import com.example.optimadefleet.model.Car;
import com.example.optimadefleet.model.RentContract;
import com.example.optimadefleet.service.CarService;
import com.example.optimadefleet.service.RentContractService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class RentContractController {

    @Autowired
    RentContractService rentContractService;
    @Autowired
    CarService carService;

    @GetMapping("/CreateRentContract")
    public String createRentContract(Model model, HttpSession session) {
        if (session.getAttribute("user") == null) {
            return "redirect:/";
        }
        model.addAttribute("cars", carService.fetchAllCarsWithAvailableStatus());
        return "CreateRentContract";
    }

    @PostMapping("/RentContract")
    public String createRentContract(@ModelAttribute RentContract rentContract) {
        rentContract.setContract_terminated(false);
        rentContractService.createRentContract(rentContract);
        carService.updateCarStatusToRented(rentContract.getLicense_plate());
        return "redirect:/DataRegister";
    }

    @GetMapping("/ShowRentContract/{licensePlate}")
    public String showRentContract(@PathVariable String licensePlate, Model model, HttpSession session) {
        if (session.getAttribute("user") == null) {
            return "redirect:/";
        }
        RentContract rentContract = rentContractService.findContractByLicensePlate(licensePlate);
        Car car = carService.findCarByLicensePlate(licensePlate);
        model.addAttribute("rentContract", rentContract);
        model.addAttribute("car", car);
        return "EditRentContract";
    }

    @PostMapping("/deleteRentContract/{licensePlate}")
    public String deleteRentContract(@ModelAttribute RentContract rentContract, @RequestParam("deleteReason") String deleteReason) {

        if (deleteReason.equals("mistake")) {
            rentContractService.deleteRentContractByLicensePlate(rentContract.getLicense_plate());
            return "redirect:/DataRegister";
        } else if (deleteReason.equals("rentPeriodEnded")) {
            rentContract.setContract_terminated(true);
            carService.updateCarStatusReturned(rentContract.getLicense_plate());
            rentContractService.updateRentContract(rentContract);
            return "redirect:/DataRegister";
        } else {
            return "redirect:/DataRegister";
        }
    }

    @PostMapping("/updateRentContract")
    public String updateRentContract(@ModelAttribute RentContract rentContract) {
        rentContractService.updateRentContract(rentContract);
        return "redirect:/DataRegister";
    }
}
