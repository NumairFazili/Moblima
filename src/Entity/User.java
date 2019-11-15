package Entity;

import java.util.*;
import java.util.List;

import Controller.DataManager;

public class User{
    int age;
    String email; 
    String mobileNumber;
    String name;
    public User(){}


    //for re-creating existing user
    public User(String name, int age, String mobileNumber, String email){
        this.age = age;
        //setName_User(name);
        this.name=name;
        this.email = email;
        this.mobileNumber = mobileNumber;
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
    public String getCustomerType (){
        if(age<15){
            return "Child";
        }
        else if(age>65){
            return "senior citizen";
        }
        else{
            return "regular";
        }
    }

    
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