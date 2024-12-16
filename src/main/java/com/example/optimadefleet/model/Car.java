package com.example.optimadefleet.model;

public class Car {
    private String license_plate;
    String car_model_name;                  // Is only to match data when fetching from DB
    CarModel carModel;
    private String vin_number;
    private int original_price;
    private double registration_tax;
    private CarStatus car_status;
    private int odometer;
    private DamageReport damageReport;
    private boolean is_pre_sold;
    //Sale price skal kunne nulles
    private int sale_price;
    private boolean alert_damages_not_fixed;
    private int year_of_manufactoring;
    private String color;

    public Car(String license_plate, String car_model_name, CarModel carModel, String vin_number, int original_price, double registration_tax, CarStatus car_status, int odometer, boolean is_pre_sold, int sale_price, int year_of_manufactoring, String color) {
        this.license_plate = license_plate;
        this.car_model_name = car_model_name;
        this.carModel = carModel;
        this.vin_number = vin_number;
        this.original_price = original_price;
        this.registration_tax = registration_tax;
        this.car_status = car_status;
        this.odometer = odometer;
        this.is_pre_sold = is_pre_sold;
        this.sale_price = sale_price;
        this.alert_damages_not_fixed = false;
        this.year_of_manufactoring = year_of_manufactoring;
        this.color = color;
    }

    public Car() {
    }

    public String getCar_model_name() {
        return car_model_name;
    }

    public void setCar_model_name(String car_model_name) {
        this.car_model_name = car_model_name;
    }

    public void setCar_status(CarStatus car_Car_status) {
        this.car_status = car_Car_status;
    }

    public String getCar_status() {
        return car_status.toString();
    }

    public String getCar_statusString() {
        if (car_status == CarStatus.available) {
            return "Klar";
        }
        if (car_status == CarStatus.rentet) {
            return "Udlejet";
        }
        if (car_status == CarStatus.returned) {
            return "Returneret";
        }
        //Hedder under repair, burde have et andet navn.
        if (car_status == CarStatus.ready_for_invoice) {
            return "Klar til fakturering";
        }
        if (car_status == CarStatus.delivered) {
            return "Leveret";
        } else {
            return "Ingen status";
        }
    }

    public String getLicense_plate() {
        return license_plate;
    }

    public void setLicense_plate(String license_plate) {
        this.license_plate = license_plate;
    }

    public CarModel getCarModel() {
        return carModel;
    }

    public void setCarModel(CarModel carModel) {
        this.carModel = carModel;
    }

    public String getVin_number() {
        return vin_number;
    }

    public void setVin_number(String vin_number) {
        this.vin_number = vin_number;
    }

    public int getOriginal_price() {
        return original_price;
    }

    public void setOriginal_price(int original_price) {
        this.original_price = original_price;
    }

    public double getRegistration_tax() {
        return registration_tax;
    }

    public void setRegistration_tax(double registration_tax) {
        this.registration_tax = registration_tax;
    }

    public int getOdometer() {
        return odometer;
    }

    public void setOdometer(int odometer) {
        this.odometer = odometer;
    }

    public DamageReport getDamageReport() {
        return damageReport;
    }

    public void setDamageReport(DamageReport damageReport) {
        this.damageReport = damageReport;
    }

    public boolean isIs_pre_sold() {
        return is_pre_sold;
    }

    public String isIs_pre_soldString() {
        if (is_pre_sold) {
            return "Solgt";
        } else {
            return "Ikke solgt";
        }
    }

    public void setIs_pre_sold(boolean is_pre_sold) {
        this.is_pre_sold = is_pre_sold;
    }

    public int getSale_price() {
        return sale_price;
    }

    public void setSale_price(int sale_price) {
        this.sale_price = sale_price;
    }

    public boolean isAlert_damages_not_fixed() {
        return alert_damages_not_fixed;
    }

    public void setAlert_damages_not_fixed(boolean alert_damages_not_fixed) {
        this.alert_damages_not_fixed = alert_damages_not_fixed;
    }

    public int getYear_of_manufactoring() {
        return year_of_manufactoring;
    }

    public void setYear_of_manufactoring(int year_of_manufactoring) {
        this.year_of_manufactoring = year_of_manufactoring;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public enum CarStatus {
        available,
        rentet,
        returned,
        ready_for_invoice,
        delivered
    }
//Returnere registreringsafgiften pr. måned for en given bil ud fra hvilke af de 3 prisklasser bilen ligger i.
    public double calculateRegistrationTax()    {
        double monthlyRegistrationTax = 0;

        if(original_price <= Constants.FIRST_THRESH_HOLD)  {
            monthlyRegistrationTax = original_price * Constants.FIRST_TRESH_HOLD_PERCENT;
        }else if(original_price > Constants.FIRST_THRESH_HOLD && original_price <= Constants.SECOND_THRESH_HOLD)    {
            double firstThreshHold = Constants.FIRST_THRESH_HOLD * Constants.FIRST_TRESH_HOLD_PERCENT;
            double secondThreshHold = (original_price - Constants.FIRST_THRESH_HOLD) * Constants.SECOND_TRESH_HOLD_PERCENT;
            monthlyRegistrationTax = firstThreshHold + secondThreshHold;
        }else if(original_price > Constants.SECOND_THRESH_HOLD)    {
            double firstThreshHold = Constants.FIRST_THRESH_HOLD * Constants.FIRST_TRESH_HOLD_PERCENT;
            double secondThreshHold = (Constants.SECOND_THRESH_HOLD - Constants.FIRST_THRESH_HOLD) * Constants.SECOND_TRESH_HOLD_PERCENT;
            double thirdThreshHold = (original_price - Constants.SECOND_THRESH_HOLD) * Constants.THIRD_TRESH_HOLD_PERCENT;
            monthlyRegistrationTax = firstThreshHold + secondThreshHold + thirdThreshHold;
        }

        return monthlyRegistrationTax;
    }
//VAT står for Value Added Tax'
    public double calculateVAT()   {
        return original_price * Constants.VAT;
    }

    public String calculateMonthlyPrice()    {
        Utility utility = new Utility();
        return utility.roundNumber((original_price + calculateRegistrationTax()) * Constants.MONTHLY_EARNINGS_PERCENT);
    }

    @Override
    public String toString() {
        return "Car{" +
                "license_plate='" + license_plate + '\'' +
                ", car_model_name='" + car_model_name + '\'' +
                ", carModel=" + carModel +
                ", vin_number='" + vin_number + '\'' +
                ", original_price=" + original_price +
                ", registration_tax=" + registration_tax +
                ", car_status=" + car_status +
                ", odometer=" + odometer +
                ", damageReport=" + damageReport +
                ", is_pre_sold=" + is_pre_sold +
                ", sale_price=" + sale_price +
                ", alert_damages_not_fixed=" + alert_damages_not_fixed +
                ", year_of_manufactoring=" + year_of_manufactoring +
                ", color='" + color + '\'' +
                '}';
    }
}


