package Controller;

import Entity.Cinema;
import Entity.Settings;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class priceManager {


    private String userType;
    private Cinema cinema;
    private Settings settings;



    public priceManager(String userType, Cinema cinema){
        this.userType=userType;
        this.cinema=cinema;
        this.settings=DataManager.LoadSettings();
    }



    private  boolean dateCheck(List<String> dates){
        SimpleDateFormat dfParse = new SimpleDateFormat("dd/MM/yyyy");
        Calendar c = Calendar.getInstance();

        int weekend=c.get(Calendar.DAY_OF_WEEK);
        if(weekend==1 || weekend==7)
            return true;

        Date date;
        Date ShowDate;
        for(int i=0;i<dates.size();i++){
            try {
                ShowDate=dfParse.parse(cinema.getTime());
                date=dfParse.parse((dates.get(i)));
                //currentDate=dfParse.parse(dfParse.format(currentDate));
                int result=ShowDate.compareTo(date);
                if(result==0){

                    return true;}

            } catch (ParseException e) {
                e.printStackTrace();
            }
        }

        return false;

    }


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
