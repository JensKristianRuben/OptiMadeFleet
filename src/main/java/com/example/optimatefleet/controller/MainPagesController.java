package com.example.optimatefleet.controller;

import com.example.optimatefleet.model.Car;
import com.example.optimatefleet.model.CarModel;
import com.example.optimatefleet.model.PreSaleContract;
import com.example.optimatefleet.model.RentContract;
import com.example.optimatefleet.service.CarService;
import com.example.optimatefleet.service.PreSaleContractService;
import com.example.optimatefleet.service.RentContractService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

@Controller
public class MainPagesController {
    @Autowired
    CarService carService;
    @Autowired
    RentContractService rentContractService;
    @Autowired
    PreSaleContractService preSaleContractService;

    @GetMapping("/DataRegister")
    public String dataRegisterPage(Model model, @RequestParam(defaultValue = "allCars") String sortBy, HttpSession session) {
        if (session.getAttribute("user") == null) {
            return "redirect:/";
        }
        List<RentContract> rentContractList = rentContractService.fethAllOngoingRentContracts();
        List<Car> carsList = carService.fetchAllCarsAndSortByParam(sortBy);
        List<PreSaleContract> preSaleContracts = preSaleContractService.fetchAllOngoingPreSaleContracts();
        model.addAttribute("sortBy", sortBy);
        model.addAttribute("carsList", carsList);
        model.addAttribute("rentContracts", rentContractList);
        model.addAttribute("preSaleContracts", preSaleContracts);
        return "DataRegister";
    }

    @GetMapping("/DamageReportPage")
    public String damageReportPage(Model model, @RequestParam(defaultValue = "returned") String sortBy, HttpSession session) {
        if (session.getAttribute("user") == null) {
            return "redirect:/";
        }
        List<Car> carsList = carService.fetchAllCarsAndSortByParam(sortBy);
        model.addAttribute("sortBy", sortBy);
        model.addAttribute("carsList", carsList);

        return "DamageReportPage";
    }

    @GetMapping("/KPI")
    public String KPIPage(Model model, @RequestParam(defaultValue = "allCars") String sortBy, HttpSession session) {
        if (session.getAttribute("user") == null) {
            return "redirect:/";
        }
        List<Car> carsList = carService.fetchAllCarsAndSortByParam(sortBy);
        int allCarsCount = carService.fetchAllCars().size();
        int rentedCarsCount = carService.fetchAllCarsAndSortByParam("rentet").size();
        int soldCarsCount = carService.fetchAllCarsAndSortByParam("delivered").size();
        int monthlyContractIncome = rentContractService.calculateMonthlyIncome();
        int preSoldCars = carService.fetchAllCarsWithSoldStatus().size();
        int notPreSoldCars = carService.fetchAllCarsWithNotSoldStatus().size();
        int soldCarsSum = preSaleContractService.soldCarsIncome();
        double averageRentTime = rentContractService.calculateAverageRentalTime();
        Map<CarModel, Integer> lowStockCarModels = carService.fetchAllCarsWithLowStock();



        model.addAttribute("sortBy", sortBy);
        model.addAttribute("carsList", carsList);
        model.addAttribute("allCarsCount", allCarsCount);
        model.addAttribute("rentedCarsCount", rentedCarsCount);
        model.addAttribute("soldCarsCount", soldCarsCount);
        model.addAttribute("preSoldCars", preSoldCars);
        model.addAttribute("notPreSoldCars", notPreSoldCars);
        model.addAttribute("monthlyContractIncome", monthlyContractIncome);
        model.addAttribute("soldCarsSum", soldCarsSum);
        model.addAttribute("averageRentTime", averageRentTime);
        model.addAttribute("lowStockCarModels", lowStockCarModels);
        return "KPI";
    }
}
