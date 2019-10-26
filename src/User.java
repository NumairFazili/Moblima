import java.util.ArrayList;

public class User extends Person{
    int age;
    String email; 
    String phoneNumber;
//    ArrayList<Booking> bookings;

    //basic methods
    public User(int age, String email, String phoneNumber){
        this.age = age;
        this.email = email;
        this.phoneNumber = phoneNumber;
//        bookings = new ArrayList<Booking>();
    }
    void setAge(int age){
        this.age = age;
    }
    void setEmail(String email){
        this.email = email;
    }
    void setPhoneNumber(String phoneNumber){
        this.phoneNumber = phoneNumber;
    }
    int getAge(){
        return age;
    }
    String getEmail(){
        return email;
    }
    String getPhoneNumber(){
        return phoneNumber;
    }
    
    public String toString() {
        return name + "\nage\t:" + String.valueOf(age) + "\nemail\t:" + email + "\nphone\t:" + phoneNumber + "\n";
    }
    // end of basic methods
    
    /* MAY CONTAIN ERROR -- CAN'T TEST
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
        m.addReview(name, content, rating);
    }

    void save(){
        //Database.save(ID, name, age, email, phoneNumber, bookings);
    }
    */
}