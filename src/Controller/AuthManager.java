package Controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import Entity.*;
import View.Boundary;
import javafx.util.Pair;


//return StaffManager/UserManager

public class AuthManager{

    public static Pair<Integer,Staff> Stafflogin(Scanner input){
        boolean adminauth = false;
        Staff mystaff =new Staff("NULL","NULL") ;
        while(adminauth==false){
            System.out.println("ADMIN USER:");
            System.out.println("Please enter login details, Input -1 to go back:");
            System.out.println("Enter Username:");
            String username = input.next();
            if(username.equals("-1")){
                return new Pair<Integer,Staff>(-1,mystaff);
            }
            System.out.println("Enter Password:");
            String password = input.next();
            mystaff = StaffManager.getStaff(username,password);
            if(mystaff != null){
                System.out.println("Admin account successfully logged in!");
                adminauth = true;
            }
            else{
                System.out.println("The username or password you typed is incorrect. Please try again.");
                adminauth = false;
            }
        }

        return new Pair<>(0, mystaff);

    }

    public static User UserLogin(Scanner input){//1. Existing User
        System.out.println("Please enter login details:");
        System.out.println("Enter Username:");
        String username = input.next();
        System.out.println("Enter Mobile number:");
        String mobilenumber = input.next();
        //Check with database if name and mobile number matches then create corresponding user object

        try{User myuser = UserManager.getUser(username, mobilenumber);
            if(myuser!=null)
                System.out.println("User Login Successful!");
            else{
                System.out.println("Error! Incorrect login details.");
            }
            return myuser;}
        catch ( Exception e){System.out.println("Error! Incorrect login details."); return null;}
/*
        if (flag  == false)
                System.out.println("Error! Incorrect login details.");
                System.out.println("1. Try again");
                System.out.println("0. Go back");
                int choice = input.nextInt();
                input.nextLine(); // Catch newline from nextInt
                if (choice == 0){
                    return null;
                }
            }
            else{
                System.out.println("User Login Successful!");
                return myuser;
            }

 */

    }







}
