package Controller;

import Entity.*;
import View.Boundary;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        int choice = -1;
        Scanner input = new Scanner(System.in);
        while (true) {
                if (Boundary.moduleSelection(choice, input) == 1) {
                    Staff staff = (StaffManager.staffLogin(input));
                    if (staff == null) {
                        continue;
                    } else {
                        do {
                            Boundary.displayOptions("staffMenu");
                            choice = input.nextInt();
                            switch (choice) {
                                case 1:
                                    StaffMovieManager.createMovieListing(input);
                                    break;
                                case 2:
                                    StaffMovieManager.updateMovie(input);
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
                                    Boundary.displayMovie(StaffMovieManager.getTopBySales());
                                    break;
                                case 8:
                                    System.out.println("Listing top 5 movies by overall reviewers’ ratings:");
                                    Boundary.displayMovie(StaffMovieManager.getTopByRatings());
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

                    Boundary.displayOptions("userMenu");
                    int user_login_choice = input.nextInt();
                    while (user == null) {
                        if (user_login_choice == 1) {
                            user = UserManager.userLogin(input);
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
                        Boundary.displayOptions("userMovieMenu");
                        choice = input.nextInt();
                        switch (choice) {
                            case 1:
                            case 5:
                                UserMovieManager userMovieManager = new UserMovieManager(user);
                                userMovieManager.searchListMovie(choice);
                                break;
                            case 2:
                                Boundary.displayBookings(user.getBookings());
                                break;
                            case 3:
                                System.out.println("Listing top 5 movies by ticket sales:");
                                Boundary.displayMovie(UserMovieManager.getTopBySales());
                                break;
                            case 4:
                                System.out.println("Listing top 5 movies by overall reviewers’ ratings:");
                                Boundary.displayMovie(UserMovieManager.getTopByRatings());
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
