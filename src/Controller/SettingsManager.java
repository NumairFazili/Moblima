package Controller;

import Entity.Settings;
import Entity.Staff;
import View.Boundary;

import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class SettingsManager {

    Staff staff;

    public  SettingsManager(){
    }

    public void configureSettings(Scanner input){
        //Choose which setting to configure
        Boundary.DisplaySettings();
        System.out.println("Choose which setting to configure: " );
        Settings mysettings = this.showSettings();
        int choice = -1;
        while (choice != 0){
            choice = -1;
            while (choice <= -1 || choice >= 9){
                try{
                    Scanner in = new Scanner(System.in);
                    Boundary.DisplayOptions("settingsMenu");
                    choice = in.nextInt();
                    in.nextLine();//catch newline
                    if (choice <= -1 || choice >= 9){
                        System.out.println("Error! Please enter either 0, 1, 2, 3, 4, 5, 6, 7 or 8:");
                    }
                }
                catch(InputMismatchException e){
                    System.out.println("That is not an integer, please try again." );
                }
            }
            //0. Done, save new settings to database
            if (choice == 0){
                break;
            }
            //1. Prompt input for new base price
            else if (choice == 1){
                System.out.println("Enter new base price:");
                this.updateBasePrice(mysettings, input.nextDouble());
            }
            //2. Prompt input for child price
            else if (choice == 2){
                System.out.println("Enter new child price:");
                this.updateChildPrice(mysettings, input.nextDouble());
            }
            //3. Prompt input for senior price
            else if (choice == 3){
                System.out.println("Enter new senior price:");
                this.updateSeniorPrice(mysettings, input.nextDouble());
            }
            //4. Prompt input for holiday price
            else if (choice == 4){
                System.out.println("Enter new holiday price:");
                this.updateHolidayPrice(mysettings, input.nextDouble());
            }
            //5. Prompt input for silver price
            else if (choice == 5){
                System.out.println("Enter new silver price:");
                this.updateSilverPrice(mysettings, input.nextDouble());
            }
            //6. Prompt input for gold price
            else if (choice == 6){
                System.out.println("Enter new gold price:");
                this.updateGoldPrice(mysettings, input.nextDouble());
            }
            //7. Prompt input for platinum price
            else if (choice == 7){
                System.out.println("Enter new platinum price:");
                this.updatePlatinumPrice(mysettings, input.nextDouble());
                input.nextLine();//Catch newline from input.nextDouble()
            }
            //8. Prompt input for holidays
            else if (choice == 8){
                System.out.println("Enter new holidays:");
                this.updateHoliday(mysettings, Arrays.asList(input.next().split(",")));
            }
        }
        //Save edited movie object to database
        if (this.saveSettingsChanges(mysettings) == Boolean.TRUE){
            System.out.println("Settings successfully updated!\n");
        }
        else{
            System.out.println("Error! Settings failed to be updated!\n");
        }
    }
    public void updateBasePrice(Settings s, double base){
        s.setBasePrice(base);
    }
    public void updateChildPrice(Settings s, double child){
        s.setChildPrice(child);
    }
    public void updateSeniorPrice(Settings s, double senior){
        s.setSeniorPrice(senior);
    }
    public void updateHolidayPrice(Settings s, double holiday){
        s.setHolidayPrice(holiday);
    }
    public void updateSilverPrice(Settings s, double silver){
        s.setSilverPrice(silver);
    }
    public void updateGoldPrice(Settings s, double gold){
        s.setGoldPrice(gold);
    }
    public void updatePlatinumPrice(Settings s, double platinum){
        s.setPlatinumPrice(platinum);
    }
    public void updateHoliday(Settings s, List<String> holiday){
        s.setHolidays(holiday);
    }
    public boolean saveSettingsChanges(Settings s){
        return DataManager.manageSettings(s);
    }
    public Settings showSettings(){ return DataManager.LoadSettings(); }



}
