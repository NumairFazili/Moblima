package Controller;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import Entity.*;
import View.Boundary;
import javafx.util.Pair;


//return StaffManager/UserManager

public class AuthManager{

    public static Pair<Integer,StaffManager> login(Scanner input){
        boolean adminauth = false;
        StaffManager mystaff =new StaffManager(new Staff("NULL","NULL")) ;
        while(adminauth==false){
            System.out.println("ADMIN USER:");
            System.out.println("Please enter login details, Input -1 to go back:");
            System.out.println("Enter Username:");
            String username = input.next();
            if(username.equals("-1")){
                return new Pair<Integer,StaffManager>(-1,mystaff);
            }
            System.out.println("Enter Password:");
            String password = input.next();
            mystaff = AuthManager.getStaff(username,password);
            if(mystaff != null){
                System.out.println("Admin account successfully logged in!");
                adminauth = true;
            }
            else{
                System.out.println("The username or password you typed is incorrect. Please try again.");
                adminauth = false;
            }
        }

        return new Pair<Integer, StaffManager>(0,mystaff);

    }

    public static UserManager UserLogin(Scanner input){//1. Existing User
        while (true){
            UserManager myuser;
            System.out.println("Please enter login details:");
            System.out.println("Enter Username:");
            String username = input.next();
            System.out.println("Enter Mobile number:");
            String mobilenumber = input.next();
            //Check with database if name and mobile number matches then create corresponding user object
            myuser = AuthManager.getUser(username, mobilenumber);
            if (myuser == null){
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
        }
    }

    public static StaffManager getStaff(String username, String password){

        ArrayList<Staff> staffList = DataManager.Loadstaff();

        for(int i = 0; i < staffList.size(); i++){
            if(staffList.get(i).getName().equals(username)){
                if(staffList.get(i).checkPassword(password)){
                    return new StaffManager(staffList.get(i));
                }
                return null;
            }
        }
        return null;
    }

    public static UserManager getUser(String name, String mobileNumber){
        ArrayList<User> u_list = DataManager.LoadUser();

        for (int i = 0; i < u_list.size(); i++){

            if (u_list.get(i).getName().equals(name)){
                if(u_list.get(i).getmobileNumber().equals(mobileNumber)){
                    return new UserManager(u_list.get(i));
                }else{
                    return null;
                }
            }
        }
        return null;
    }

    public static void main(String args[]){
        UserManager u = AuthManager.getUser("test1", "1234567890");
        if(u != null){
            List<Booking> m_list = u.getBookings();
            for (int i = 0; i < m_list.size(); i++){
                System.out.println(m_list.get(i).getBookingID());
            }
        }else{
            System.out.println("Error in authenticating existing user!");
        }
    }

}
