package Controller;
import java.security.KeyStore;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import Entity.*;
import View.Boundary;

import javax.xml.crypto.Data;

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        //Variables for admin to create movie
        int movieid, minage;
        double rating;
        String moviename, language, runtime, description, director;
        List<String> cast;
        String username, password, inputsearch, mobilenumber, email;
        int choice = -1;
        int adminauth, age, inputsearchint, i, rowofseat, colofseat;
        boolean looper;
        Cinema mycinema;
        ArrayList<Movie> mymovielist = new ArrayList<>();
        Movie mymovie;
        UserManager myuser = null;
        StaffManager mystaff = null;
        System.out.println("MOBLIMA Movie Booking System START:");
        while (choice != -2){
            while (choice != 1 && choice != 2 && choice != 0){
                try{
                    Scanner in = new Scanner(System.in);
                    System.out.println("Please enter 1 for Admin, 2 for Movie-Goer and 0 to exit:");
                    choice = in.nextInt();
                    if (choice == 0){
                        System.exit(0);
                    }
                    if (choice != 1 && choice != 2 && choice != 0){
                        System.out.println("Error! Please enter either 1, 2 or 0:");
                    }
                }
                catch(InputMismatchException e){
                    System.out.println("That is not an integer, please try again." );
                }
            }

            //Admin choice
            if (choice == 1){
                adminauth = 0;
                while (adminauth == 0){
                    System.out.println("ADMIN USER:");
                    System.out.println("Please enter login details, Input -2 to go back:");
                    System.out.println("Enter Username:");
                    username = input.next();
                    if (username.equals("-2")){ //If input is -2 go back to previous menu
                        choice = -1;
                        break;
                    }
                    System.out.println("Enter Password:");
                    password = input.next();
                    //Check with database to if username and password is correct
                    mystaff = AuthManager.getStaff(username, password);
                    if (mystaff != null){
                        System.out.println("Admin account successfully logged in!");
                        adminauth = 1;
                    }
                    if (adminauth == 0){
                        System.out.println("The username or password you typed is incorrect. Please try again.");
                    }
                }

                //If admin username and password is authenticated
                if (adminauth == 1){
                    while (choice != 0){
                        choice = -1;
                        while (choice <= -1 || choice >= 8){
                            try{
                                Scanner in = new Scanner(System.in);
                                System.out.println("1. Create movie listing");
                                System.out.println("2. Update movie listing");
                                System.out.println("3. Remove movie listing");
                                System.out.println("4. Create cinema showtimes and the movies to be shown");
                                System.out.println("5. Update cinema showtimes and the movies to be shown");
                                System.out.println("6. Remove cinema showtimes and the movies to be shown");
                                System.out.println("7. Configure system settings");
                                System.out.println("0. Exit the Program");
                                choice = in.nextInt();
                                if (choice <= -1 || choice >= 8){
                                    System.out.println("Error! Please enter either 0, 1, 2, 3, 4, 5, 6 or 7:");
                                }
                            }
                            catch(InputMismatchException e){
                                System.out.println("That is not an integer, please try again." );
                            }
                        }
                        //0. Exit the program
                        if (choice == 0){
                            System.exit(0);
                        }
                        //1. Create movie listing
                        if (choice == 1){
                            //Get user input for movie listing details
                            System.out.println("Enter movieID:");
                            movieid = input.nextInt();
                            input.nextLine(); //Catch newline from .nextInt()
                            System.out.println("Enter movie name:");
                            moviename = input.nextLine();
                            System.out.println("Enter language:");
                            language = input.nextLine();
                            System.out.println("Enter runtime:");
                            runtime = input.nextLine();
                            System.out.println("Enter cast members");
                            cast = Arrays.asList(input.nextLine().split(","));
                            System.out.println("Enter description:");
                            description = input.nextLine();
                            System.out.println("Enter director:");
                            director = input.nextLine();
                            System.out.println("Enter Minimum age:");
                            minage = input.nextInt();
                            input.nextLine(); //Catch newline from .nextInt()
                            //Create movie object and save movie listing to database using DataManager
                            mystaff.createNewMovie(movieid, moviename, language, null, runtime, cast, description, director, null, minage);
                        }

                        //2. Update movie listing
                        else if (choice == 2){
                            //List all movies
                            Boundary.DisplayMovies(mystaff.getAllMovies());
                            //Boundary.DisplayMovies(DataManager.LoadMovies(""));
                            //Select movie to update by movieID
                            System.out.println("Enter ID of the movie to be updated: " );
                            inputsearchint = input.nextInt();
                            input.nextLine();//Catch newline from input.nextInt()
                            mymovie = SearchManager.find_Movie_byID(mystaff.getAllMovies(), inputsearchint);

                            //Choose which attribute of the movie to be edited
                            System.out.println("Choose attribute of movie to be edited: " );
                            choice = -1;
                            while (choice != 0){
                                choice = -1;
                                while (choice <= -1 || choice >= 8){
                                    try{
                                        Scanner in = new Scanner(System.in);
                                        System.out.println("1. movie name");
                                        System.out.println("2. language");
                                        System.out.println("3. rating");
                                        System.out.println("4. runtime");
                                        System.out.println("5. Cast member");
                                        System.out.println("6. description");
                                        System.out.println("7. director");
                                        System.out.println("0. Done");
                                        choice = in.nextInt();
                                        if (choice <= -1 || choice >= 8){
                                            System.out.println("Error! Please enter either 0, 1, 2, 3, 4, 5, 6 or 7:");
                                        }
                                    }
                                    catch(InputMismatchException e){
                                        System.out.println("That is not an integer, please try again." );
                                    }
                                }
                                //0. Done, save new movie object to database
                                if (choice == 0){
                                    choice = -1;
                                    break;
                                }
                                //1. Prompt input for movie name and edit movie object
                                else if (choice == 1){
                                    System.out.println("Enter new movie name:");
                                    mystaff.updateMovieName(mymovie, input.nextLine());
                                }
                                //2. Prompt input for language and edit movie object
                                else if (choice == 2){
                                    System.out.println("Enter new language:");
                                    mystaff.updateMovieLanguage(mymovie, input.nextLine());
                                }
                                //3. Prompt input for rating and edit movie object
                                else if (choice == 3){// Currently not in use
                                    System.out.println("Enter new rating:");
                                    //mymovie.setRating(Arrays.asList(input.nextInt()));
                                    //input.nextLine();//Catch newline from input.nextDouble()
                                }
                                //4. Prompt input for runtime and edit movie object
                                else if (choice == 4){
                                    System.out.println("Enter new runtime:");
                                    mystaff.updateMovieRunTime(mymovie, input.nextLine());
                                }
                                //5. Prompt input for Cast member and edit movie object
                                else if (choice == 5){
                                    System.out.println("Enter new cast members:");
                                    mystaff.updateMovieCast(mymovie, Arrays.asList(input.nextLine().split(",")));
                                }
                                //6. Prompt input for description and edit movie object
                                else if (choice == 6){
                                    System.out.println("Enter new description:");
                                    mystaff.updateMovieDescription(mymovie, input.nextLine());
                                }
                                //7. Prompt input for director and edit movie object
                                else if (choice == 7){
                                    System.out.println("Enter new director:");
                                    mystaff.updateMovieDirector(mymovie, input.nextLine());
                                }

                            }
                            //Save edited movie object to database
                            if (mystaff.saveMovieChanges(mymovie) == Boolean.TRUE){
                                System.out.println("Movie listing successfully updated!");
                            }
                            else{
                                System.out.println("Error movie listing failed to be updated!");
                            }
                        }
                        //3. Remove movie listing
                        else if (choice == 3){
                            //List all movies
                            Boundary.DisplayMovies(mystaff.getAllMovies());
                            //Select movie to remove by movieID
                            System.out.println("Enter ID of the movie to remove: " );
                            inputsearchint = input.nextInt();
                            mymovie = SearchManager.find_Movie_byID(mystaff.getAllMovies(), inputsearchint);
                            if (mystaff.deleteMovie(mymovie) == Boolean.TRUE){
                                System.out.println("Movie listing successfully removed!");
                            }
                            else{
                                System.out.println("Error movie listing failed to be removed!");
                            }

                        }
                        //4. Create cinema showtimes and the movies to be shown
                        else if (choice == 4){

                        }
                        //5. Update cinema showtimes and the movies to be shown
                        else if (choice == 5){

                        }
                        //6. Remove cinema showtimes and the movies to be shown
                        else if (choice == 6){

                        }
                        //7. Configure system settings
                        else if (choice == 7){

                        }
                    }
                }

            }


            //Movie-Goer choice
            else {
                System.out.println("MOVIE-GOER USER");
                choice = 0;
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

                //1. Existing User
                if (choice == 1){
                    looper = Boolean.TRUE;
                    while (looper){
                        System.out.println("Please enter login details:");
                        System.out.println("Enter Username:");
                        username = input.nextLine();
                        System.out.println("Enter Mobile number:");
                        mobilenumber = input.nextLine();
                        //Check with database if name and mobile number matches then create corresponding user object
                        myuser = AuthManager.getUser(username, mobilenumber);
                        if (myuser == null){
                            System.out.println("Error! Incorrect login details.");
                            System.out.println("1. Try again");
                            System.out.println("2. Proceed as New User");
                            System.out.println("3. Proceed as Guest User");
                            choice = input.nextInt();
                            input.nextLine(); // Catch newline from nextInt
                            if (choice == 2){
                                choice = 2;
                                looper = Boolean.FALSE;
                            }
                            else if (choice == 3){
                                choice = 3;
                                looper = Boolean.FALSE;
                            }
                        }
                        else{
                            System.out.println("User Login Successful!");
                            looper = Boolean.FALSE;
                        }
                    }


                }
                //2. New User
                if (choice == 2){
                    //Get user input to create new user object
                    System.out.println("Creating New Account:");
                    System.out.println("Enter Username:");
                    username = input.nextLine();
                    System.out.println("Enter Age:");
                    age = input.nextInt();
                    input.nextLine(); // Catch newline from nextInt
                    System.out.println("Enter Mobile number:");
                    mobilenumber = input.nextLine();
                    System.out.println("Enter Email Address:");
                    email = input.nextLine();
                    //Create usermanager object and Save user into database
                    myuser = new UserManager(username, age, mobilenumber, email);
                }
                //3. Proceed as Guest User
                if (choice == 3) {
                    //do nothing for now
                }


                while (choice != 0){
                    choice = -1;
                    while (choice <= -1 || choice >= 6){
                        try{
                            Scanner in = new Scanner(System.in);
                            System.out.println("1. Search movie");
                            System.out.println("2. View booking history");
                            System.out.println("3. List the Top 5 ranking by ticket sales");
                            System.out.println("4. List the Top 5 ranking by overall reviewers’ ratings");
                            System.out.println("5. List all movies");
                            System.out.println("0. Exit the Program");
                            choice = in.nextInt();
                            if (choice <= -1 || choice >= 6){
                                System.out.println("Error! Please enter either 0, 1, 2, 3 or 4:");
                            }
                        }
                        catch(InputMismatchException e){
                            System.out.println("That is not an integer, please try again." );
                        }
                    }
                    //0. Exit the Program
                    if (choice == 0){
                        System.exit(0);
                    }
                    //1. Search/List movie
                    else if (choice == 1 || choice == 5){
                        //Search and display movies
                        if (choice == 1){
                            System.out.println("Enter name of movie to search: " );
                            inputsearch = input.next();
                            mymovielist = DataManager.LoadMovies(inputsearch);
                            if (mymovielist.isEmpty()){
                                choice = -1;
                                System.out.println("No movie found!" );
                                continue;
                            }
                        }
                        //List all movies
                        else{
                            mymovielist = DataManager.LoadMovies("");
                        }
                        Boundary.DisplayMovies(mymovielist);

                        //Select movie by movie ID and print movie details – including reviews and ratings
                        System.out.println("Enter ID of the movie to see the details: " );
                        inputsearchint = input.nextInt();
                        mymovie = SearchManager.find_Movie_byID(mymovielist, inputsearchint);
                        Boundary.DisplayMovie(mymovie);
                        //Select movie, then search for all showtimes.
                        System.out.println("Enter 1 to show all showtimes for this movie or -2 to go back");
                        choice = input.nextInt();
                        if (choice == -2){
                            choice = -1;
                            continue;
                        }
                        //Select the showtime by index
                        else{
                            Boundary.DisplayCinemas(DataManager.LoadShowTimes(mymovie.getId()));
                        }
                        //3. Check seat availability and selection of seat/s.
                        looper = Boolean.TRUE;
                        while (looper){
                            System.out.println("Choose index of the showtime to view seat availability: ");
                            inputsearchint = input.nextInt();
                            input.nextLine(); //Catch newline from .nextInt()
                            Boundary.DisplaySeating(((DataManager.LoadShowTimes(mymovie.getId()).get(inputsearchint)).getSeats()));
                            choice = -1;
                            while (choice <= -1 || choice >= 3){
                                try{
                                    Scanner in = new Scanner(System.in);
                                    System.out.println("1. Select seats");
                                    System.out.println("2. Select another showtime");
                                    choice = in.nextInt();
                                    if (choice <= -1 || choice >= 3){
                                        System.out.println("Error! Please enter either 1 or 2:");
                                    }
                                }
                                catch(InputMismatchException e){
                                    System.out.println("That is not an integer, please try again." );
                                }
                            }
                            //1. Select seats
                            if (choice == 1){
                                looper = Boolean.FALSE; //stop while loop to check showtime
                                System.out.println("Enter row index of seat:" );
                                rowofseat = input.nextInt();
                                System.out.println("Enter col index of seat:" );
                                colofseat = input.nextInt();
                                input.nextLine();//Catch newline from input
                                //4. Book and purchase ticket

                            }
                            //2. Select another showtime
                            else if (choice == 2){
                                //Continue choosing different showtime
                            }
                        }
                    }
                    //2. View booking history
                    else if (choice == 2){
                        myuser.getBookings();
                    }
                    //3. List the Top 5 ranking by ticket sales
                    else if (choice == 3){
                        System.out.println("Listing top 5 movies by ticket sales:");
                        Boundary.DisplayMovies(SearchManager.get_topN_byRating(DataManager.LoadMovies("")));
                    }
                    //4. List the Top 5 ranking by overall reviewers’ ratings
                    else if (choice == 4){
                        System.out.println("Listing top 5 movies by overall reviewers’ ratings:");
                        Boundary.DisplayMovies(SearchManager.get_topN_bySale(DataManager.LoadMovies(""), DataManager.LoadBookings()));
                    }
                }
            }
        }
    }
}
