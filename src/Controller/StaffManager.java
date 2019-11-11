package Controller;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import Entity.*;
import View.Boundary;

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


public class StaffManager extends PersonManager{

    Staff staff;
    public StaffManager(){};
    public StaffManager(Staff s){
        staff = s;
        if(s == null){
            throw new IllegalArgumentException("Staff cannot be null!");
        }
    }

    //MOVIE
    public void createMovieListing(Scanner input){

        System.out.println("Enter movieID:");
        int movieid = input.nextInt();
        input.nextLine(); //Catch newline from .nextInt()
        System.out.println("Enter movie name:");
        String moviename = input.nextLine();
        System.out.println("Enter language:");
        String language = input.nextLine();
        System.out.println("Enter runtime:");
        String runtime = input.nextLine();
        System.out.println("Enter cast members, split by ','");
        List<String> cast = Arrays.asList(input.nextLine().split(","));
        System.out.println("Enter description:");
        String description = input.nextLine();
        System.out.println("Enter director:");
        String director = input.nextLine();
        System.out.println("Enter Minimum age:");
        int minage = input.nextInt();
        input.nextLine();
        this.createNewMovie(movieid, moviename, language, Arrays.asList(), runtime, cast, description, director, Arrays.asList(), minage);

    }
    public void createNewMovie(int id,String name,String Language,List<Integer> rating,String runTime,List<String> cast,String Description,String Director, List<String> comments, int minAge){

        Movie m = new Movie(id, name, Language, rating, runTime, cast, Description, Director, comments, minAge);
        DataManager.SaveMovies(m);
    }
    public void UpdateMovie(Scanner input){

        ArrayList<Movie> allMovies = this.getAllMovies();
        Boundary.DisplayMovies(allMovies);
        System.out.println("Enter ID of the movie to be updated: " );
        int inputsearchint = input.nextInt();
        input.nextLine();
        Movie mymovie = SearchManager.find_Movie_byID(allMovies, inputsearchint);
        System.out.println("Choose attribute of movie to be edited: " );
        int choice = -1;

    }
    public void updateMovieName(Movie m, String s){
        m.setName(s);
    }
    public void updateMovieLanguage(Movie m, String s){ m.setLanguage(s); }
    public void updateMovieRunTime(Movie m, String s){
        m.setRunTime(s);
    }
    public void updateMovieDescription(Movie m, String s){
        m.setDescription(s);
    }
    public void updateMovieDirector(Movie m, String s){
        m.setDirector(s);
    }
    public void updateMovieCast(Movie m, List<String> cast){
        m.setCast(cast);
    }
    public void updateMovieMinAge(Movie m, int age){
        m.setMinAge(age);
    }
    public boolean saveMovieChanges(Movie m){
        return DataManager.manageMovie(m, false);
    }
    public boolean deleteMovie(Movie m){
        return DataManager.manageMovie(m, true);
    }

    

    //ShowTime
    public void createShowTime(int cinplexID, int cinemaID, int movieID, String time, String status, String cinemaClass, List<Integer> seats, String movieType){
        Cinema c = new Cinema(cinplexID, cinemaID, movieID, time, status, cinemaClass, seats, movieType);
        DataManager.AddShowTimes(c);
    }
    public void updateCinemaID(Cinema c, int i){
        c.setCinemaID(i);
    }
    public void updateMovieID(Cinema c, int i){
        c.setMovieID(i);
    }
    public void updateTime(Cinema c, String s){
        c.setTime(s);
    }
    public void updateStatus(Cinema c, String s){
        c.setStatus(s);
    }
    public void updateClass(Cinema c, String s){
        c.setCinemaClass(s);
    }
    public void updateMovieType(Cinema c, String s){
        c.setMovieType(s);
    }
    public boolean saveShowTimeChanges(Cinema c){
        return DataManager.UpdateShowTime(c);
    }
    public void deleteShowTime(Cinema c){
        c.setStatus("ended");
    }



    //Settings
    public Settings showSettings(){
        Settings s = DataManager.LoadSettings();
        return s;
    }
    public void updateBasePrice(Settings s, double base){
        s.setBasePrice(base);
    }
    public void updateChildPrice(Settings s, double child){
        s.setChildPrice(child);
    }
    public void updateSeniorPrice(Settings s, double senior){
        s.setSeniorPrice(senior);
    }
    public void updateHolidayPrice(Settings s, double holiday){
        s.setHolidayPrice(holiday);
    }
    public void updateSilverPrice(Settings s, double silver){
        s.setSilverPrice(silver);
    }
    public void updateGoldPrice(Settings s, double gold){
        s.setGoldPrice(gold);
    }
    public void updatePlatinumPrice(Settings s, double platinum){
        s.setPlatinumPrice(platinum);
    }
    public void updateHoliday(Settings s, List<String> holiday){
        s.setHolidays(holiday);
    }
    public boolean saveSettingsChanges(Settings s){
        return DataManager.manageSettings(s);
    }

    public static void main(String args[]){
        
    }

}