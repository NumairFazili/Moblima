package Controller;

import Entity.Cinema;
import Entity.Movie;
import Entity.Settings;
import View.Boundary;
import javafx.beans.binding.ObjectExpression;
import javafx.util.Pair;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Test_main {
    public static void main(String[] args) {
        //Variables for admin to create movie
        int movieid, minage;
        double rating;


        String username, password, inputsearch, mobilenumber, email;
        int choice = -1;
        int age, inputsearchint, i, rowofseat, colofseat;
        boolean looper;
        //variables for admin to create showtime
        int cineplexid, cinemaid, movierating;
        String showtime, status, cinemaclass, movietype, moviereview;
        List<Integer> seats;

        //Variables for admin to edit settings
        double basePrice, childPrice, seniorPrice, holidayPrice, silverPrice, goldPrice, platinumPrice;
        List<String> holidays;
        Settings mysettings;

        Cinema mycinema;
        ArrayList<Movie> mymovielist;
        Movie mymovie;
        UserManager myuser = null;
        Scanner input = new Scanner(System.in);
        while(true){
            if (Boundary.ModuleSelection(choice, input ) == 1) {
                Pair<Integer, StaffManager> temp = (AuthManager.login(input));
                int login_status = temp.getKey();
                StaffManager staff = temp.getValue();
                if(login_status==-1){continue;}
                else{
                    do{
                        Boundary.Display_Staff_main();
                        choice = input.nextInt();
                        switch (choice){
                            case 1:
                                staff.createMovieListing(input);
                                break;
                            case 2:
                                staff.UpdateMovie(input);
                                break;
                            case 3:
                                staff.removeMovieListing(input);
                                break;
                            case 4:
                                break;
                            case 5:
                                break;
                            case 6:
                                break;
                            case 7:
                                break;
                            case 0:

                                break;
                            default:
                                System.out.println("Error! Please enter either 0, 1, 2, 3, 4, 5, 6 or 7:");
                        }
                    }while(choice != 0);

                }

            }
            else {
                System.out.println("User module");
            }
            System.out.println("Module Finished. ");
            break;
        }



    }
}