package Controller;
import java.util.ArrayList;
import Entity.*;

/**
 Represents the AuthManager class, to authenticate staff and user login details
 @author CZ2002 Group 1
 @version 1.0
 @since 15-11-2019
 */
public class AuthManager{

    /**
     * Checks if input username and password corresponds to any staff accounts in the database
     * @param username input username of staff
     * @param password input password of staff
     * @return Staff object with corresponding details, if username and password corresponds to a staff account in the database, null otherwise
     */
    public static Staff validateStaff(String username, String password){

        ArrayList<Staff> staffList = DataManager.loadstaff();
        for(int i = 0; i < staffList.size(); i++)
            if(staffList.get(i).getName().equals(username) && staffList.get(i).checkPassword(password))
                    return staffList.get(i);

        return null;
        }


    /**
     * Checks if input name and mobile number corresponds to any user accounts in the database
     * @param name input username of staff
     * @param mobileNumber input password of staff
     * @return User object with corresponding details, if username and password corresponds to a user account in the database, null otherwise
     */
    public static User validateUser(String name, String mobileNumber){
        ArrayList<User> userList = DataManager.loadUser();

        for (int i = 0; i < userList.size(); i++)
            if (userList.get(i).getName().equals(name)  &&  userList.get(i).getmobileNumber().equals(mobileNumber) )
                    return userList.get(i);

        return null;
    }
}
