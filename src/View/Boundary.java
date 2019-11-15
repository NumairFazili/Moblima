package View;

import Controller.DataManager;
import Entity.*;

import java.util.*;

public class Boundary {


    private static final String[] staffOptions={"Create movie listing","Update movie listing","Create cinema showtimes and the movies to be shown","Update cinema showtimes and the movies to be shown","Remove cinema showtimes and the movies to be shown","Configure system settings","List the Top 5 ranking by ticket sales","List the Top 5 ranking by overall reviewers’ ratings",};
    private static final String[] startOptions ={"Admin user","Movie-Goer"};
    private static final String[] userMovieOptions={"Search movie", "View booking history","List the Top 5 ranking by ticket sales","List the Top 5 ranking by overall reviewers’ ratings","List all movies"};
    private static final String[] userOptions={"Existing User","New User"};
    private static final String[] settingsOptions={"Base price","Child price","Senior price","Holiday price","Silver price","Gold price","Platinum price","3Dprice","Holidays"};
    private static final String[] movieOptions={"movie name","language","runtime","Cast member","Synopsis","director","Status"};
    private static final String[] cinemaOptions={"Cineplex_ID","Cinema_ID","Movie_ID","ShowTime","class","MovieType"};


    public static int ModuleSelection(int choice, Scanner input){

        while(choice != 1 && choice !=2 && choice !=0){
            System.out.println("MOBLIMA Movie Booking System ");
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
            case "settingsMenu":
                str=settingsOptions;
                break;
            case "moviesMenu":
                str=movieOptions;
                break;
            case "cinemaMenu":
                str=cinemaOptions;
                break;
        }
        int count = 1;
        for (int i = 0; i < str.length; i++)
            System.out.println(" " + count++ + " : " + str[i]);

        if(str.equals("settingsMenu") || str.equals("moviesMenu") || str.equals("cinemaMenu"))
            System.out.println(" "+0+" : "+"Done");
        else
            System.out.println(" "+0+" : "+"Exit");

        System.out.println();
    }



    public static void DisplayMovie(List<Movie> movieList){
        System.out.format("%-5s %-25s %-15s %-15s %n", "ID", "Title", "Rating(Avg)", "Language");
        for(Movie movie:movieList){

            String rating;
            if (movie.getAvgRating() == -1)
                rating="NA";
            else
                rating=String.format("%.3g",movie.getAvgRating());

            System.out.format("%-5d %-25s %-15s %-15s %n", movie.getId(), movie.getName(),rating, movie.getLanguage());

        }
        System.out.println();
    }

    public static void DisplayMovie(Movie movie){

        System.out.format("%-10s   %-20s   %-7s   %-10s  %-15s   %-10s         %-80s  %-30s  %n","Movie ID","Movie Name","Rating",
                "Language","Status","Director","Cast","Synopsis");

        String rating;
        if(movie.getAvgRating() == -1)
            rating="NA";
        else
            rating=String.format("%.3g",movie.getAvgRating());



        System.out.format("%-10s   %-20s   %-7s   %-10s  %-15s   %-15s  %-80s  %-30s  %n",movie.getId(),movie.getName(),rating,movie.getLanguage(),movie.getStatus(),movie.getDirector(),movie.getCast(),movie.getSynopsis());
        System.out.println();
    }


    public static void Display(List<Object> list){
        int count=1;
        if(list.get(0)!=null){
            if(list.get(0) instanceof Cinema){
                System.out.println("index"+"\t"+"CinePlex ID" + "\t" + "Cinema ID" + "\t" + "Time" + "\t"  +"\t"+"Class"+"\t"+"Type");
            }

        }
        for(Object o:list){
            System.out.print(count+" ");
            count++;
            System.out.println(o.toString());
        }
    }
    public static void Display(Object o){
        System.out.println(o.toString());
    }

    public static void DisplayBookings(List<Booking> bookings){
        int count=0;
        System.out.format("%-20s   %-20s   %-10s   %-15s  %-15s   %-10s %-4s   %-20s   %-20s  %-6s  %-15s  %-20s  %-20s  %n","Customer Name","Customer Type","Booking ID",
                            "Cinplex ID","Cinema ID ","Movie ID","Seat","Showtime","Booking Time","Price","Cinema Class","Movie Type","Email");

        for(Booking booking:bookings)
            System.out.format("%-20s   %-20s   %-10s   %-15d   %-15d   %-10d %-4d   %-20s   %-20s  %-6.2f  %-15s  %-20s  %-20s  %n",booking.getCustomerName(),
                    booking.getCustomerType(),booking.getBookingID(),booking.getCinplexID(),booking.getCinemaID(),booking.getMovieID(),booking.getSeatNO(),
                    booking.getShowTime(),booking.getBookingTime(),booking.getPrice(),booking.getCinemaClass(),booking.getMovieType(),booking.getEmail());
        System.out.println();
    }



    public static void DisplayMovieReviews(Movie movie){
        int reviewNum = movie.getRating().size() < movie.getReviews().size() ? movie.getRating().size() : movie.getReviews().size();
        List<Integer> rating_list = movie.getRating();
        List<String> review_list = movie.getReviews();

        System.out.format("%-7s %-20s %n","Rating","Review");

        for(int i = 0; i < reviewNum; i++)
            System.out.format("%-1s/5    %-20s  %n",rating_list.get(i),review_list.get(i));
        System.out.println();

    }

    public static void DisplayCinemas(List<Cinema> cinemas){
        int count=0;

        System.out.format("%-6s %-12s %-10s %-20s  %-9s %-5s %n", "index", "CinePlex ID", "Cinema ID", "Time", "Class", "Type");

        for(Cinema cinema:cinemas){
            System.out.format("%-6d %-12d %-10d %-20s %-9s %-5s %n", count++, cinema.getCinplexID(), cinema.getCinemaID(), cinema.getTime(),cinema.getCinemaClass(), cinema.getMovieType());
        }
        System.out.println();
    }

    public static void DisplaySeating(Cinema cinema) {
        int value;
        int rows=10;
        int cols=10;
        List<Integer> seating_temp = cinema.getSeats();

        System.out.print("   ");
        for(int i=0;i<cols;i++)
            System.out.print(i+"  ");
        System.out.println();

        for(int i=0;i<rows;i++){
            System.out.print(i+" ");
            for (int j=0;j<cols;j++){
                value=i*cols + j;

                int k;
                for(k=0; k < seating_temp.size(); k++){
                    if(seating_temp.get(k)==value){
                        break;
                    }
                }

                if(k == seating_temp.size()){
                    System.out.print(" A ");
                }else{
                    System.out.print(" X ");
                    seating_temp.remove(k);
                }
            }
            System.out.println();
        }
        System.out.println("=========MOVIE SCREEN==========");
    }

    public static void DisplaySettings(){

        System.out.println("\nCurrent Settings");
        System.out.format("%-13s  %-13s  %-13s  %-13s  %-13s  %-14s  %-14s  %-13s  %-13s %n", "Base Price", "Child Price", "Senior Price", "Holiday Price","Silver Price","Gold Price", "Platinum Price","3D Price","Holidays");

        Settings settings = DataManager.LoadSettings();

        System.out.format("%-13s  %-13s  %-13s  %-13s  %-13s  %-14s  %-14s  %-13s   %-13s %n",settings.getBasePrice(),settings.getChildPrice(),settings.getSeniorPrice(),settings.getHolidayPrice(),settings.getSilverPrice(),settings.getGoldPrice(),settings.getPlatinumPrice(),settings.getPrice3D(),settings.getHolidays());

        System.out.println();

    }



}

