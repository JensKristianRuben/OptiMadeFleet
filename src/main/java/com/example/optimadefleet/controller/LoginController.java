package com.example.optimadefleet.controller;

import com.example.optimadefleet.model.Login;
import com.example.optimadefleet.service.LoginService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController {
    @Autowired
    LoginService loginService;

    @GetMapping("/login")
    public String login() {
        return "index";
    }

    //Vær opmærksom på ikke at slette tjekket på alle de getmappings som indeholder det.
    @PostMapping("/login")
    public String login(@RequestParam("username") String user_name, @RequestParam("password") String user_password, HttpSession session, Model model) {
        Login login = loginService.fetchAndFindLoginDetails(user_name);
        if (login == null) {
            model.addAttribute("failedLogin", "Din adgangskode eller brugernavn er desværre forkert.");
            return "index";
        }
        if (user_name.equals(login.getUser_name()) && user_password.equals(login.getUser_password())) {
            session.setAttribute("user", user_name);
            return "redirect:/KPI";
        } else {
            model.addAttribute("failedLogin", "Din adgangskode eller brugernavn er desværre forkert.");
            return "index";
        }
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/login";
    }
}
