package Controller;

import java.util.ArrayList;
import java.util.List;

import Entity.*;

//put some common functions here
/*
(1) Movie
    getAllMovies
    getMovieByName
    selectMovieByID
    listTopBySales
    listTopByRatings
(2) ShowTime
    getShowTimesByCineplex
    getShowTimesByMovie
*/


public class PersonManager{

    public ArrayList<Movie> getAllMovies(){
        ArrayList<Movie> m_list = DataManager.LoadMovies("");
        return m_list;
    }

    public ArrayList<Movie> getMovieByName(String s){
        return DataManager.LoadMovies(s);
    }

    public Movie selectMovieByID(ArrayList<Movie> m_list, int id){
        for (int i = 0; i < m_list.size(); i++){
            if (m_list.get(i).getId() == id){
                return m_list.get(i);
            }
        }
        return null;
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

    public ArrayList<Movie> getTopByRatings(){
        return SearchManager.get_topN_byRating(getAllMovies());
    }
    public ArrayList<Movie> getTopBySales(){
        return SearchManager.get_topN_bySale(getAllMovies(), DataManager.LoadBookings());
    }
 
    public List<Cinema> getAllShowTimes(){
        List<Cinema> c_list = DataManager.LoadShowTimes(-1);
        return c_list;
    }
    public List<Cinema> getShowTimesByCineplex(int cineplexID){
        List<Cinema> c_list = DataManager.LoadShowTimes(-1);
        List<Cinema> return_list = new ArrayList<Cinema>();
        for(Cinema c: c_list){
            if(c.getCinplexID() == cineplexID){
                return_list.add(c);
            }
        }
        return return_list;
    }
    public List<Cinema> getShowTimesByMovie(int movieID){
        return DataManager.LoadShowTimes(movieID);
    }

}