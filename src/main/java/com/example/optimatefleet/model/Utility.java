package com.example.optimatefleet.model;

import org.springframework.stereotype.Service;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;

@Service
public class Utility {

    public String roundNumber (double number)    {
        DecimalFormat decimalFormat = new DecimalFormat("#.##");
        DecimalFormatSymbols symbols = new DecimalFormatSymbols(Locale.US);
        decimalFormat.setDecimalFormatSymbols(symbols);

        return decimalFormat.format(number);
    }
}
