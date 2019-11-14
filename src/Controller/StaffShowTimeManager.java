package Controller;

import Entity.Cinema;
import View.Boundary;
import org.junit.Test;

import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class StaffShowTimeManager extends ShowTimeManager {

    ShowTimeManager showTimeManager;

    public StaffShowTimeManager(){
        showTimeManager=new ShowTimeManager();
    }


//    public static void main(String[] args) {
//        StaffShowTimeManager staffShowTimeManager=new StaffShowTimeManager();
//        staffShowTimeManager.updateCinemaShowtime();
//    }
    public void updateCinemaShowtime(Scanner input){
        //List all showtimes
        Boundary.DisplayCinemas(this.getAllShowTimes());
        //Select showtime to update by index
        System.out.println("Enter Index of the showtime to be updated: " );
        System.out.println("Otherwise enter -2 to go back" );
        int inputsearchint = input.nextInt();
        if (inputsearchint == -2){
            return;
        }
        input.nextLine();//Catch newline from input.nextInt()
        Cinema mycinema = this.getAllShowTimes().get(inputsearchint);
        //Choose which attribute of the showtime to be edited
        System.out.println("Choose attribute of showtime to be edited: " );
        int choice = -1;
        while (choice != 0){
            choice = -1;
            while (choice <= -1 || choice >= 8){
                try{
                    Scanner in = new Scanner(System.in);
                    System.out.println("1. Status");
                    System.out.println("2. class");
                    System.out.println("3. MovieType");
                    System.out.println("0. Done");
                    choice = in.nextInt();
                    if (choice <= -1 || choice >= 4){
                        System.out.println("Error! Please enter either 0, 1, 2, 3:");
                    }
                }
                catch(InputMismatchException e){
                    System.out.println("That is not an integer, please try again." );
                }
            }
            //0. Done, save showtime to database
            if (choice == 0){
                choice = -1;
                break;
            }
            else if (choice == 1){
                System.out.println("Enter new Status:");
                this.updateStatus(mycinema, input.next());
            }
            //6. Prompt input for cinema class and edit cinema object
            else if (choice == 2){
                System.out.println("Enter new class:");
                this.updateClass(mycinema, input.next());
            }
            //7. Prompt input for MovieType and edit cinema object
            else if (choice == 3){
                System.out.println("Enter new MovieType:");
                this.updateMovieType(mycinema, input.next());
            }

        }


        System.out.println();
        Boundary.DisplayCinemas(Arrays.asList(mycinema));
        System.out.println();

        if (this.saveShowTimeChanges(mycinema) == Boolean.TRUE){
            System.out.println("Showtime successfully updated!");
        }
        else{
            System.out.println("Error! Showtime failed to be updated!");
        }
    }
    @Override
    public List<Cinema> getAllShowTimes(){
        return  DataManager.LoadShowTimes(-1);
    }


    public void removeCinemaShowtime(Scanner input){
        //List all showtimes
        Boundary.DisplayCinemas(showTimeManager.getAllShowTimes());
        //Select showtime to remove by index
        System.out.println("Enter the index of showtime to be removed: " );
        System.out.println("Otherwise enter -2 to go back" );
        int inputsearchint = input.nextInt();
        if (inputsearchint == -2){
            //Do nothing
        }
        else{
            Cinema mycinema = this.getAllShowTimes().get(inputsearchint);
            DataManager.UpdateShowTime(mycinema, true);
        }
    }

    public void createCinemaShowtime(Scanner input){
        //Get user input for new showtime details
        System.out.println("Enter Cineplex_ID:");
        int cineplexid = input.nextInt();
        input.nextLine(); //Catch newline from .nextInt()
        System.out.println("Enter Cinema_ID:");
        int cinemaid = input.nextInt();
        input.nextLine(); //Catch newline from .nextInt()
        System.out.println("Enter Movie ID:");//might need to error check if movieid is legit
        int movieid = input.nextInt();
        input.nextLine(); //Catch newline from .nextInt()
        System.out.println("Enter ShowTime:");
        String showtime = input.nextLine();
        System.out.println("Enter Status");
        String status = input.nextLine();
        System.out.println("Enter class:");
        String cinemaclass = input.nextLine();
        //System.out.println("Enter Seats:"); //Staff probably does not need to enter seat details
        //seats = input.nextLine();
        System.out.println("Enter MovieType:");
        String movietype = input.nextLine();
        //Create new showtime object and save movie listing to database using DataManager
        this.createShowTime(cineplexid, cinemaid, movieid, showtime, status, cinemaclass, Arrays.asList(), movietype);
    }



    public void updateStatus(Cinema c, String s){
        c.setStatus(s);
    }
    public void updateClass(Cinema c, String s){
        c.setCinemaClass(s);
    }
    public void updateMovieType(Cinema c, String s){
        c.setMovieType(s);
    }
    public boolean saveShowTimeChanges(Cinema c){
        return DataManager.UpdateShowTime(c,false);
    }
    public void deleteShowTime(Cinema c){
        c.setStatus("ended");
    }

    public void createShowTime(int cinplexID, int cinemaID, int movieID, String time, String status, String cinemaClass, List<Integer> seats, String movieType){
        Cinema c = new Cinema(cinplexID, cinemaID, movieID, time, status, cinemaClass, seats, movieType);
        DataManager.AddShowTimes(c);
    }


}
