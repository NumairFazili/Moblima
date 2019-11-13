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



    public static Staff getStaff(String username, String password){

        ArrayList<Staff> staffList = DataManager.Loadstaff();

        for(int i = 0; i < staffList.size(); i++){
            if(staffList.get(i).getName().equals(username)){
                if(staffList.get(i).checkPassword(password)){
                    return staffList.get(i);
                }
                return null;
            }
        }
        return null;
    }





    

    //ShowTime



    //Settings


    public static void main(String args[]){
        
    }

}