package View;

import Entity.Movie;

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

    public static void DisplaySeating(List<Integer> seating) {
        int value;
        int rows=3;
        int cols=3;

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

