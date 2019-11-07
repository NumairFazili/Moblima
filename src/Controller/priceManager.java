package Controller;

import Entity.Settings;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class priceManager {


    private int age;
    private String cinemaClass;

    Settings settings;



    public priceManager(int age, String cinemaClass){
        this.age=age;
        this.cinemaClass=cinemaClass;
        settings=DataManager.LoadSettings();
    }



    public static Boolean dateCheck(List<String> dates){
        SimpleDateFormat dfParse = new SimpleDateFormat("dd/MM/yyyy");
        Date date;
        Date currentDate=new Date();
        for(int i=0;i<dates.size();i++){

            try {
                date=dfParse.parse((dates.get(i)));
                currentDate=dfParse.parse(dfParse.format(currentDate));
                int result=currentDate.compareTo(date);
                if(result==0)
                    return true;

            } catch (ParseException e) {
                e.printStackTrace();
            }
        }

        return false;

    }


    public double getPrice(){

        double discount=0;

        if(age < 15)
            discount+=settings.getChildPrice();
        if(age >65)
            discount+=settings.getSeniorPrice();

        if(dateCheck(settings.getHolidays()))
            discount+=settings.getHolidayPrice();

        switch(cinemaClass){
            case "silver":
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
