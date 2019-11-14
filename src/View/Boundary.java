package View;

import Controller.DataManager;
import Entity.Booking;
import Entity.Cinema;
import Entity.Movie;
import org.junit.Test;

import java.util.*;

public class Boundary {


    private static final String[] staffOptions={"Create movie listing","Update movie listing","Create cinema showtimes and the movies to be shown","Update cinema showtimes and the movies to be shown","Configure system settings"};
    private static final String[] startOptions ={"MOBLIMA Movie Booking System START", "Admin user","Movie-Goer"};
    private static final String[] userMovieOptions={"Search movie", "View booking history","List the Top 5 ranking by ticket sales","List the Top 5 ranking by overall reviewers’ ratings","List all movies"};
    private static final String[] userOptions={"Existing User","New User","Guest User"};


    public static int ModuleSelection(int choice, Scanner input){

        while(choice != 1 && choice !=2 && choice !=0){
            Boundary.DisplayOptions("startMenu");

            try{
                choice = input.nextInt();
                if(choice == 0){
                    System.exit(0);
                }
                else if(choice ==1 || choice ==2){

                    return choice;
                }
                else{
                    System.out.println("Error! Please enter either 1, 2 or 0:");
                }
            }
            catch(InputMismatchException e){
                System.out.println("That is not an integer, please try again." );
                input.next();
            }


        }

        return 0;
    }

    public static void DisplayOptions(String input) {

        String str[]={};

        switch (input){
            case "staffMenu":
                str=staffOptions;
                break;
            case "startMenu":
                str= startOptions;
                break;

            case "userMovieMenu":
                str=userMovieOptions;
                break;

            case "userMenu":
                str=userOptions;
                break;
        }
        int count = 1;
        for (int i = 0; i < str.length; i++)
            System.out.println(" " + count++ + " : " + str[i]);
        System.out.println(" "+0+" : "+"Exit");
    }

    public static void main(String[] args) {

        Boundary.DisplayOptions("staffMenu");


    }


    public static void DisplayMovie(List<Movie> movieList){
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


    public static void DisplayBookings(List<Booking> bookings){
        int count=0;
        for(Booking booking:bookings){
            System.out.println("Booking number: " + count++);
            System.out.println("Customer Name : "+booking.getCustomerName());
            System.out.println("Customer Type : "+booking.getCustomerType());
            System.out.println("Booking ID : "+booking.getBookingID());
            System.out.println("Cinplex ID : "+booking.getCinplexID());
            System.out.println("Cinema ID : "+booking.getCinemaID());
            System.out.println("Movie ID : "+booking.getMovieID());
            System.out.println("Seat No : "+booking.getSeatNO());
            System.out.println("Showtime : "+booking.getShowTime());
            System.out.println("Booking Time : "+booking.getBookingTime());
            System.out.println("Cinema Class : "+booking.getCinemaClass());
            System.out.println("Movie Type : "+booking.getMovieType());
            System.out.println("Email : "+booking.getEmail());
            System.out.println();
        }


    }



    public static void DisplayMovieReviews(Movie movie){
        int reviewNum = movie.getRating().size() < movie.getReviews().size() ? movie.getRating().size() : movie.getReviews().size();
        List<Integer> rating_list = movie.getRating();
        List<String> review_list = movie.getReviews();
        for(int i = 0; i < reviewNum; i++){
            System.out.println("Rating:"+rating_list.get(i) + " | Review: "+review_list.get(i));
        }
    }

    public static void DisplayCinemas(List<Cinema> cinemas){
        int count=0;
        System.out.println("index"+"\t"+"CinePlex ID" + "\t" + "Cinema ID" + "\t" + "Time" + "\t" + "Status" +"\t"+"Class"+"\t"+"Type");
        for(Cinema cinema:cinemas){
            System.out.println(count++ +"\t"+cinema.getCinplexID() + "\t" + cinema.getCinemaID() + "\t" + cinema.getTime() + "\t" +cinema.getStatus() + "\t" +cinema.getCinemaClass() + "\t" + cinema.getMovieType());
        }
    }
    @Test
    public void t1(){
        DisplaySeating(DataManager.LoadShowTimes(1002).get(1));
    }
    public static void DisplaySeating(Cinema cinema) {
        int value;
        int rows=8;
        int cols=8;
        List<Integer> seating_temp = cinema.getSeats();

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

