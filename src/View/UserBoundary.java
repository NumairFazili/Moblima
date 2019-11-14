package View;

import Entity.*;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

import Controller.*;

public class UserBoundary{
//
//    public static void SearchListMovie(Scanner input, int choice, User u){
//        ArrayList<Movie> mymovielist = null;
//        //List all movies
//        if (choice == 5){
//            mymovielist = DataManager.LoadMovies("");
//        }
//        //Search and display movies
//        if (choice == 1){
//            System.out.println("Enter name of movie to search: " );
//            String inputsearch = input.next();
//            mymovielist = DataManager.LoadMovies(inputsearch);
//            if (mymovielist.isEmpty()){
//                System.out.println("No movie found!" );
//                return;
//            }
//        }
//        Boundary.DisplayMovie(mymovielist);
//        //Select movie by movie ID and print movie details – including reviews and ratings
//        System.out.println("Enter ID of the movie to see the details: " );
//        int inputsearchint = input.nextInt();
//        Movie mymovie = SearchManager.find_Movie_byID(mymovielist, inputsearchint);
//        System.out.println("Movie Details: " );
//        Boundary.DisplayMovie(mymovie);
//        System.out.println("All ratings and reviews: " );
//        Boundary.DisplayMovieReviews(mymovie);
//        //Select movie, then search for all showtimes.
//        choice = -1;
//        while (choice <= -1 || choice >= 3){
//            try{
//                Scanner in = new Scanner(System.in);
//                System.out.println("1. View all showtimes for this movie: ");
//                System.out.println("2. Leave a rating and review for this movie: ");
//                System.out.println("0. Go back");
//                choice = in.nextInt();
//                if (choice <= -1 || choice >= 3){
//                    System.out.println("Error! Please enter either 0, 1, 2:");
//                }
//            }
//            catch(InputMismatchException e){
//                System.out.println("That is not an integer, please try again." );
//            }
//        }
//        if (choice == 0){
//            return;
//        }
//        //Select the showtime by index
//        else if (choice == 1){
//            Boundary.DisplayCinemas(u.getShowTimesByMovie(mymovie.getId()));
//            //3. Check seat availability and selection of seat/s.
//            boolean looper = Boolean.TRUE;
//            while (looper){
//                System.out.println("Choose index of the showtime to view seat availability: ");
//                inputsearchint = input.nextInt();
//                input.nextLine(); //Catch newline from .nextInt()
//                Boundary.DisplaySeating(((DataManager.LoadShowTimes(mymovie.getId()).get(inputsearchint))));
//                choice = -1;
//                while (choice <= -1 || choice >= 3){
//                    try{
//                        Scanner in = new Scanner(System.in);
//                        System.out.println("1. Select seats");
//                        System.out.println("2. Select another showtime");
//                        System.out.println("0. Cancel selection and return to main menu");
//                        choice = in.nextInt();
//                        if (choice <= -1 || choice >= 3){
//                            System.out.println("Error! Please enter either 0, 1 or 2:");
//                        }
//                    }
//                    catch(InputMismatchException e){
//                        System.out.println("That is not an integer, please try again." );
//                    }
//                }
//                //0. Cancel selection and return to main menu
//                if (choice == 0){
//                    break;
//                }
//                //1. Select seats
//                else if (choice == 1){
//                    looper = Boolean.FALSE; //stop while loop
//                    System.out.println("Enter row index of seat:" );
//                    int rowofseat = input.nextInt();
//                    System.out.println("Enter col index of seat:" );
//                    int colofseat = input.nextInt();
//                    input.nextLine();//Catch newline from input
//                    //4. Book and purchase ticket
//                    Cinema mycinema = ((DataManager.LoadShowTimes(mymovie.getId()).get(inputsearchint)));
//                    u.createBooking(mycinema, rowofseat*8 + colofseat);
//                }
//                //2. Select another showtime
//                else if (choice == 2){
//                    Boundary.DisplayCinemas(u.getShowTimesByMovie(mymovie.getId()));
//                    //Boundary.DisplayCinemas(DataManager.LoadShowTimes(mymovie.getId()));
//                }
//            }
//        }
//        else if (choice == 2){
//            System.out.println("Enter rating for the movie:" );
//            int movierating = input.nextInt();
//            System.out.println("Enter review for the movie:" );
//            String moviereview = input.nextLine();
//            u.reviewMovie(mymovie, movierating, moviereview);
//        }
//    }
//
//

}