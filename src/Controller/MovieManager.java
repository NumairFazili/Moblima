package Controller;

import Entity.Cinema;
import Entity.Movie;
import Entity.User;
import View.Boundary;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class MovieManager {


    ShowTimeManager showTimeManager;


    public MovieManager(){
        showTimeManager=new ShowTimeManager();
    }


    public Movie selectMovieByID(int id){
        ArrayList<Movie> m_list = DataManager.LoadMovies("");
        for (int i = 0; i < m_list.size(); i++){
            if (m_list.get(i).getId() == id){
                return m_list.get(i);
            }
        }
        return null;
    }

    public Movie selectMovieByID(ArrayList<Movie> m_list, int id){
        for (int i = 0; i < m_list.size(); i++){
            if (m_list.get(i).getId() == id){
                return m_list.get(i);
            }
        }
        return null;
    }


    public ArrayList<Movie> getAllMovies(){
        ArrayList<Movie> m_list = DataManager.LoadMovies("");
        return m_list;
    }

    public ArrayList<Movie> getMovieByName(String s){
        return DataManager.LoadMovies(s);
    }


    public ArrayList<Movie> getTopByRatings(){
        return SearchManager.get_topN_byRating(this.getAllMovies());
    }
    public ArrayList<Movie> getTopBySales(){
        SearchManager.calculateSales(DataManager.LoadBookings());
        return SearchManager.get_topN_bySale(this.getAllMovies(), DataManager.LoadBookings());
    }
}
