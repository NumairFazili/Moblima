package Controller;

import Entity.Booking;
import Entity.Movie;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MovieManager {

    static int SIZE_OF_PQ = 5;

    public MovieManager(){
    }

    public static Movie selectMovieByID(int id){
        ArrayList<Movie> m_list = DataManager.LoadMovies("");
        
        for(Movie m: m_list){
            if(m.getId()==id){
                return m;
            }
        }
        return null;
    }

    public static Movie selectMovieByID(ArrayList<Movie> m_list, int id){
        for (int i = 0; i < m_list.size(); i++){
            if (m_list.get(i).getId() == id){
                return m_list.get(i);
            }
        }
        return null;
    }

    public static ArrayList<Movie> getAllMovies(){
        ArrayList<Movie> m_list = DataManager.LoadMovies("");
        return m_list;
    }

    public static ArrayList<Movie> getMovieByName(String s){
        return DataManager.LoadMovies(s);
    }

    public static ArrayList<Movie> getTopByRatings(){
        ArrayList<Movie> to_return = SortingManager.sortByRating(getAllMovies(), SIZE_OF_PQ);
        return to_return;
    }
    
    public static ArrayList<Movie> getTopBySales(){
        ArrayList<Movie> movies = getAllMovies();
        ArrayList<Movie> to_return = new ArrayList<Movie>();
        HashMap<Integer,Integer> sales_map = calculateSales(DataManager.LoadBookings());
        Map<Integer, Integer> sorted_map = SortingManager.sortByValue(sales_map);
        for(Map.Entry<Integer,Integer> entry: sorted_map.entrySet()){

            Movie m = selectMovieByID(movies,entry.getKey());
            if(m == null){
                continue;
            }
            to_return.add(m);
            if(to_return.size()>=SIZE_OF_PQ){
                break;
            }
        }
        return to_return;
    }

    private static HashMap<Integer,Integer> calculateSales(ArrayList<Booking> bookings){

        HashMap<Integer,Integer> map = new HashMap<Integer, Integer>();
        for(int i = 0;i<bookings.size();i++){
            int movie_id = bookings.get(i).getMovieID();
            int count = map.containsKey(movie_id)? map.get(movie_id):0;
            map.put(movie_id, count+1);
        }
        return map;
    }

}
