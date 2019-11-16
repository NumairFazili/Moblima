package Controller;

import Entity.Settings;
import View.Boundary;

import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 Represents the SettingsManager class to manage Settings for staff class.
 @author CZ2002 Group 1
 @version 1.0
 @since 15-11-2019
 */
public class SettingsManager {

    public  SettingsManager(){}

    /**
     * Displays all current setting details from database and prompts user for input to select which setting to be edited, then saves updated settings to database
     * @param input Scanner object
     */
    public static void configureSettings(Scanner input){
        //Choose which setting to configure
        Boundary.DisplaySettings();
        System.out.println("Choose which setting to configure: " );
        Settings mysettings = DataManager.LoadSettings();
        int choice = -1;
        do {
            do {
                try{
                    Scanner in = new Scanner(System.in);
                    Boundary.DisplayOptions("settingsMenu");
                    choice = in.nextInt();
                    in.nextLine();//catch newline
                    if (choice <= -1 || choice >= 10){
                        System.out.println("Error! Please enter either 0, 1, 2, 3, 4, 5, 6, 7,8 or 9:");
                    }
                }
                catch(InputMismatchException e){
                    System.out.println("That is not an integer, please try again." );
                }
            }while (choice <= -1 || choice >= 10);
            //0. Done, save new settings to database
            if (choice == 0){
                break;
            }
            //1. Prompt input for new base price
            else if (choice == 1){
                System.out.println("Enter new base price:");
                mysettings.setBasePrice(input.nextDouble());
                input.nextLine();//Catch newline from input.nextDouble()
            }
            //2. Prompt input for child price
            else if (choice == 2){
                System.out.println("Enter new child price:");
                mysettings.setChildPrice(input.nextDouble());
                input.nextLine();//Catch newline from input.nextDouble()
            }
            //3. Prompt input for senior price
            else if (choice == 3){
                System.out.println("Enter new senior price:");
                mysettings.setSeniorPrice(input.nextDouble());
                input.nextLine();//Catch newline from input.nextDouble()
            }
            //4. Prompt input for holiday price
            else if (choice == 4){
                System.out.println("Enter new holiday price:");
                mysettings.setHolidayPrice(input.nextDouble());
                input.nextLine();//Catch newline from input.nextDouble()
            }
            //5. Prompt input for silver price
            else if (choice == 5){
                System.out.println("Enter new silver price:");
                mysettings.setSilverPrice(input.nextDouble());
                input.nextLine();//Catch newline from input.nextDouble()
            }
            //6. Prompt input for gold price
            else if (choice == 6){
                System.out.println("Enter new gold price:");
                mysettings.setGoldPrice(input.nextDouble());
                input.nextLine();//Catch newline from input.nextDouble()
            }
            //7. Prompt input for platinum price
            else if (choice == 7){
                System.out.println("Enter new platinum price:");
                mysettings.setPlatinumPrice(input.nextDouble());
                input.nextLine();//Catch newline from input.nextDouble()
            }
            // prompt for 3D price
            else if(choice == 8){
                System.out.println("Enter new 3D price:");
                mysettings.setPrice3D(input.nextDouble());
                input.nextLine();//Catch newline from input.nextDouble()
            }
            //8. Prompt input for holidays
            else if (choice == 9){
                System.out.println("Enter new holidays in (dd/mm/yyy) format separated by commas :");
                mysettings.setHolidays(Arrays.asList(input.next().split(",")));
            }
        }while (choice != 0);
        //Save edited movie object to database
        if (DataManager.manageSettings(mysettings) == Boolean.TRUE){
            System.out.println("Settings successfully updated!\n");
        }
        else{
            System.out.println("Error! Settings failed to be updated!\n");
        }
    }




}
