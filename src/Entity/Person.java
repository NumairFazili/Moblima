package Entity;

import Controller.DataManager;
import Controller.SearchManager;

import java.util.ArrayList;


public abstract class Person {
    private String name;
    public Person(){}
    void setName(String name){
        this.name = name;
    }
    String getName(){
        return name;
    }
    public String toString() {
        return name;
    }



    public void save(){}









//    public ArrayList<Movie> getTopByRatings(){
//        return SearchManager.get_topN_byRating(getAllMovies());
//    }
//    public ArrayList<Movie> getTopBySales(){
//        SearchManager.calculateSales(DataManager.LoadBookings());
//        return SearchManager.get_topN_bySale(getAllMovies(), DataManager.LoadBookings());
//    }


}
