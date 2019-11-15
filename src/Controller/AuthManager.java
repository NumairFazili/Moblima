package Controller;
import java.util.ArrayList;
import Entity.Staff;
import Entity.User;

public class AuthManager{

    public static Staff getStaff(String username, String password){

        ArrayList<Staff> staffList = DataManager.Loadstaff();
        for(int i = 0; i < staffList.size(); i++)
            if(staffList.get(i).getName().equals(username) && staffList.get(i).checkPassword(password))
                    return staffList.get(i);

        return null;
        }



    public static User getUser(String name, String mobileNumber){
        ArrayList<User> userList = DataManager.LoadUser();

        for (int i = 0; i < userList.size(); i++)
            if (userList.get(i).getName().equals(name)  &&  userList.get(i).getmobileNumber().equals(mobileNumber) )
                    return userList.get(i);

        return null;
    }
}
