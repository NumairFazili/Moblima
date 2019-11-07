package Controller;

import java.util.ArrayList;
import java.util.List;

import Entity.*;

/*
List of Methods
(1)Staff
    verifyPassword
(2)Movie
    showMovies,
    createMovie,
    updateMovie[Name, RunTime, Description, Director, Cast],
    deleteMovie
(3)ShowTime(Cinema)
    showShowTimes,
    createShowTime,
    update[CinemaID, MovieID, Time, Status],
    deleteShowTime
(4)Settings
    showSettings
    update[BasePrice, ChildPrice, SeniorPrice, HolidayPrice, SilverPrice, GoldPrice, PlatinumPrice, Holiday]
    saveSettings
*/


public class StaffManager {

    Staff staff;

    public boolean verifyPassword(String username, String password){

        ArrayList<Staff> staffList = DataManager.loadStaff();

        for(int i = 0; i < staffList.size(); i++){
            if(staffList.get(i).getName() == username){
                if(staffList.get(i).checkPassword(password)){
                    staff = staffList.get(i);
                    return true;
                }
                return false;
            }
        }
        return false;
    }

    //MOVIE 
    public ArrayList<Movie> showMovies(){
        ArrayList<Movie> m_list = DataManager.loadMovie();
        return m_list;
    }
    public void createMovie(int id,String name,String Language,double rating,String runTime,List<String> cast,String Description,String Director){
        staff.createNewMovie(id, name, Language, rating, runTime, cast, Description, Director);
    }
    public void updateMovieName(Movie m, String s){
        staff.updateMovieName(m, s);
    }
    public void updateMovieRunTime(Movie m, String s){
        staff.updateMovieRunTime(m, s);
    }
    public void updateMovieDescription(Movie m, String s){
        staff.updateMovieDescription(m, s);
    }
    public void updateMovieDirector(Movie m, String s){
        staff.updateMovieDirector(m, s);
    }
    public void updateMovieCast(Movie m, List<String> cast){
        staff.updateMovieCast(m, cast);
    }
    public void deleteMovie(Movie m){
        staff.deleteMovie(m);
    }


    //Cinema
    public void createShowTime(int cinplexID, int cinemaID, int movieID, String time, String status, List<Integer> seats){
        staff.createShowTime(cinplexID, cinemaID, movieID, time, status, seats);
    }
    public List<Cinema> showShowTimes(int cineplexID){
        List<Cinema> c_list = DataManager.LoadShowTimes(cineplexID);
        return c_list;
    }
    public void updateCinemaID(Cinema c, int i){
        staff.updateCinemaID(c, i);
    }
    public void updateMovieID(Cinema c, int i){
        staff.updateMovieID(c, i);
    }
    public void updateTime(Cinema c, String s){
        staff.updateTime(c, s);
    }
    public void updateStatus(Cinema c, String s){
        staff.updateStatus(c, s);
    }
    public void deleteShowTime(Cinema c){
        staff.deleteShowTime(c);
    }

    //USER
    /*
    void viewUserBookingHistory(User u){
        u.viewBookingHistory();
    }
    */

    //Settings
    public Settings showSettings(){
        Settings s = DataManager.LoadSettings();
        return s;
    }
    public void updateBasePrice(Settings s, double base){
        staff.updateBasePrice(s, base);
    }
    public void updateChildPrice(Settings s, double child){
        staff.updateChildPrice(s, child);
    }
    public void updateSeniorPrice(Settings s, double senior){
        staff.updateSeniorPrice(s, senior);
    }
    public void updateHolidayPrice(Settings s, double holiday){
        staff.updateHolidayPrice(s, holiday);
    }
    public void updateSilverPrice(Settings s, double silver){
        staff.updateSilverPrice(s, silver);
    }
    public void updateGoldPrice(Settings s, double gold){
        staff.updateGoldPrice(s, gold);
    }
    public void updatePlatinumPrice(Settings s, double platinum){
        staff.updatePlatinumPrice(s, platinum);
    }
    public void updateHoliday(Settings s, ArrayList<String> holiday){
        staff.updateHoliday(s, holiday);
    }

    public static void main(String args[]){
        
    }

}