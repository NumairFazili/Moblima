package Controller;

import java.util.*;

import Entity.*;
import View.Boundary;

import javax.xml.crypto.Data;

/*
    createBookings
    getBookings
*/

public class UserManager extends PersonManager{

    User user;

    //for existing user
    public UserManager(User u){
        user = u;
        if(user == null){
            throw new IllegalArgumentException("UserManager must have a user");
        }
    }

    //for new user
    public UserManager(String name, int age, String mobileNumber, String email){
        User u = new User(name, age, mobileNumber, email, Arrays.asList());
        u.save();
        user = u;
    }


    public void createBooking(Cinema cinema, int seatNO){
        Movie m = super.selectMovieByID(cinema.getMovieID());
        BookingManager.init();

        if(user.getAge() >= m.getMinAge()){
            Booking b = BookingManager.createBooking(user, cinema, seatNO);
            user.addBooking(b);
            user.save();    
        }else{
            System.out.println("Minimum age requirement not reached.");
        }
    }

    public List<Booking> getBookings(){
        List<String> b_list = user.getBookings();
        ArrayList<Booking> all_b = DataManager.LoadBookings();
        ArrayList<Booking> bookings = new ArrayList<Booking>();
        for(int i = 0; i < b_list.size(); i++){
            for(int j = 0; j < all_b.size(); j++){
                if (all_b.get(j).getBookingID().equals(b_list.get(i))){
                    bookings.add(all_b.get(j));
                    break;
                }
            }
        }
        if(bookings.size() != b_list.size()){
            System.out.println("some bookings not found");
        }
        return bookings;
    }

    public void reviewMovie(Movie m, int rating, String review){
        m.addRating(rating);
        m.addReview(review);
    }

    public static void main(String args[]){
        UserManager um = new UserManager("James", 18, "jammy@gmail.com", "13572468");
        System.out.println(um.user.getBookings());
    }

    public static UserManager createUser(Scanner input){
        //2. New User
        //Get user input to create new user object
        System.out.println("Creating New Account:");
        System.out.println("Enter Username:");
        String username = input.nextLine();
        System.out.println("Enter Age:");
        int age = input.nextInt();
        input.nextLine(); // Catch newline from nextInt
        System.out.println("Enter Mobile number:");
        String mobilenumber = input.nextLine();
        System.out.println("Enter Email Address:");
        String email = input.nextLine();
        //Create usermanager object and Save user into database
        UserManager myuser = new UserManager(username, age, mobilenumber, email);
        return myuser;
    }

    public static UserManager getGuestUser(){
        UserManager myuser = AuthManager.getUser("guestaccount", "10101010");
        return myuser;
    }

    public void SearchListMovie(Scanner input, int choice){
        ArrayList<Movie> mymovielist = null;
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
        Boundary.DisplayMovies(mymovielist);
        //Select movie by movie ID and print movie details – including reviews and ratings
        System.out.println("Enter ID of the movie to see the details: " );
        int inputsearchint = input.nextInt();
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
            Boundary.DisplayCinemas(this.getShowTimesByMovie(mymovie.getId()));
            //3. Check seat availability and selection of seat/s.
            boolean looper = Boolean.TRUE;
            while (looper){
                System.out.println("Choose index of the showtime to view seat availability: ");
                inputsearchint = input.nextInt();
                input.nextLine(); //Catch newline from .nextInt()
                Boundary.DisplaySeating(((DataManager.LoadShowTimes(mymovie.getId()).get(inputsearchint))).getSeats());
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
                    this.createBooking(mycinema, rowofseat*8 + colofseat);
                }
                //2. Select another showtime
                else if (choice == 2){
                    Boundary.DisplayCinemas(this.getShowTimesByMovie(mymovie.getId()));
                    //Boundary.DisplayCinemas(DataManager.LoadShowTimes(mymovie.getId()));
                }
            }
        }
        else if (choice == 2){
            System.out.println("Enter rating for the movie:" );
            int movierating = input.nextInt();
            System.out.println("Enter review for the movie:" );
            String moviereview = input.nextLine();
            this.reviewMovie(mymovie, movierating, moviereview);
        }
    }
    
}