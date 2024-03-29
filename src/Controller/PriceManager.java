package Controller;

import Entity.Cinema;
import Entity.Settings;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 Represents the PriceManager class, to handle price calculations and booking expiration date checking
 @author CZ2002 Group 1
 @version 1.0
 @since 15-11-2019
 */
public class PriceManager {


    /**
     * Customer type (regular/senior/child)
     */
    private String userType;

    /**
     * Cinema object
     */
    private Cinema cinema;

    /**
     * Settings object
     */
    private Settings settings;


    /**
     * Creates a new priceManager object with the following parameters
     * @param userType customer type
     * @param cinema Cinema object
     */
    public PriceManager(String userType, Cinema cinema){
        this.userType=userType;
        this.cinema=cinema;
        this.settings=DataManager.loadSettings();
    }


    /**
     * Checks if current date is a non working day. Non working day includes weekends and also the list of holidays passed in. 
     * @param holidays List of String objects in the format of "dd/MM/yyyy"
     * @return True if current date is holiday, False otherwise
     */
    private Boolean dateCheck(List<String> holidays){
        SimpleDateFormat dfParse = new SimpleDateFormat("dd/MM/yyyy");
        Calendar c = Calendar.getInstance();

        int weekend=c.get(Calendar.DAY_OF_WEEK);


        if(weekend==1 || weekend==7)
            return true;

        Date date;
        Date ShowDate;
        for(int i=0;i<holidays.size();i++){
            try {
                ShowDate=dfParse.parse(cinema.getTime());
                date=dfParse.parse((holidays.get(i)));
                int result=ShowDate.compareTo(date);
                if(result==0){

                    return true;}

            } catch (ParseException e) {
                e.printStackTrace();
            }
        }

        return false;

    }


    /**
     * Calculate and return price of movie booking based on customer type, if showtime is on a holiday, movie type and cinema class.
     * @return price of movie booking
     */
    public double getPrice(){

        double discount=0;
        switch (userType){
            case "Child":
                discount+=settings.getChildPrice();
                break;
            case "Senior":
                discount+=settings.getSeniorPrice();
                break;
        }

        if(this.dateCheck(settings.getHolidays()))
            discount+=settings.getHolidayPrice();

        if(cinema.getMovieType().equals("3D"))
            discount+=settings.getPrice3D();

        switch(cinema.getCinemaClass()){
            case "Silver":
                discount+=settings.getSilverPrice();
                break;
            case "Gold":
                discount+=settings.getGoldPrice();
                break;
            case "Platinum":
                discount+=settings.getPlatinumPrice();
                break;
        }
        return settings.getBasePrice() + discount;

    }
}


