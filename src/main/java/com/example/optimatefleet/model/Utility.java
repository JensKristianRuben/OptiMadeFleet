package com.example.optimatefleet.model;

import org.springframework.stereotype.Service;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;

@Service
public class Utility {

    public String roundNumber (double number)    {
        DecimalFormat decimalFormat = new DecimalFormat("#.##");
        //Sætter formatet af decimalFormart til at være med "." og ikke ",". Dette er vigtigt til parsing tilbage til double.
        DecimalFormatSymbols symbols = new DecimalFormatSymbols(Locale.US);
        decimalFormat.setDecimalFormatSymbols(symbols);

        return decimalFormat.format(number);
    }
}
