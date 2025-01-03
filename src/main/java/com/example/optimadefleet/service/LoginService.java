package com.example.optimadefleet.service;

import com.example.optimadefleet.model.Login;
import com.example.optimadefleet.repository.LoginRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LoginService {
    @Autowired
    LoginRepository loginRepository;

    public Login fetchAndFindLoginDetails(String user_name){
        List<Login> loginList = loginRepository.fetchAndFindLoginDetails(user_name);
         Login login = null;

         for (Login element : loginList){
             login = element;
         }
         return login;
    }
}