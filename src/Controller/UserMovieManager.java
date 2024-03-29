package Controller;

import Entity.Cinema;
import Entity.Movie;
import Entity.User;
import View.Boundary;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;
/**
 Represents the user movie manager to manage movie objects for the user
 @author CZ2002 Group 1
 @version 1.0
 @since 15-11-2019
 */
public class UserMovieManager extends MovieManager {




    private  User user;



    public UserMovieManager(User user){
        this.user=user;
    }


    /**
     * Method to Search or List movie depending on user input, display movie details of chosen movie, display all showtimes for chosen movie, display seats for chosen showtime, allow user to choose seat for showtime and confirmation to book chosen movie.

     * @param choice choice parameter to determine whether to search or list movie
     */
    public  void searchListMovie(int choice) {
        Scanner input = new Scanner(System.in);


        ArrayList<Movie> mymovielist = new ArrayList<Movie>();
        //List all movies
        if (choice == 5) {
            mymovielist = DataManager.loadMovies("");
        }
        //Search and display movies
        if (choice == 1) {
            System.out.println("Enter name of movie to search: ");
            String inputsearch = input.next();
            mymovielist = DataManager.loadMovies(inputsearch);
            if (mymovielist.isEmpty()) {
                System.out.println("No movie found!");
                return;
            }
        }
        Boundary.displayMovie(mymovielist);
        //Select movie by movie ID and print movie details – including reviews and ratings
        System.out.println("Enter ID of the movie to see the details: ");
        System.out.println("Otherwise enter 0 to go back");
        int inputsearchint = input.nextInt();
        if (inputsearchint == 0) {
            return;
        }
        Movie movie = selectMovieByID(mymovielist, inputsearchint);
        if(movie==null){
            System.out.println("Invalid Movie ID");
            return;
        }


        System.out.println("Movie Details: ");
        Boundary.displayMovie(movie);
        System.out.println("All ratings and reviews: ");
        Boundary.displayMovieReviews(movie);


         this.BookMovie(movie,choice);
    }


    public void BookMovie(Movie movie,int choice){
        //Select movie, then search for all showtimes.
        Scanner input = new Scanner(System.in);
        do {
            try{
                Scanner in = new Scanner(System.in);
                System.out.println("1. View all showtimes for this movie: ");
                System.out.println("2. Leave a rating and review for this movie: ");
                System.out.println("0. Go back");
                choice = in.nextInt();
                if (choice <= -1 || choice >= 3){
                    System.out.println("Error! Please enter either 0, 1, 2:");
                }
            }
            catch(InputMismatchException e){
                System.out.println("That is not an integer, please try again." );
            }
        } while (choice <= -1 || choice >= 3);
        if (choice == 0){
            return;
        }
        //Select the showtime by index
        else if (choice == 1){

            boolean looper=Boolean.TRUE;
            if(movie.getStatus().equals("End of Show")){
                System.out.println("The movie has Ended\n");
                looper=Boolean.FALSE;
            }

            if(ShowTimeManager.getShowTimesByMovie(movie.getId()).size()==0){
                System.out.println("No ShowTimes Available\n");
                looper=Boolean.FALSE;
            }
            
            while (looper){
                Boundary.displayCinemas(ShowTimeManager.getShowTimesByMovie(movie.getId()));
                System.out.println("Choose index of the showtime to view seat availability: ");
                int inputsearchint = input.nextInt();
                input.nextLine(); //Catch newline from .nextInt()

                String time=((DataManager.loadShowTimes(movie.getId()).get(inputsearchint))).getTime();

                if(!BookingManager.dateCheck(time)){
                    System.out.println("The Movie has Already Passed");
                    break;
                }
                Boundary.displaySeating(((DataManager.loadShowTimes(movie.getId()).get(inputsearchint))));
                choice = -1;
               {
                    try{
                        Scanner in = new Scanner(System.in);
                        System.out.println("1. Select seats");
                        System.out.println("2. Select another showtime");
                        System.out.println("0. Cancel selection and return to main menu");
                        choice = in.nextInt();
                        if (choice <= -1 || choice >= 3){
                            System.out.println("Error! Please enter either 0, 1 or 2:");
                        }
                    }
                    catch(InputMismatchException e){
                        System.out.println("That is not an integer, please try again.");
                    }
                }
                //0. Cancel selection and return to main menu
                if (choice == 0){
                    break;
                }
                //1. Select seats
                else if (choice == 1){
                    looper = Boolean.FALSE; //stop while loop
                    while(true){
                        System.out.println("Enter row index of seat:" );

                        int rowofseat = input.nextInt();
                        System.out.println("Enter col index of seat:" );
                        int colofseat = input.nextInt();
                        //4. Book and purchase ticket
                        Cinema mycinema = ((DataManager.loadShowTimes(movie.getId()).get(inputsearchint)));
                        BookingManager bookingManager=new BookingManager(user,mycinema);
                        if(!bookingManager.createBooking(rowofseat*10 + colofseat)){
                            System.out.println("Booking Failed");
                            System.out.println("Enter 1 to retry or  0 to go back");
                            choice = input.nextInt();
                        }
                        else{
                            break;
                        }

                        if (choice == 0){
                            break;
                        }
                        if (choice == 1){
                            Boundary.displaySeating(((DataManager.loadShowTimes(movie.getId()).get(inputsearchint))));
                        }
                    }
                }
                //2. Select another showtime
                else if (choice == 2){
                    Boundary.displayCinemas(ShowTimeManager.getShowTimesByMovie(movie.getId()));
                }
            }
        }
        else if (choice == 2){
            System.out.println("Enter rating for the movie:" );
            int movieRating = input.nextInt();
            System.out.println("Enter review for the movie:" );
            input.nextLine();
            String movieReview = input.nextLine();

            if(reviewMovie(movie, movieRating, movieReview))
                System.out.println("Review added successfully");
            else
                System.out.println("Failed adding review");
        }
    }

    /**
     * Method to add rating and review for movie object
     * @param movie Movie object
     * @param rating rating to be added to movie object
     * @param review review to be added to movie object
     * @return True if review rating and review is successfully added to movie, and False otherwise
     */
    public static Boolean reviewMovie(Movie movie, int rating, String review){
        movie.addRating(rating);
        movie.addReview(review);
        return DataManager.manageMovie(movie);
    }
}
