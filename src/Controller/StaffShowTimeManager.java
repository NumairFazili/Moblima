package Controller;

import Entity.Cinema;
import View.Boundary;

import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class StaffShowTimeManager extends ShowTimeManager {

    public StaffShowTimeManager(){
    }

//    public static void main(String[] args) {
//        StaffShowTimeManager staffShowTimeManager=new StaffShowTimeManager();
//        staffShowTimeManager.updateCinemaShowtime();
//    }
    public void updateCinemaShowtime(Scanner input){
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
        Cinema cinema= getAllShowTimes().get(inputsearchint);
        //Choose which attribute of the showtime to be edited
        System.out.println("Choose attribute of showtime to be edited: " );
        int choice = -1;
        while (choice != 0){
            choice = -1;
            while (choice <= -1 || choice >= 8){
                try{
                    Scanner in = new Scanner(System.in);
                    Boundary.DisplayOptions("cinemaMenu");
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
                System.out.println("Enter new Cineplex_ID:");
                this.updateCineplexID(updatedCinema, input.nextInt());
                input.nextLine();//Catch newline from input.nextInt
            }

            //2. Prompt input for Cinema_ID and edit cinema object

            else if (choice == 2){
                System.out.println("Enter new Cinema_ID:");
                this.updateCinemaID(updatedCinema, input.nextInt());
                input.nextLine();//Catch newline from input.nextInt
            }

            //3. Prompt input for Movie_ID and edit cinema object
            else if (choice == 3){
                System.out.println("Enter new Movie_ID:");
                this.updateMovieID(updatedCinema, input.nextInt());
                input.nextLine();//Catch newline from input.nextInt
            }
            //4. Prompt input for ShowTime and edit cinema object
            else if (choice == 4){
                System.out.println("Enter new ShowTime:");
                this.updateTime(updatedCinema, input.nextLine());
            }

            //6. Prompt input for cinema class and edit cinema object
            else if (choice == 5){
                System.out.println("Enter new class:");
                this.updateClass(updatedCinema, input.next());
            }
            //7. Prompt input for MovieType and edit cinema object
            else if (choice == 6){
                System.out.println("Enter new MovieType:");
                this.updateMovieType(updatedCinema, input.next());
            }

        }


        System.out.println();
        Boundary.DisplayCinemas(Arrays.asList(updatedCinema));
        System.out.println();

        if (this.saveShowTimeChanges(cinema,updatedCinema) == Boolean.TRUE){
            System.out.println("Showtime successfully updated!\n");
        }
        else{
            System.out.println("Error! Showtime failed to be updated!\n");
        }
    }

    public void removeCinemaShowtime(Scanner input){
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
        this.createShowTime(cineplexid, cinemaid, movieid, showtime, cinemaclass, Arrays.asList(), movietype);
    }


    public void updateCineplexID(Cinema cinema, int cineplexID){ cinema.setCinplexID(cineplexID); }
    public void updateCinemaID(Cinema cinema, int cinemaID){ cinema.setCinemaID(cinemaID); }
    public void updateMovieID(Cinema cinema, int movieID){ cinema.setMovieID(movieID); }
    public void updateTime(Cinema cinema, String time){ cinema.setTime(time);}
    public void updateClass(Cinema cinema, String cinemaClass){
        cinema.setCinemaClass(cinemaClass);
    }
    public void updateMovieType(Cinema cinema, String movieType){
        cinema.setMovieType(movieType);
    }
    public boolean saveShowTimeChanges(Cinema cinema,Cinema updatedCinema){ return DataManager.UpdateShowTime(cinema,updatedCinema);}
    public void createShowTime(int cinplexID, int cinemaID, int movieID, String time, String cinemaClass, List<Integer> seats, String movieType){
        Cinema c = new Cinema(cinplexID, cinemaID, movieID, time,cinemaClass, seats, movieType);
        DataManager.AddShowTimes(c);
    }


}
