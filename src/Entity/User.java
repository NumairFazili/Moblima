package Entity;

import java.lang.reflect.Array;
import java.util.*;
import java.util.List;

import Controller.*;
import View.Boundary;

/**
 Represents the Movie-goer using the MOBLIMA movie booking system, a movie-goer can book multiple movies
 @author CZ2002 Group 1
 @version 1.0
 @since 15-11-2019
 */
public class User{
    /**
     * Movie-goer age
     */
    int age;

    /**
     * Movie-goer email, mobile number, name
     */
    String email, mobileNumber, name;

    /**
     * UserManager object
     */
    UserManager userManager;

    /**
     * movieManager object
     */
    MovieManager movieManager;

    /**
     * Creates a UserManager and MovieManager object
     */
    public User(){userManager=new UserManager(); movieManager=new MovieManager();}


    //for re-creating existing user
    /**
     * Creates a new User, UserManager and movieManager object
     * @param age movie-goer age
     * @param email movie-goer email
     * @param mobileNumber movie-goer mobile number
     * @param name movie-goer name
     */
    public User(String name, int age, String mobileNumber, String email){
        this.age = age;
        //setName_User(name);
        this.name=name;
        this.email = email;
        this.mobileNumber = mobileNumber;
        userManager=new UserManager();
        movieManager=new MovieManager();

    }

    /**
     * Get user's age
     * @return user's age
     */
    public int getAge() {return age; }

    /**
     * Get user's name
     * @return user's name
     */
    public String getName() {
        return name;
    }

    /**
     * Get user's email
     * @return user's email
     */
    public String getEmail(){
        return email;
    }

    /**
     * Get user's mobile number
     * @return user's mobile number
     */
    public String getmobileNumber(){
        return mobileNumber;
    }

    /**
     * Get user type (Child, Senior or Regular)
     * @return user type
     */
    public String getCustomerType (){
        if(age<15){
            return "Child";
        }
        else if(age>65){
            return "Senior";
        }
        else{
            return "regular";
        }
    }


    /**
     * Get user details in a String
     * @return User details in a single String
     */
    public String toString() {
        return name + "\nemail\t:" + email + "\nage\t:" + String.valueOf(age) + "\nmobile\t:" + mobileNumber + "\n";
    }

    /**
     * Save user details to database
     */
    public void save(){
        DataManager.ManageUser(this,true);
    }


    /**
     * Get booking history of user
     * @return List of booking objects of user
     */
    public List<Booking> getBookings(){
        ArrayList<Booking> bookingArrayList = new ArrayList<Booking>();
        for(Booking booking: DataManager.LoadBookings()){
            if(booking.getCustomerName().equals(this.getName())&& booking.getMobileNumber().equals(this.getmobileNumber())){
                bookingArrayList.add(booking);
            }
        }
        return bookingArrayList;
    }



}