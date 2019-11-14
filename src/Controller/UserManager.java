package Controller;

import java.util.*;

import Entity.*;
import View.Boundary;

/*
    createBookings
    getBookings
*/

public class UserManager {

    public static User UserLogin(Scanner input){//1. Existing User
        System.out.println("Please enter login details:");
        System.out.println("Enter Username:");
        String username = input.next();
        System.out.println("Enter Mobile number:");
        String mobilenumber = input.next();
        //Check with database if name and mobile number matches then create corresponding user object

        try{User myuser = AuthManager.getUser(username, mobilenumber);
            if(myuser!=null)
                System.out.println("User Login Successful!");
            else{
                System.out.println("Error! Incorrect login details.");
            }
            return myuser;}
        catch ( Exception e){System.out.println("Error! Incorrect login details."); return null;}
    }


    public static User createUser(Scanner input){
        //2. New User
        //Get user input to create new user object
        System.out.println("Creating New Account:");
        System.out.println("Enter Username:");
        String username = input.next();
        System.out.println("Enter Age:");
        int age = input.nextInt();
        input.nextLine(); // Catch newline from nextInt
        System.out.println("Enter Mobile number:");
        String mobilenumber = input.nextLine();
        System.out.println("Enter Email Address:");
        String email = input.next();
        //Create usermanager object and Save user into database
        User myuser = new User(username, age, mobilenumber, email);
        myuser.save();
        return myuser;

    }


    public String getType(int age){
        if(age<18){
            return "student";
        }
        else if(age>65){
            return "senior citizen";
        }
        else{
            return "regular";
        }
    }


    public static User getGuestUser(){
        User myuser = AuthManager.getUser("guestaccount", "10101010");
        return myuser;
    }


}