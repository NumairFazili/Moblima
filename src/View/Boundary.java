package View;

import Controller.DataManager;
import Entity.*;

import java.util.*;

public class Boundary {


    private static final String[] staffOptions={"Create movie listing","Update movie listing","Delete movie listing","Create cinema showtimes and the movies to be shown","Update cinema showtimes and the movies to be shown","Remove cinema showtimes and the movies to be shown","Configure system settings"};
    private static final String[] startOptions ={"Admin user","Movie-Goer"};
    private static final String[] userMovieOptions={"Search movie", "View booking history","List the Top 5 ranking by ticket sales","List the Top 5 ranking by overall reviewers’ ratings","List all movies"};
    private static final String[] userOptions={"Existing User","New User","Guest User"};


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
        }
        int count = 1;
        for (int i = 0; i < str.length; i++)
            System.out.println(" " + count++ + " : " + str[i]);
        System.out.println(" "+0+" : "+"Exit");
    }



    public static void DisplayMovie(List<Movie> movieList){
        //System.out.println("ID" + "\t" + "Title" + " \t" + "Rating(Avg)" + "\t" + "Language");
        System.out.format("%-5s %-25s %-15s %-15s %n", "ID", "Title", "Rating(Avg)", "Language");
        for(Movie movie:movieList){
            //System.out.println(movie.getId() + "\t" + movie.getName() + "\t" + movie.getAvgRating() + "\t" +movie.getLanguage());
            System.out.format("%-5d %-25s %-15.2f %-15s %n", movie.getId(), movie.getName(), movie.getAvgRating(), movie.getLanguage());
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
    public static void Display(List<Object> list){
        int count=1;
        if(list.get(0)!=null){
            if(list.get(0) instanceof Cinema){
                System.out.println("index"+"\t"+"CinePlex ID" + "\t" + "Cinema ID" + "\t" + "Time" + "\t" + "Status" +"\t"+"Class"+"\t"+"Type");
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
        System.out.format("%-20s   %-20s   %-10s   %-15s  %-15s   %-10s %-4s   %-20s   %-20s   %-15s  %-20s  %-20s  %n","Customer Name","Customer Type","Booking ID",
                            "Cinplex ID","Cinema ID ","Movie ID","Seat","Showtime","Booking Time","Cinema Class","Movie Type","Email");

        for(Booking booking:bookings)
            System.out.format("%-20s   %-20s   %-10s   %-15d   %-15d   %-10d %-4d   %-20s   %-20s   %-15s  %-20s  %-20s  %n",booking.getCustomerName(),
                    booking.getCustomerType(),booking.getBookingID(),booking.getCinplexID(),booking.getCinemaID(),booking.getMovieID(),booking.getSeatNO(),
                    booking.getShowTime(),booking.getBookingTime(),booking.getCinemaClass(),booking.getMovieType(),booking.getEmail());
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

        System.out.format("%-6s %-12s %-10s %-20s %-13s %-9s %-5s %n", "index", "CinePlex ID", "Cinema ID", "Time", "Status", "Class", "Type");
        //System.out.println("index"+"\t"+"CinePlex ID" + "\t" + "Cinema ID" + "\t" + "Time" + "\t" + "Status" +"\t"+"Class"+"\t"+"Type");

        for(Cinema cinema:cinemas){
            //System.out.println(count++ +"\t"+cinema.getCinplexID() + "\t" + cinema.getCinemaID() + "\t" + cinema.getTime() + "\t" +cinema.getStatus() + "\t" +cinema.getCinemaClass() + "\t" + cinema.getMovieType());
            System.out.format("%-6d %-12d %-10d %-20s %-13s %-9s %-5s %n", count++, cinema.getCinplexID(), cinema.getCinemaID(), cinema.getTime(), cinema.getStatus(), cinema.getCinemaClass(), cinema.getMovieType());
        }
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
        Settings mysettings = DataManager.LoadSettings();
        System.out.println("Current Settings");
        System.out.println("BasePrice:"+ mysettings.getBasePrice());
        System.out.println("ChildPrice:"+ mysettings.getChildPrice());
        System.out.println("Senior:"+ mysettings.getSeniorPrice());
        System.out.println("HolidayPrice:" +mysettings.getHolidays());
        System.out.println("SilverPrice:" +mysettings.getSilverPrice());
        System.out.println("GoldPrice:" +mysettings.getGoldPrice());
        System.out.println("PlatinumPrice:" +mysettings.getPlatinumPrice());
        System.out.println("Holidays:" +mysettings.getHolidays());
        System.out.println();

    }

}

