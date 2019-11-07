package Controller;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.ArrayList;
import Entity.*;
import View.Boundary;

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        String username, password, inputsearch, mobilenumber, email;
        int choice = -1;
        int adminauth, age, inputsearchint;
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

                        }
                        //2. Update movie listing
                        else if (choice == 2){

                        }
                        //3. Remove movie listing
                        else if (choice == 3){

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
                    System.out.println("Please enter login details:");
                    System.out.println("Enter Username:");
                    username = input.next();
                    System.out.println("Enter Mobile number:");
                    mobilenumber = input.next();
                    //Check with database if name and mobile number matches
                }
                //2. New User
                else if (choice == 2){
                    System.out.println("Creating New Account:");
                    System.out.println("Enter Username:");
                    username = input.next();
                    System.out.println("Enter Mobile number:");
                    mobilenumber = input.next();
                    System.out.println("Enter Age:");
                    age = input.nextInt();
                    System.out.println("Enter Email Address:");
                    email = input.next();
                    //Create new user object and save to database
                }
                //3. Proceed as Guest User
                else if (choice == 3) {
                    //Initialize guest user object
                }


                while (choice != 0){
                    choice = -1;
                    while (choice <= -1 || choice >= 5){
                        try{
                            Scanner in = new Scanner(System.in);
                            System.out.println("1. Search/List movie");
                            System.out.println("2. View booking history");
                            System.out.println("3. List the Top 5 ranking by ticket sales");
                            System.out.println("4. List the Top 5 ranking by overall reviewers’ ratings");
                            System.out.println("0. Exit the Program");
                            choice = in.nextInt();
                            if (choice <= -1 || choice >= 5){
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
                    else if (choice == 1){
                        System.out.println("Enter name of movie: " );
                        inputsearch = input.next();
                        ArrayList<Movie> tempmovielist = new ArrayList<>();
                        tempmovielist = DataManager.LoadMovies(inputsearch);

                        Boundary.DisplayMovies(tempmovielist);

                        //Display movies
                        System.out.println("Enter ID of the movie to see the details: " );
                        inputsearchint = input.nextInt();
                        SearchManager.find_Movie_byID(tempmovielist, inputsearchint);
                        //2. View movie details – including reviews and ratings
                        //Select movie, then search for all showtimes.
                        //Select the showtime by index
                        //3. Check seat availability and selection of seat/s.

                        //4. Book and purchase ticket
                    }
                    //2. View booking history
                    else if (choice == 2){

                    }
                    //3. List the Top 5 ranking by ticket sales
                    else if (choice == 3){

                    }
                    //4. List the Top 5 ranking by overall reviewers’ ratings
                    else if (choice == 4){

                    }
                }
            }
        }
    }
}
