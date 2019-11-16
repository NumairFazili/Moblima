package Controller;

import java.util.*;

import Entity.*;

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

    public static Staff StaffLogin(Scanner input){
        Staff mystaff;
        while(true){
            System.out.println("ADMIN USER:");
            System.out.println("Please enter login details, Input -1 to go back:");
            System.out.println("Enter Username:");
            String username = input.next();
            if(username.equals("-1")){
                return null;
            }
            System.out.println("Enter Password:");
            String password = input.next();
            mystaff = AuthManager.getStaff(username,password);
            if(mystaff != null){
                System.out.println("Admin account successfully logged in!");
                return mystaff;
            }
            else{
                System.out.println("The username or password you typed is incorrect. Please try again.");
            }
        }

    }
}