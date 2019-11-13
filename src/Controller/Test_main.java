package Controller;

import Entity.*;
import View.Boundary;
import javafx.beans.binding.ObjectExpression;
import javafx.util.Pair;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Test_main {
    public static void main(String[] args) {
        int choice = -1;
        Scanner input = new Scanner(System.in);
        while(true){
            if (Boundary.ModuleSelection(choice, input ) == 1) {
                Pair<Integer, Staff> temp = (AuthManager.Stafflogin(input));
                int login_status = temp.getKey();
                Staff staff = temp.getValue();
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
                User user = null;
                while (user == null){
                    int user_login_choice = Boundary.Display_User_Choice();
                    if(user_login_choice == 1){
                        user = AuthManager.UserLogin(input);
                    }
                    else if(user_login_choice == 2){
                        user = UserManager.createUser(input);
                    }
                    else if(user_login_choice == 3){
                        user = UserManager.getGuestUser();
                    }
                }
                do{
                    Boundary.Display_User_main();
                    choice = input.nextInt();
                    switch (choice) {
                        case 1:
                            user.SearchListMovie(input, choice);
                            break;
                        case 2:
                            user.getBookings();
                            //Display bookings method to be added
                            break;
                        case 3:
                            System.out.println("Listing top 5 movies by ticket sales:");
                            Boundary.DisplayMovies(user.getTopBySales());
                            break;
                        case 4:
                            System.out.println("Listing top 5 movies by overall reviewers’ ratings:");
                            Boundary.DisplayMovies(user.getTopByRatings());
                        case 5:
                            user.SearchListMovie(input, choice);
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