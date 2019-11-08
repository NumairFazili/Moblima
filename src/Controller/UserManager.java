package Controller;

import java.util.ArrayList;
import java.util.List;

import Entity.*;

/*
    createBookings
    getBookings
*/

public class UserManager extends PersonManager{

    User user;

    //for existing user
    public UserManager(User u){
        user = u;
        if(user == null){
            throw new IllegalArgumentException("UserManager must have a user");
        }
    }

    //for new user
    public UserManager(String name, int age, String email, String mobileNumber){
        User u = new User(name, age, mobileNumber, email, null);
        u.save();
        user = u;
    }


    public void createBooking(Cinema cinema, String showTime, String cinemaClass, String movieType, int seatNO, int price){
        Movie m = super.selectMovieByID(cinema.getMovieID());
        if(user.getAge() >= m.getMinAge()){
            Booking b = BookingManager.createBooking(user, cinema, showTime, cinemaClass, movieType, seatNO, price);
            user.addBooking(b);
            user.save();    
        }else{
            System.out.println("Minimum age requirement not reached.");
        }
    }

    public List<Booking> getBookings(){
        List<Long> b_list = user.getBookings();
        ArrayList<Booking> all_b = DataManager.LoadBookings();
        ArrayList<Booking> bookings = new ArrayList<Booking>();
        for(int i = 0; i < b_list.size(); i++){
            for(int j = 0; j < all_b.size(); j++){
                if (all_b.get(j).getBookingID() == b_list.get(i)){
                    bookings.add(all_b.get(j));
                    break;
                }
            }
        }
        if(bookings.size() != b_list.size()){
            System.out.println("some bookings not found");
        }
        return bookings;
    }

    public void reviewMovie(Movie m, double rating, String review){
        m.addRating(rating);
        m.addReview(review);
    }

    public static void main(String args[]){
        UserManager um = new UserManager("James", 18, "jammy@gmail.com", "13572468");
        System.out.println(um.user.getBookings());
    }
    
}