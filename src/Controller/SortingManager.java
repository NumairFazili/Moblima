/*
Author: Bingyan
Methods:
1.sortMovies

Helpers:
1.SortByName  2. SortByRating
 */
package Controller;

import Entity.Movie;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class SortingManager{


    public SortingManager(){}

    private static String sortMoviesBy = "name";


    private static ArrayList<Movie> sortMovies(String by, ArrayList<Movie> movies) throws IllegalArgumentException{
        String str1 = new String("name");
        if(by.equalsIgnoreCase(new String("name"))){
            Collections.sort(movies, new SortByName());

        }
        else if(by.equalsIgnoreCase(new String("rating"))){
            Collections.sort(movies, new SortByRating());
        }
        else{
            throw new IllegalArgumentException("no such argument, argument must be 'name' or 'rating'.\n");
        }
        return movies;
    }

    //default is sort by name
    private static ArrayList<Movie> sortMovies(ArrayList<Movie> movies) throws IllegalArgumentException{
        return SortingManager.sortMovies(sortMoviesBy,movies);
    }




}

class SortByName implements Comparator<Movie> {
    @Override
    public int compare(Movie o1, Movie o2){
        return o1.getName().compareTo(o2.getName());
    }

}
class SortByRating implements Comparator<Movie>{
    @Override
    public int compare( Movie o1, Movie o2){

        if(o1.getAvgRating()<o2.getAvgRating()){
            return 1;
        }
        else if(o1.getAvgRating()==o2.getAvgRating()){
            return 0;
        }
        else{
            return -1;
        }
    }
}
