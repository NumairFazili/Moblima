package Controller;

import java.util.ArrayList;
import java.util.List;

import Entity.*;
import View.Boundary;


//return StaffManager/UserManager

public class AuthManager{

    public static StaffManager getStaff(String username, String password){

        ArrayList<Staff> staffList = DataManager.loadStaff();

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
        UserManager u = AuthenticationManager.getUser("test1", "1234567890");
        if(u != null){
            ArrayList<Booking> m_list = u.getBookings();
            for (int i = 0; i < m_list.size(); i++){
                System.out.println(m_list.get(i).getBookingID());
            }
        }else{
            System.out.println("Error in authenticating existing user!");
        }
    }

}