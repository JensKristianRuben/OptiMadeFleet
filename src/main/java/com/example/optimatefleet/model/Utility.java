package com.example.optimatefleet.model;

import org.springframework.stereotype.Service;

@Service
public class Utility {

    public Number roundNumber (double number)    {
        if(number % 1 == 0)    {
            return (int) number;
        }else {
            return Math.round(number * 100.0) / 100.0;
        }

    }
}
