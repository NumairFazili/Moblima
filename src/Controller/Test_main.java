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
        //variables for admin to create showtime
        String showtime, status, cinemaclass, movietype, moviereview;
        List<Integer> seats;

        //Variables for admin to edit settings
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
                                staff.createCinemaShowtime(input);
                                break;
                            case 5:
                                staff.updateCinemaShowtime(input);
                                break;
                            case 6:
                                staff.removeCinemaShowtime(input);
                                break;
                            case 7:
                                staff.configureSettings(input);
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
                int user_login_choice = AuthManager.UserLogin(input);
                //while loop should be inside Userlogin.(Leave it for me)
                if(user_login_choice == 1){
                    UserManager.createUser(input);
                }
                do{
                    Boundary.Display_User_main();
                    choice = input.nextInt();

                    switch (choice){
                        case 1:
                            user.createMovieListing(input);
                            break;
                        case 2:
                            user.UpdateMovie(input);
                            break;
                        case 3:
                            user.removeMovieListing(input);
                            break;
                        case 4:
                            user.createCinemaShowtime(input);
                            break;
                        case 5:
                            user.updateCinemaShowtime(input);
                            break;
                        case 0:
                            break;
                }while(choice != 0);


                System.out.println("User module");
            }
            System.out.println("Module Finished. ");
            break;
        }



    }
}