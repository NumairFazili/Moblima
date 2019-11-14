package Controller;

import Entity.Cinema;
import Entity.Movie;
import Entity.User;
import View.Boundary;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class MovieManager {


    ShowTimeManager showTimeManager;


    public MovieManager(){
        showTimeManager=new ShowTimeManager();
    }


    public Movie selectMovieByID(int id){
        ArrayList<Movie> m_list = DataManager.LoadMovies("");
        for (int i = 0; i < m_list.size(); i++){
            if (m_list.get(i).getId() == id){
                return m_list.get(i);
            }
        }
        return null;
    }

    public Movie selectMovieByID(ArrayList<Movie> m_list, int id){
        for (int i = 0; i < m_list.size(); i++){
            if (m_list.get(i).getId() == id){
                return m_list.get(i);
            }
        }
        return null;
    }


    public ArrayList<Movie> getAllMovies(){
        ArrayList<Movie> m_list = DataManager.LoadMovies("");
        return m_list;
    }

    public ArrayList<Movie> getMovieByName(String s){
        return DataManager.LoadMovies(s);
    }


    public void reviewMovie(Movie movie, int rating, String review){
        movie.addRating(rating);
        movie.addReview(review);
    }

    public void SearchListMovie(User user, Scanner input, int choice){
        ArrayList<Movie> mymovielist = new ArrayList<Movie>();
        //List all movies
        if (choice == 5){
            mymovielist = DataManager.LoadMovies("");
        }
        //Search and display movies
        if (choice == 1){
            System.out.println("Enter name of movie to search: " );
            String inputsearch = input.next();
            mymovielist = DataManager.LoadMovies(inputsearch);
            if (mymovielist.isEmpty()){
                System.out.println("No movie found!" );
                return;
            }
        }
        Boundary.DisplayMovie(mymovielist);
        //Select movie by movie ID and print movie details – including reviews and ratings
        System.out.println("Enter ID of the movie to see the details: " );
        System.out.println("Otherwise enter 0 to go back" );
        int inputsearchint = input.nextInt();
        if (inputsearchint == 0){
            return;
        }
        Movie mymovie = SearchManager.find_Movie_byID(mymovielist, inputsearchint);
        System.out.println("Movie Details: " );
        Boundary.DisplayMovie(mymovie);
        System.out.println("All ratings and reviews: " );
        Boundary.DisplayMovieReviews(mymovie);
        //Select movie, then search for all showtimes.
        choice = -1;
        while (choice <= -1 || choice >= 3){
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
        }
        if (choice == 0){
            return;
        }
        //Select the showtime by index
        else if (choice == 1){
            Boundary.DisplayCinemas(showTimeManager.getShowTimesByMovie(mymovie.getId()));
            //3. Check seat availability and selection of seat/s.
            boolean looper = Boolean.TRUE;
            while (looper){
                System.out.println("Choose index of the showtime to view seat availability: ");
                inputsearchint = input.nextInt();
                input.nextLine(); //Catch newline from .nextInt()
                Boundary.DisplaySeating(((DataManager.LoadShowTimes(mymovie.getId()).get(inputsearchint))));
                choice = -1;
                while (choice <= -1 || choice >= 3){
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
                        System.out.println("That is not an integer, please try again." );
                    }
                }
                //0. Cancel selection and return to main menu
                if (choice == 0){
                    break;
                }
                //1. Select seats
                else if (choice == 1){
                    looper = Boolean.FALSE; //stop while loop
                    System.out.println("Enter row index of seat:" );
                    int rowofseat = input.nextInt();
                    System.out.println("Enter col index of seat:" );
                    int colofseat = input.nextInt();
                    input.nextLine();//Catch newline from input
                    //4. Book and purchase ticket
                    Cinema mycinema = ((DataManager.LoadShowTimes(mymovie.getId()).get(inputsearchint)));

                    BookingManager bookingManager=new BookingManager();
                    if(!bookingManager.createBooking(user,mycinema, rowofseat*10 + colofseat)){
                        System.out.println("Booking Failed");
                        continue;
                    }


                }
                //2. Select another showtime
                else if (choice == 2){
                    Boundary.DisplayCinemas(showTimeManager.getShowTimesByMovie(mymovie.getId()));
                }
            }
        }
        else if (choice == 2){
            System.out.println("Enter rating for the movie:" );
            int movieRating = input.nextInt();
            System.out.println("Enter review for the movie:" );
            String movieReview = input.next();
            this.reviewMovie(mymovie, movieRating, movieReview);
        }
    }


    public ArrayList<Movie> getTopByRatings(){
        return SearchManager.get_topN_byRating(this.getAllMovies());
    }
    public ArrayList<Movie> getTopBySales(){
        SearchManager.calculateSales(DataManager.LoadBookings());
        return SearchManager.get_topN_bySale(this.getAllMovies(), DataManager.LoadBookings());
    }










}
