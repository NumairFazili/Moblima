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


    public static Staff getStaff(String username, String password){

        ArrayList<Staff> staffList = DataManager.Loadstaff();

        for(int i = 0; i < staffList.size(); i++){
            if(staffList.get(i).getName().equals(username)){
                if(staffList.get(i).checkPassword(password)){
                    return staffList.get(i);
                }
                return null;
            }
        }
        return null;
    }


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
}
