package com.example.optimatefleet.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class LoginController {

    @GetMapping("/login")
    public String login() {
        return "index";
    }

    @PostMapping("/login")
    public String login(String username, String password, HttpSession session) {

        if (username.equals("admin") && password.equals("admin")) {
            session.setAttribute("user", username);
            return "redirect:/KPI";
        }

        return "index";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/login";
    }
}
