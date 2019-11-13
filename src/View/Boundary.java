package View;

import Entity.Cinema;
import Entity.Movie;
//import sun.tools.tree.IntegerExpression;

import java.lang.reflect.Array;
import java.util.*;

public class Boundary {
    public static int ModuleSelection(int choice, Scanner input){

        while(choice != 1 && choice !=2 && choice !=0){
            Boundary.DisplayBegin();

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

    public static int Display_User_Choice(){
        System.out.println("MOVIE-GOER USER");
        int choice = 0;
        while (choice <= 0 || choice >= 4){
            try{
                Scanner in = new Scanner(System.in);
                System.out.println("1. Existing User");
                System.out.println("2. New User");
                System.out.println("3. Proceed as Guest User");
                choice = in.nextInt();
                if (choice <= 0 || choice >= 4){
                    System.out.println("Error! Please enter either 1, 2 or 3:");
                }
            }
            catch(InputMismatchException e){
                System.out.println("That is not an integer, please try again." );
            }
        }
        return choice;
    }

    public static void Display_User_main(){
        System.out.println("1. Search movie");
        System.out.println("2. View booking history");
        System.out.println("3. List the Top 5 ranking by ticket sales");
        System.out.println("4. List the Top 5 ranking by overall reviewers’ ratings");
        System.out.println("5. List all movies");
        System.out.println("0. Exit the Program");
    }

    public static void Display_Staff_main(){
        System.out.println("1. Create movie listing");
        System.out.println("2. Update movie listing");
        System.out.println("3. Remove movie listing");
        System.out.println("4. Create cinema showtimes and the movies to be shown");
        System.out.println("5. Update cinema showtimes and the movies to be shown");
        System.out.println("6. Remove cinema showtimes and the movies to be shown");
        System.out.println("7. Configure system settings");
        System.out.println("0. Exit the Program");
    }
    public static void DisplayBegin(){
        System.out.println("MOBLIMA Movie Booking System START:");
        System.out.println("1. Admin user: ");
        System.out.println("2. Movie-Goer: ");
        System.out.println("0. to exit:");
    }
    public static void DisplayOptions(String[] str) {
        int count = 0;
        for (int i = 0; i < str.length; i++)
            System.out.println(" " + String.valueOf(count++) + " : " + str[i]);
        System.out.println(-1 + " : " + "Exit");
    }


    public static void DisplayMovies(ArrayList<Movie> movieList){
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

    public static void DisplaySeating(List<Integer> i_list) {
        int value;
        int rows=10;
        int cols=10;
//        List<Integer> seating_temp = cinema.getSeats();
        List<Integer> seating_temp = i_list;

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
    }

    public static void main(String args[]){
        List<Integer> i_list = new ArrayList<Integer>();
        i_list.add(14);
        i_list.add(3);
        i_list.add(17);
        DisplaySeating(i_list);
    }    

}

