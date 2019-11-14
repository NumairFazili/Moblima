package Entity;

import java.util.ArrayList;
import java.util.List;

public class Settings {

    private double basePrice,childPrice,seniorPrice,holidayPrice,silverPrice,goldPrice,platinumPrice;
    private List<String> holidays;

    public Settings(double basePrice, double childPrice, double seniorPrice, double holidayPrice,double silverPrice,double goldPrice,double platinumPrice,List<String> holidays){
        this.basePrice=basePrice;
        this.childPrice=childPrice;
        this.seniorPrice=seniorPrice;
        this.holidayPrice=holidayPrice;
        this.silverPrice=silverPrice;
        this.goldPrice=goldPrice;
        this.platinumPrice=platinumPrice;
        this.holidays=holidays;
    }


    public double getSilverPrice() {
        return silverPrice;
    }

    public void setSilverPrice(double silverPrice) {
        this.silverPrice = silverPrice;
    }

    public double getGoldPrice() {
        return goldPrice;
    }

    public void setGoldPrice(double goldPrice) {
        this.goldPrice = goldPrice;
    }

    public double getPlatinumPrice() {
        return platinumPrice;
    }

    public void setPlatinumPrice(double platinumPrice) {
        this.platinumPrice = platinumPrice;
    }

    public double getBasePrice() {
        return basePrice;
    }

    public void setBasePrice(double basePrice) {
        this.basePrice = basePrice;
    }

    public double getChildPrice() {
        return childPrice;
    }

    public void setChildPrice(double childPrice) {
        this.childPrice = childPrice;
    }

    public double getSeniorPrice() {
        return seniorPrice;
    }

    public void setSeniorPrice(double seniorPrice) {
        this.seniorPrice = seniorPrice;
    }

    public double getHolidayPrice() {
        return holidayPrice;
    }

    public void setHolidayPrice(double holidayPrice) {
        this.holidayPrice = holidayPrice;
    }

    public List<String> getHolidays() {
        return holidays;
    }

    public void setHolidays(List<String> holidays) {
        this.holidays = holidays;
    }
}
