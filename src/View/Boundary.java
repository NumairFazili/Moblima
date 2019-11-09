package View;

import Entity.Cinema;
import Entity.Movie;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Boundary {


    public static void DisplayOptions(String[] str) {
        int count = 0;
        for (int i = 0; i < str.length; i++)
            System.out.println(" " + String.valueOf(count++) + " : " + str[i]);
        System.out.println(-1 + " : " + "Exit");
    }


    public static void DisplayMovies(List<Movie> movieList){
        System.out.println("ID" + "  " + "Title" + "  " + "Rating" + "  " + "Language");
        for(Movie movie:movieList){
            System.out.println(movie.getId() + "  " + movie.getName() + "  " + movie.getRating() + "  " +movie.getLanguage());
        }
    }

    public static void DisplayMovie(Movie movie){
        System.out.println("ID: "+movie.getId());
        System.out.println("Title: "+movie.getName());
        System.out.println("Rating: "+movie.getRating());
        System.out.println("Language: "+movie.getLanguage());
        System.out.println("Cast: "+movie.getCast());
        System.out.println("Description: "+movie.getDescription());
    }

    public static void DisplayCinemas(List<Cinema> cinemas){
        int count=0;
        System.out.println("index"+"  "+"CinePlex ID" + "  " + "Cinema ID" + "  " + "Time" + "  " + "Status" +"  "+"Class");
        for(Cinema cinema:cinemas){
            System.out.println(count++ +"  "+cinema.getCinplexID() + "  " + cinema.getCinemaID() + "  " + cinema.getTime() + "  " +cinema.getStatus() + "  " +cinema.getCinemaClass());
        }
    }

    public static void DisplaySeating(List<Integer> seating) {
        int value;
        int rows=8;
        int cols=8;

        System.out.print("   ");
        for(int i=0;i<cols;i++)
            System.out.print(i+"  ");
        System.out.println();

         for(int i=0;i<rows;i++){
             System.out.print(i+" ");
             for (int j=0;j<cols;j++){
                 value=i*cols + j;

                 if(seating.get(value)==1)
                     System.out.print(" X ");
                 else
                     System.out.print(" A ");
             }
             System.out.println();
         }
    }
}

