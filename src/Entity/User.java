package Entity;

import java.util.ArrayList;

public class User extends Person{
    int age;
    String name;
    String email; 
    String mobileNumber;
//    ArrayList<Booking> bookings;

    //basic methods
    public User(int age, String name, String email, String mobileNumber){
        this.age = age;
        this.name = name;
        this.email = email;
        this.mobileNumber = mobileNumber;
//        bookings = new ArrayList<Booking>();
    }
    void setAge(int age){
        this.age = age;
    }
    void setName(String name) {
        this.name = name;
    }
    void setEmail(String email){
        this.email = email;
    }
    void setmobileNumber(String mobileNumber){
        this.mobileNumber = mobileNumber;
    }
    int getAge(){
        return age;
    }
    String getName() {
        return name;
    }
    String getEmail(){
        return email;
    }
    String getmobileNumber(){
        return mobileNumber;
    }
    
    public String toString() {
        return "email\t:" + email + "\nage\t:" + String.valueOf(age) + "\nmobile\t:" + mobileNumber + "\n";
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

    void save(){
        //Database.save(ID, age, email, mobileNumber, bookings);
    }
    */
}