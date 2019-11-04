package Entity;

import java.util.ArrayList;

import Controller.DataManager;

public class User extends Person{
    int age;
    String email; 
    String mobileNumber;
//    ArrayList<Booking> bookings;

    //basic methods

    //for creating guest user -- only have ID and name like Person201911040200, set attributes afterwards
    public User(){}

    //for DataManager to re-create existing user
    public User(String name, int age, String email, String mobileNumber){
        this.age = age;
        setName_User(name);
        this.email = email;
        this.mobileNumber = mobileNumber;
//        bookings = new ArrayList<Booking>();
    }

    //setters - used when make booking
    void setName_User(String name){
        this.setName(name);     //using base class function
    }
    void setAge(int age){
        this.age = age;
    }
    void setEmail(String email){
        this.email = email;
    }
    void setmobileNumber(String mobileNumber){
        this.mobileNumber = mobileNumber;
    }
    //
    public void setAttributes(String name, int age, String email, String mobileNumber){
        setName(name);
        setAge(age);
        setEmail(email);
        setmobileNumber(mobileNumber);
    }
    //getters
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
    
    public String toString() {
        return name + "\nemail\t:" + email + "\nage\t:" + String.valueOf(age) + "\nmobile\t:" + mobileNumber + "\n";
    }
    // end of basic methods

    /*
    // MAY CONTAIN ERROR -- CAN'T TEST
    void makeBooking(MovieShow m, int seatID){
        Booking b = new Booking(m, seatID);
        bookings.add(b);
    }

    void cancelBooking(Booking b){
        for(int i = bookings.size()-1; i >= 0; i--){
            if(bookings.get(i) == b){
                bookings.remove(i);
                break;
            }
        }
    }

    void viewBookingHistory(){
        for(int i = 0; i < bookings.size(); i++){
            System.out.print(bookings.get(i));
            //for this to work Booking object should have a toString method
            //not using View class for now
        }
    }

    void doReview(Movie m, String content, int rating){
        m.addReview(content, rating);
    }
    */

    public void save(){
        DataManager.addUser(this);
    }
    
    //to verify the attributes
    public static void main(String args[]){
        User guest = new User();
        User existing = new User("John", 19, "john0002@gmail.com", "12345654");
        
        System.out.println("Guest user: \n" + guest.ID);
        System.out.println(guest.name);

        System.out.println("Existing user: \n" + existing.age);
        System.out.println(existing.name);
        System.out.println(existing.email);
        System.out.println(existing.mobileNumber);
    }
}