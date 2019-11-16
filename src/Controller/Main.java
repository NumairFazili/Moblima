package Controller;

import Entity.*;
import View.Boundary;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        int choice = -1;
        Scanner input = new Scanner(System.in);
        while (true) {
                if (Boundary.ModuleSelection(choice, input) == 1) {
                    Staff staff = (StaffManager.StaffLogin(input));
                    if (staff == null) {
                        continue;
                    } else {
                        do {
                            Boundary.DisplayOptions("staffMenu");
                            choice = input.nextInt();
                            switch (choice) {
                                case 1:
                                    StaffMovieManager.createMovieListing(input);
                                    break;
                                case 2:
                                    StaffMovieManager.UpdateMovie(input);
                                    break;
                                case 3:
                                    StaffShowTimeManager.createCinemaShowtime(input);
                                    break;
                                case 4:
                                    StaffShowTimeManager.updateCinemaShowtime(input);
                                    break;
                                case 5:
                                    StaffShowTimeManager.removeCinemaShowtime(input);
                                    break;
                                case 6:
                                    SettingsManager.configureSettings(input);
                                    break;
                                case 7:
                                    System.out.println("Listing top 5 movies by ticket sales:");
                                    Boundary.DisplayMovie(StaffMovieManager.getTopBySales());
                                    break;
                                case 8:
                                    System.out.println("Listing top 5 movies by overall reviewers’ ratings:");
                                    Boundary.DisplayMovie(StaffMovieManager.getTopByRatings());
                                    break;

                                case 0:
                                    break;
                                default:
                                    System.out.println("Error! Please enter either 0, 1, 2, 3, 4, 5,6 or 7:");
                            }
                        } while (choice != 0);

                    }

                } else {

                    User user = null;

                    Boundary.DisplayOptions("userMenu");
                    int user_login_choice = input.nextInt();
                    while (user == null) {
                        if (user_login_choice == 1) {
                            user = UserManager.UserLogin(input);
                        } else if (user_login_choice == 2) {
                            user = UserManager.createUser(input);
                        } else if (user_login_choice == 0) {
                            System.exit(-1);
                        } else {
                            System.out.println("Enter Valid Input");
                            user_login_choice = input.nextInt();
                        }
                    }
                    do {
                        Boundary.DisplayOptions("userMovieMenu");
                        choice = input.nextInt();
                        switch (choice) {
                            case 1:
                            case 5:
                                Movie movie = UserMovieManager.SearchListMovie(choice);
                                if(movie!=null) UserMovieManager.BookMovie(movie,user,choice);
                                break;
                            case 2:
                                Boundary.DisplayBookings(user.getBookings());
                                break;
                            case 3:
                                System.out.println("Listing top 5 movies by ticket sales:");
                                Boundary.DisplayMovie(UserMovieManager.getTopBySales());
                                break;
                            case 4:
                                System.out.println("Listing top 5 movies by overall reviewers’ ratings:");
                                Boundary.DisplayMovie(UserMovieManager.getTopByRatings());
                                break;
                            case 0:
                                break;
                            default:
                                System.out.println("Error! Please enter either 0, 1, 2, 3, 4 or 5:");
                        }
                    } while (choice != 0);


                    System.out.println("User module");
                }
                System.out.println("Module Finished. ");
                break;
            }


        }
    }
