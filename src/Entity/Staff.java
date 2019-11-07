package Entity;

import java.util.ArrayList;
import java.util.List;

import Controller.DataManager;


public class Staff extends Person {
    String password;
    
    public Staff(String name, String password){
        this.setName_Staff(name);         //using base class method
        this.password = password;
        //ID should be generated automatically
    }

    public void setPassword(String password){
        this.password = password;
    }
    public void setName_Staff(String name){
        this.setName(name);
    }
    public String getName(){
        return name;
    }
    public boolean checkPassword(String password){
        if (password == this.password){
            return true;
        }
        return false;
    }

    public void createNewMovie(int id,String name,String Language,double rating,String runTime,List<String> cast,String Description,String Director){
        Movie m = new Movie(id, name, Language, rating, runTime, cast, Description, Director);
        DataManager.saveMovies(m);
    }
    public void updateMovieName(Movie m, String s){
        m.setName(s);
    }
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
    public void deleteMovie(Movie m){
        m.setRunTime("ended");
    }


    public void createShowTime(int cinplexID, int cinemaID, int movieID, String time, String status, List<Integer> seats){
        Cinema c = new Cinema(cinplexID, cinemaID, movieID, time, status, seats);
        DataManager.AddShowTimes(c);
    }
    public void updateCinemaID(Cinema c, int i){
        c.setCinemaID(i);
        DataManager.UpdateShowTime(c);
    }
    public void updateMovieID(Cinema c, int i){
        c.setMovieID(i);
        DataManager.UpdateShowTime(c);
    }
    public void updateTime(Cinema c, String s){
        c.setTime(s);
        DataManager.UpdateShowTime(c);
    }
    public void updateStatus(Cinema c, String s){
        c.setStatus(s);
        DataManager.UpdateShowTime(c);
    }
    public void deleteShowTime(Cinema c){
        c.setStatus("ended");
        DataManager.UpdateShowTime(c);
    }

    //Settings
    public Settings showSettings(){
        Settings s = DataManager.LoadSettings();
        return s;
    }
    public void updateBasePrice(Settings s, double base){
        s.setBasePrice(base);
        DataManager.manageSettings(s);
    }
    public void updateChildPrice(Settings s, double child){
        s.setChildPrice(child);
        DataManager.manageSettings(s);
    }
    public void updateSeniorPrice(Settings s, double senior){
        s.setSeniorPrice(senior);
        DataManager.manageSettings(s);
    }
    public void updateHolidayPrice(Settings s, double holiday){
        s.setHolidayPrice(holiday);
        DataManager.manageSettings(s);
    }
    public void updateSilverPrice(Settings s, double silver){
        s.setSilverPrice(silver);
        DataManager.manageSettings(s);
    }
    public void updateGoldPrice(Settings s, double gold){
        s.setGoldPrice(gold);
        DataManager.manageSettings(s);
    }
    public void updatePlatinumPrice(Settings s, double platinum){
        s.setPlatinumPrice(platinum);
        DataManager.manageSettings(s);
    }
    public void updateHoliday(Settings s, ArrayList<String> holiday){
        s.setHolidays(holiday);
        DataManager.manageSettings(s);
    }

    public static void main(String args[]){
        Staff s = new Staff("Cindy", "passwordhere");
        System.out.println(s.ID);
        System.out.println(s.name);
        System.out.println(s.password);

    }

}