package Controller;

import Entity.Cinema;
import Entity.Settings;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class priceManager {


    private int age;
    private Cinema cinema;

    Settings settings;



    public priceManager(int age, Cinema cinema){
        this.age=age;
        this.cinema=cinema;
        settings=DataManager.LoadSettings();
    }



    public  Boolean dateCheck(List<String> dates){
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

        if(age < 15)
            discount+=settings.getChildPrice();
        if(age >65)
            discount+=settings.getSeniorPrice();

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

//    public static void main(String[] args) {
//        Cinema cinema=new Cinema(1,1,1005,"13/11/2019 16:30","Now Showing","Silver", Arrays.asList(),"3D");
//        priceManager priceManager=new priceManager(21,cinema);
//
//        System.out.println(priceManager.getPrice());
//    }
}
