package Entity;

import java.util.List;
import java.util.ArrayList;
import java.util.List;

import Controller.DataManager;

public class User extends Person{
    int age;
    String email; 
    String mobileNumber;
    List<String> bookings;

    //for re-creating existing user
    public User(String name, int age, String mobileNumber, String email, List<String> bookings){
        this.age = age;
        setName_User(name);
        this.email = email;
        this.mobileNumber = mobileNumber;
        this.bookings = bookings;
    }

    public void setName_User(String name){
        this.setName(name);     //using base class function
    }
    public void setAge(int age){
        this.age = age;
    }
    public void setEmail(String email){
        this.email = email;
    }
    public void setmobileNumber(String mobileNumber){
        this.mobileNumber = mobileNumber;
    }
    public void addBooking(Booking b){
        bookings.add(b.getBookingID());
    }
    
    public int getAge(){
        if(age == 0){
            return -1;
        }
        return age;
    }
    public String getName() {
        return name;
    }
    public String getEmail(){
        return email;
    }
    public String getmobileNumber(){
        return mobileNumber;
    }
    public List<String> getBookings(){
        if(bookings == null){
            return new ArrayList<String>();
        }
        return bookings;
    }
    
    public String toString() {
        return name + "\nemail\t:" + email + "\nage\t:" + String.valueOf(age) + "\nmobile\t:" + mobileNumber + "\n";
    }
    // end of basic methods


    public void save(){
        DataManager.ManageUser(this,true);
    }
    
    //to verify the attributes
    public static void main(String args[]){
        User existing = new User("John", 19, "12345654", "john0002@gmail.com", null);

        System.out.println("Existing user: \n" + existing.age);
        System.out.println(existing.name);
        System.out.println(existing.email);
        System.out.println(existing.mobileNumber);
        System.out.println(existing.bookings);
    }
}