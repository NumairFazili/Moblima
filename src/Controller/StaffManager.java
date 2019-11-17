package Controller;

import Entity.Staff;

import java.util.Scanner;

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


/**
 Represents the StaffManager class to manage Staff objects
 @author CZ2002 Group 1
 @version 1.0
 @since 15-11-2019
 */
public class StaffManager{

    /**
     * Creates a new StaffManaer object
     */
    private static Staff staff;

    public StaffManager(){staff = new Staff();};

    /**
     * Prompts user to enter username and password and uses AuthManager.getStaff method to check with database if username and password is correct
     * @param input Scanner object
     * @return Staff object corresponding to login details entered
     */
    public static Staff StaffLogin(Scanner input) {

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
            staff = AuthManager.ValidateStaff(username,password);
            if(staff != null){
                System.out.println("Admin account successfully logged in!");
                return staff;
            }
            else{
                System.out.println("The username or password you typed is incorrect. Please try again.");
            }
        }

    }
}