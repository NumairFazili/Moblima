package Entity;

import java.util.List;
        /**
         Represents the price settings for different types of movie-goers and cinema classes as well as holiday dates
         @author CZ2002 Group 1
         @version 1.0
         @since 15-11-2019
         */
public class Settings {
    /**
     * Price parameters for different type of movie-goers, movie-type and cinema class.
     */
    private double basePrice,childPrice,seniorPrice,holidayPrice,silverPrice,goldPrice,platinumPrice,price3D;

    /**
     * List of strings denoting the days where holiday price should be charged, format "DD/MM/YYYY"
     */
    private List<String> holidays;

    /**
     * Creates a new Settings object with the following parameters.
     *
     *@param basePrice base price for a movie  booking
     *@param childPrice child price for a movie booking
     *@param seniorPrice senior price for a movie booking
     *@param holidayPrice holiday price for a movie booking
     *@param silverPrice silver price for cinema class
     *@param goldPrice gold price for cinema class
     *@param platinumPrice platinum price for cinema class
     *@param price3D 3D price for a movie booking
     *@param holidays list of dates designated to be charged holiday price
     */
    public Settings(double basePrice, double childPrice, double seniorPrice, double holidayPrice,double silverPrice,double goldPrice,double platinumPrice,double price3D,List<String> holidays){
        this.basePrice=basePrice;
        this.childPrice=childPrice;
        this.seniorPrice=seniorPrice;
        this.holidayPrice=holidayPrice;
        this.silverPrice=silverPrice;
        this.goldPrice=goldPrice;
        this.platinumPrice=platinumPrice;
        this.price3D=price3D;
        this.holidays=holidays;
    }

    /**
     * Get the price for silver cinema class
     * @return silver cinema class price
     */
    public double getSilverPrice() {
        return silverPrice;
    }

    /**
     * Change the price for silver cinema class
     * @param silverPrice silver cinema class price
     */
    public void setSilverPrice(double silverPrice) {
        this.silverPrice = silverPrice;
    }

    /**
     * Get the price for gold cinema class
     * @return gold cinema class price
     */
    public double getGoldPrice() {
        return goldPrice;
    }

    /**
     * Change the price for gold cinema class
     * @param goldPrice gold cinema class price
     */
    public void setGoldPrice(double goldPrice) {
        this.goldPrice = goldPrice;
    }

    /**
     * Get the price for platinum cinema class
     * @return platinum cinema class price
     */
    public double getPlatinumPrice() {
        return platinumPrice;
    }

    /**
     * Change the price for platinum cinema class
     * @param platinumPrice platinum cinema class price
     */
    public void setPlatinumPrice(double platinumPrice) {
        this.platinumPrice = platinumPrice;
    }

    /**
     * Get the base price for a movie booking
     * @return base price for a movie booking
     */
    public double getBasePrice() {
        return basePrice;
    }

    /**
     * Change the base price for a movie booking
     * @param basePrice base price for a movie booking
     */
    public void setBasePrice(double basePrice) {
        this.basePrice = basePrice;
    }

    /**
     * Get the child price for a movie booking
     * @return child price for a movie booking
     */
    public double getChildPrice() {
        return childPrice;
    }

    /**
     * Change the child price price for a movie booking
     * @param childPrice child price for a movie booking
     */
    public void setChildPrice(double childPrice) {
        this.childPrice = childPrice;
    }

    /**
     * Get the 3D price for a movie booking
     * @return 3D price for a movie booking
     */
    public double getPrice3D() {
        return price3D;
    }

    /**
     * Change the 3D price price for a movie booking
     * @param price3D child price for a movie booking
     */
    public void setPrice3D(double price3D) {
        this.price3D = price3D;
    }

    /**
     * Get the senior price for a movie booking
     * @return  senior price for a movie booking
     */
    public double getSeniorPrice() {
        return seniorPrice;
    }

    /**
     * Change the senior price price for a movie booking
     * @param seniorPrice senior price for a movie booking
     */
    public void setSeniorPrice(double seniorPrice) {
        this.seniorPrice = seniorPrice;
    }

    /**
     * Get the holiday price for a movie booking
     * @return holiday price for a movie booking
     */
    public double getHolidayPrice() {
        return holidayPrice;
    }

    /**
     * Change the holiday price price for a movie booking
     * @param holidayPrice holiday price for a movie booking
     */
    public void setHolidayPrice(double holidayPrice) {
        this.holidayPrice = holidayPrice;
    }

    /**
     * Get the list of dates to be charged for holiday price
     * @return list of dates to be charged for holiday price
     */
    public List<String> getHolidays() {
        return holidays;
    }

    /**
     * Change the list of dates to be charged for holiday price
     * @param holidays list of dates to be charged for holiday price
     */
    public void setHolidays(List<String> holidays) {
        this.holidays = holidays;
    }
}
