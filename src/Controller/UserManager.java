package Controller;

import java.util.*;

import Entity.*;
import View.Boundary;

/*
    createBookings
    getBookings
*/

public class UserManager {

    User user;



    public static User getUser(String name, String mobileNumber){
        ArrayList<User> u_list = DataManager.LoadUser();

        for (int i = 0; i < u_list.size(); i++){

            if (u_list.get(i).getName().equals(name)){
                if(u_list.get(i).getmobileNumber().equals(mobileNumber)){
                    return u_list.get(i);
                }else{
                    return null;
                }
            }
        }
        return null;
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
        User myuser = UserManager.getUser("guestaccount", "10101010");
        return myuser;
    }


}