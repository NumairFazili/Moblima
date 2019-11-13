package Controller;

import java.util.*;

import Entity.*;
import View.Boundary;
import View.UserBoundary;

import javax.xml.crypto.Data;

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
    public UserManager(String name, int age, String mobileNumber, String email){
        User u = new User(name, age, mobileNumber, email, Arrays.asList());
        u.save();
        user = u;
    }


    public void createBooking(Cinema cinema, int seatNO){
        Movie m = super.selectMovieByID(cinema.getMovieID());
        BookingManager.init();

        try{
            if(user.getAge() >= m.getMinAge()){
                Booking b = BookingManager.createBooking(user, cinema, seatNO);
                user.addBooking(b);
                user.save();    
            }else{
                System.out.println("Minimum age requirement not reached.");
            }    
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    public List<Booking> getBookings(){
        List<String> bID_list = user.getBookings();     //no need null check 
        ArrayList<Booking> all_b = DataManager.LoadBookings();
        ArrayList<Booking> bookings = new ArrayList<Booking>();
        for(String bID: bID_list){
            for(Booking b: all_b){
                if (b.getBookingID().equals(bID)){
                    bookings.add(b);
                    break;
                }
            }
        }
        if(bookings.size() != bID_list.size()){
            System.out.println("some bookings not found");
        }
        return bookings;
    }

    public void reviewMovie(Movie m, int rating, String review){
        m.addRating(rating);
        m.addReview(review);    
    }

    public static void main(String args[]){
        UserManager um = new UserManager("James", 18, "jammy@gmail.com", "13572468");
        System.out.println(um.user.getBookings());
    }

    public void SearchListMovie(Scanner input, int choice){
        UserBoundary.SearchListMovie(input, choice, this);
    }
    
}