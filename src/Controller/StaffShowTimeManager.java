package Controller;

import Entity.Cinema;
import View.Boundary;

import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

/**
 Represents the Staff show time manager class to manage showtimes for the staff class
 @author CZ2002 Group 1
 @version 1.0
 @since 15-11-2019
 */

public class StaffShowTimeManager extends ShowTimeManager {

    /**
     * Creates a ShowTimeManager object
     */
    public StaffShowTimeManager(){
    }


    /**
     * Display all showtimes, update showtimes and delete showtimes
     * @param input Scanner object
     */
    public static void updateCinemaShowtime(Scanner input){
        //List all showtimes
        Boundary.DisplayCinemas(getAllShowTimes());
        //Select showtime to update by index
        System.out.println("Enter Index of the showtime to be updated: " );
        System.out.println("Otherwise enter -2 to go back" );
        int inputsearchint = input.nextInt();
        if (inputsearchint == -2){
            return;
        }
        input.nextLine();//Catch newline from input.nextInt()
        Cinema updatedCinema = getAllShowTimes().get(inputsearchint);
        Cinema cinema=getAllShowTimes().get(inputsearchint);
        //Choose which attribute of the showtime to be edited
        System.out.println("Choose attribute of showtime to be edited: " );
        int choice = -1;
        do {
            do{
                try{
                    Scanner in = new Scanner(System.in);
                    Boundary.DisplayOptions("cinemaMenu");
                    choice = in.nextInt();
                    if (choice <= -1 || choice >= 7){
                        System.out.println("Error! Please enter either 0, 1, 2, 3:");
                    }
                }
                catch(InputMismatchException e){
                    System.out.println("That is not an integer, please try again." );
                }
            }while (choice <= -1 || choice >= 8);
            //0. Done, save showtime to database
            if (choice == 0){
                break;
            }

            //2. Prompt input for Cineplex_ID and edit cinema object
            else if (choice == 1){
                System.out.println("Enter new Cineplex_ID:");
                updatedCinema.setCinplexID(input.nextInt());
                input.nextLine();//Catch newline from input.nextInt
            }

            //2. Prompt input for Cinema_ID and edit cinema object

            else if (choice == 2){
                System.out.println("Enter new Cinema_ID:");
                updatedCinema.setCinemaID(input.nextInt());
                input.nextLine();//Catch newline from input.nextInt
            }

            //3. Prompt input for Movie_ID and edit cinema object
            else if (choice == 3){
                System.out.println("Enter new Movie_ID:");
                updatedCinema.setMovieID(input.nextInt());
                input.nextLine();//Catch newline from input.nextInt
            }
            //4. Prompt input for ShowTime and edit cinema object
            else if (choice == 4){
                System.out.println("Enter new ShowTime:");
                updatedCinema.setTime(input.nextLine());
            }

            //6. Prompt input for cinema class and edit cinema object
            else if (choice == 5){
                System.out.println("Enter new class:");
                updatedCinema.setCinemaClass(input.next());
            }
            //7. Prompt input for MovieType and edit cinema object
            else if (choice == 6){
                System.out.println("Enter new MovieType:");
                updatedCinema.setMovieType(input.next());
            }

        }while (choice != 0);


        System.out.println();
        Boundary.DisplayCinemas(Arrays.asList(updatedCinema));
        System.out.println();

        if (DataManager.UpdateShowTime(cinema,updatedCinema) == Boolean.TRUE){
            System.out.println("Showtime successfully updated!\n");
        }
        else{
            System.out.println("Error! Showtime failed to be updated!\n");
        }
    }


    /**
     * Display all showtimes, choose index of shown showtimes to be removed, then deletes corresponding showtime from database
     * @param input Scanner object
     */
    public static void removeCinemaShowtime(Scanner input){
        //List all showtimes
        Boundary.DisplayCinemas(getAllShowTimes());
        //Select showtime to remove by index
        System.out.println("Enter the index of showtime to be removed: " );
        System.out.println("Otherwise enter -2 to go back" );
        int inputsearchint = input.nextInt();
        if (inputsearchint == -2){
            //Do nothing
        }
        else{
            Cinema mycinema = getAllShowTimes().get(inputsearchint);
            if(DataManager.UpdateShowTime(mycinema, true))
                System.out.println("Showtime Deleted Successfully\n");
            else
                System.out.println("Delete Failed\n");
        }
    }

    /**
     * Prompts user for input of cinema attributes and creates a new Cinema object to be saved in database
     * @param input Scanner object
     */
    public static void createCinemaShowtime(Scanner input){
        //Get user input for new showtime details
        System.out.println("Enter Cineplex_ID:");
        int cineplexid = input.nextInt();
        input.nextLine(); //Catch newline from .nextInt()
        System.out.println("Enter Cinema_ID:");
        int cinemaid = input.nextInt();
        input.nextLine(); //Catch newline from .nextInt()
        System.out.println("Enter Movie ID:");
        int movieid = input.nextInt();
        input.nextLine(); //Catch newline from .nextInt()
        System.out.println("Enter ShowTime:");
        String showtime = input.nextLine();
        System.out.println("Enter class:");
        String cinemaclass = input.nextLine();
        System.out.println("Enter MovieType:");
        String movietype = input.nextLine();
        System.out.println();
        //Create new showtime object and save movie listing to database using DataManager
        if(createShowTime(cineplexid, cinemaid, movieid, showtime, cinemaclass, Arrays.asList(), movietype))
            System.out.println("Showtime added successfully");
        else
            System.out.println("Failed to add Showtime");
    }

    /**
     * Display all showtimes, choose index of shown showtimes to be removed, then deletes corresponding showtime from database
     * @param cinplexID Cineplex ID of Cinema object
     * @param cinemaID Cinema ID of Cinema object
     * @param movieID Movie ID of Cinema object
     * @param time time of Cinema object
     * @param cinemaClass Cinema class of Cinema object
     * @param seats Seats taken of Cinema object
     * @param movieType Movie type of Cinema object
     * @return True if showtime is successfully saved to database, False otherwise
     */
    private static Boolean createShowTime(int cinplexID, int cinemaID, int movieID, String time, String cinemaClass, List<Integer> seats, String movieType){
        try{
            Cinema c = new Cinema(cinplexID, cinemaID, movieID, time,cinemaClass, seats, movieType);
            return DataManager.AddShowTimes(c);
        }catch(IllegalArgumentException e){
            System.out.println(e.getMessage());
            return false;
        }
    }


}
