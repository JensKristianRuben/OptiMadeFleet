package com.example.optimatefleet.service;

import com.example.optimatefleet.model.Login;
import com.example.optimatefleet.repository.LoginRepository;
import org.apache.juli.logging.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LoginService {
    @Autowired
    LoginRepository loginRepository;

    public Login fetchAndFindLoginDetails(String user_name){
        List<Login> loginList = loginRepository.fetchAndFindLoginDetails(user_name);
        System.out.println(loginList.size());
         Login login = null;

         for (Login element : loginList){
             login = element;
         }
         return login;
    }
}
