package Controller;

import java.util.*;

import Entity.User;

/**
 Represents UserManager class to manage user's creation and login
 @author CZ2002 Group 1
 @version 1.0
 @since 15-11-2019
 */
public class UserManager {

    /**
     * Prompts user to enter login details(username and mobile number) and compares values with database. If a match is found, return corresponding User object, else returns null
     * @param input Scanner object
     * @return User object if match is found in database, null if no match is found.
     */
    public static User UserLogin(Scanner input){//1. Existing User
        System.out.println("Please enter login details:");
        System.out.println("Enter Username:");
        String username = input.next();
        System.out.println("Enter Mobile number:");
        String mobilenumber = input.next();
        //Check with database if name and mobile number matches then create corresponding user object

        try{User myuser = AuthManager.getUser(username, mobilenumber);
            if(myuser!=null)
                System.out.println("User Login Successful!\n");
            else{
                System.out.println("Error! Incorrect login details.\n");
            }
            return myuser;}
        catch ( Exception e){System.out.println("Error! Incorrect login details.\n"); return null;}
    }

    /**
     * Prompts user to enter details (username, age, mobile number, email address) to create a new User object and save it into the database
     * @param input Scanner object
     * @return Corresponding created User object
     */
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
        System.out.println();
        //Create usermanager object and Save user into database
        User myuser = new User(username, age, mobilenumber, email);
        myuser.save();
        return myuser;

    }

}