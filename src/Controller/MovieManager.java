package Controller;

import Entity.Booking;
import Entity.Movie;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class MovieManager {

    private static final int SIZE_OF_PQ = 5;

    protected ShowTimeManager showTimeManager;


    public MovieManager(){
        showTimeManager=new ShowTimeManager();
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
        return DataManager.LoadMovies("");
    }


    public ArrayList<Movie> getTopByRatings(){
        ArrayList<Movie> movies = getAllMovies();
        ArrayList<Movie> to_return = new ArrayList<Movie>();
            Collections.sort(movies,new SortByRating());
            for(int i = 0;i<Math.min(SIZE_OF_PQ,movies.size());i++){
                to_return.add(movies.get(i));
            }
        return to_return;
    }

    public ArrayList<Movie> getTopBySales(){
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

    public static double getAvgRating(Movie movie){
        double averageRating;
        if(movie.getRating().size()> 1){
            averageRating = 0;
            for(int i = 0; i < movie.getRating().size(); i++)
                averageRating += movie.getRating().get(i);
            return averageRating /= movie.getRating().size();
        }
        return -1;
    }
}
