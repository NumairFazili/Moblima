package Controller;

import Entity.Booking;
import Entity.Movie;

import java.util.*;

/**
 Represents the MovieManager class, to manage movie objects
 @author CZ2002 Group 1
 @version 1.0
 @since 15-11-2019
 */
public class MovieManager {

    /**
     * Number of movies to return when getting movies by top sales or ratings
     */
    private static final int SIZE_OF_PQ = 5;

    protected ShowTimeManager showTimeManager;

    public MovieManager(){
        showTimeManager=new ShowTimeManager();
    }



    /**
     * Select and return a Movie object by movie ID from an ArrayList of Movie objects
     * @param id movie ID of movie object to be returned
     * @param m_list ArrayList of movie objects
     * @return Movie object corresponding to input movie ID
     */
    public static Movie selectMovieByID(ArrayList<Movie> m_list, int id){
        for (int i = 0; i < m_list.size(); i++){
            if (m_list.get(i).getId() == id){
                return m_list.get(i);
            }
        }
        return null;
    }

    /**
     * Get details of all movies from database
     * @return ArrayList of Movie objects
     */
    public static ArrayList<Movie> getAllMovies(){
        return DataManager.LoadMovies("");
    }


    /**
     * Gets top movies by ratings from database
     * @return ArrayList size of "SIZE_OF_PQ" Movie objects
     */
    public static ArrayList<Movie> getTopByRatings(){
        ArrayList<Movie> movies = getAllMovies();
        ArrayList<Movie> to_return = new ArrayList<Movie>();
            Collections.sort(movies,new SortByRating());
            for(int i = 0;i<Math.min(SIZE_OF_PQ,movies.size());i++){
                to_return.add(movies.get(i));
            }
        return to_return;
    }



    /**
     * Get average rating of a Movie object
     * @param movie input Movie object
     * @return Average rating of Movie object
     */
    public static double getAvgRating(Movie movie){
        double averageRating;
        if(movie.getRating().size()>=1){
            averageRating = 0;
            for(int i = 0; i < movie.getRating().size(); i++)
                averageRating += movie.getRating().get(i);
            return averageRating /= movie.getRating().size();
        }
        return -1;
    }

    /**
     * Gets top movies by sales from database
     * @return ArrayList size of "SIZE_OF_PQ" Movie objects
     */
    public static ArrayList<Movie> getTopBySales(){
        ArrayList<Movie> movies = getAllMovies();
        ArrayList<Movie> to_return = new ArrayList<Movie>();
        HashMap<Integer,Integer> sales_map = BookingManager.calculateSales();
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


}
