package Controller;

import Entity.*;
import View.Boundary;
//import View.UserBoundary;
import javafx.beans.binding.ObjectExpression;
import javafx.util.Pair;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        MovieManager movieManager=new MovieManager();
        StaffShowTimeManager staffShowTimeManager=new StaffShowTimeManager();
        StaffMovieManager staffMovieManager=new StaffMovieManager();
        SettingsManager settingsManager=new SettingsManager();

        int choice = -1;
        Scanner input = new Scanner(System.in);
        while(true){
            if (Boundary.ModuleSelection(choice, input ) == 1) {
                Pair<Integer, Staff> temp = (StaffManager.StaffLogin(input));
                int login_status = temp.getKey();
                Staff staff = temp.getValue();
                if(login_status==-1){continue;}
                else{
                    do{
                        Boundary.DisplayOptions("staffMenu");
                        choice = input.nextInt();
                        switch (choice){
                            case 1:
                                staffMovieManager.createMovieListing(input);
                                break;
                            case 2:
                                staffMovieManager.UpdateMovie(input);
                                break;
                            case 3:
                                staffMovieManager.removeMovieListing(input);
                                break;
                            case 4:
                                staffShowTimeManager.createCinemaShowtime(input);
                                break;
                            case 5:
                                staffShowTimeManager.updateCinemaShowtime(input);
                                break;
                            case 6:
                                staffShowTimeManager.removeCinemaShowtime(input);
                                break;
                            case 7:
                                settingsManager.configureSettings(input);
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

                User user = null;

                Boundary.DisplayOptions("userMenu");
                int user_login_choice = input.nextInt();
                while (user == null ){
                    if(user_login_choice == 1){
                        user = UserManager.UserLogin(input);
                    }
                    else if(user_login_choice == 2){
                        user = UserManager.createUser(input);
                    }
                    else if(user_login_choice == 0){
                        System.exit(-1);
                    }
                    else{
                        System.out.println("Enter Valid Input");
                        user_login_choice = input.nextInt();
                    }
                }
                do{
                    Boundary.DisplayOptions("userMovieMenu");
                    choice = input.nextInt();
                    switch (choice) {
                        case 1:
                        case 5:
                            movieManager.SearchListMovie(user,input, choice);
                            break;
                        case 2:
                            List<Booking> bookings = user.getBookings();
                            Boundary.DisplayBookings(bookings);
                            break;
                        case 3:
                            System.out.println("Listing top 5 movies by ticket sales:");
                            Boundary.DisplayMovie(movieManager.getTopBySales());
                            break;
                        case 4:
                            System.out.println("Listing top 5 movies by overall reviewers’ ratings:");
                            Boundary.DisplayMovie(movieManager.getTopByRatings());
                            break;
                        case 0:
                            break;
                        default:
                            System.out.println("Error! Please enter either 0, 1, 2, 3, 4 or 5:");
                    }
                }while(choice != 0);


                System.out.println("User module");
            }
            System.out.println("Module Finished. ");
            break;
        }



    }
}