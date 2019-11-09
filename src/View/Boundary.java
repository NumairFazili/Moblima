package View;

import Entity.Cinema;
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
        System.out.println("ID" + "\t" + "Title" + " \t" + "Rating(Avg)" + "\t" + "Language");
        for(Movie movie:movieList){
            System.out.println(movie.getId() + "\t" + movie.getName() + "\t" + movie.getAvgRating() + "\t" +movie.getLanguage());
        }
    }

    public static void DisplayMovie(Movie movie){
        System.out.println("ID\t\t\t: "+movie.getId());
        System.out.println("Title\t\t: "+movie.getName());
        System.out.println("Rating(Avg)\t\t: "+movie.getAvgRating());
        System.out.println("Language\t\t: "+movie.getLanguage());
        System.out.println("Age Requirement\t: "+movie.getMinAge());
        System.out.println("Cast\t\t: "+movie.getCast());
        System.out.println("Description\t\t: "+movie.getDescription());
    }

    public static void DisplayMovieReviews(Movie movie){
        int reviewNum = movie.getRating().size() < movie.getReviews().size() ? movie.getRating().size() : movie.getReviews().size();
        List<Integer> rating_list = movie.getRating();
        List<String> review_list = movie.getReviews();
        for(int i = 0; i < reviewNum; i++){
            System.out.println("Rating\t: "+rating_list.get(i));
            System.out.println("Reviewd\t: "+review_list.get(i));
        }
    }

    public static void DisplayCinemas(List<Cinema> cinemas){
        int count=0;
        System.out.println("index"+"\t"+"CinePlex ID" + "\t" + "Cinema ID" + "\t" + "Time" + "\t" + "Status" +"\t"+"Class"+"\t"+"Type");
        for(Cinema cinema:cinemas){
            System.out.println(count++ +"\t"+cinema.getCinplexID() + "\t" + cinema.getCinemaID() + "\t" + cinema.getTime() + "\t" +cinema.getStatus() + "\t" +cinema.getCinemaClass() + "\t" + cinema.getMovieType());
        }
    }

    public static void DisplaySeating(List<Integer> seating) {
        int value;
        int rows=8;
        int cols=8;
        List<Integer> seating_temp = seating;

        System.out.print("   ");
        for(int i=0;i<cols;i++)
            System.out.print(i+"  ");
        System.out.println();

        for(int i=0;i<rows;i++){
            System.out.print(i+" ");
            for (int j=0;j<cols;j++){
                value=i*cols + j;

                for(int k=0; k < seating_temp.size(); k++){
                if(seating_temp.get(k)==value){
                    System.out.print(" X ");
                    seating_temp.remove(k);
                }else{
                    System.out.print(" A ");
                }
                }
             }
            System.out.println();
         }
    }
}

