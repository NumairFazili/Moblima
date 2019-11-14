package Controller;

import java.util.*;

import Entity.*;
import javafx.util.Pair;

/*
List of Methods
(1)Movie
    createMovie,
    updateMovie[Name, RunTime, Description, Director, Cast],
    saveMovieChanges, 
    deleteMovie
(2)ShowTime(Cinema)
    createShowTime,
    update[CinemaID, MovieID, Time, Status],
    saveShowTimeChanges,
    deleteShowTime
(3)Settings
    showSettings
    update[BasePrice, ChildPrice, SeniorPrice, HolidayPrice, SilverPrice, GoldPrice, PlatinumPrice, Holiday]
    saveSettingsChanges
*/


public class StaffManager {


    public StaffManager(){};


    public static Pair<Integer,Staff> StaffLogin(Scanner input){
        boolean adminauth = false;
        Staff mystaff =new Staff("NULL","NULL") ;
        while(adminauth==false){
            System.out.println("ADMIN USER:");
            System.out.println("Please enter login details, Input -1 to go back:");
            System.out.println("Enter Username:");
            String username = input.next();
            if(username.equals("-1")){
                return new Pair<Integer,Staff>(-1,mystaff);
            }
            System.out.println("Enter Password:");
            String password = input.next();
            mystaff = AuthManager.getStaff(username,password);
            if(mystaff != null){
                System.out.println("Admin account successfully logged in!");
                adminauth = true;
            }
            else{
                System.out.println("The username or password you typed is incorrect. Please try again.");
                adminauth = false;
            }
        }

        return new Pair<>(0, mystaff);

    }









    

    //ShowTime



    //Settings


    public static void main(String args[]){
        
    }

}