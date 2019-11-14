package Entity;

import java.lang.reflect.Array;
import java.util.*;
import java.util.List;

import Controller.*;
import View.Boundary;

public class User{
    int age;
    String email; 
    String mobileNumber;
    String name;
    UserManager userManager;
    MovieManager movieManager;
    public User(){userManager=new UserManager(); movieManager=new MovieManager();}


    //for re-creating existing user
    public User(String name, int age, String mobileNumber, String email){
        this.age = age;
        //setName_User(name);
        this.name=name;
        this.email = email;
        this.mobileNumber = mobileNumber;
        userManager=new UserManager();
        movieManager=new MovieManager();

    }
    public int getAge() {return age; }
    public String getName() {
        return name;
    }
    public String getEmail(){
        return email;
    }
    public String getmobileNumber(){
        return mobileNumber;
    }
    public String getCustomerType (){return userManager.getType(this.getAge());}

    
    public String toString() {
        return name + "\nemail\t:" + email + "\nage\t:" + String.valueOf(age) + "\nmobile\t:" + mobileNumber + "\n";
    }

    public void save(){
        DataManager.ManageUser(this,true);
    }


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