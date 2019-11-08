/*
Author: Bingyan
Constructor:
1.BookingManager

Methods:
1.makeBooking
2.createBooking
3.genBookingID

Helpers:
 */
package Controller;

import Entity.Booking;
import Entity.Cinema;
import Entity.User;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Random;
import java.util.Set;

public class BookingManager {
 
    // Initialize booking Manager class by BookingManager(DataManager.LoadBookings());
    private static Set<String> bookingIDset;

    public BookingManager(){
        ArrayList<Booking> bookings = DataManager.LoadBookings();
        for(int i = 0;i<bookings.size();i++){
            if(bookingIDset.contains(bookings.get(i).getBookingID())==false){
                bookingIDset.add(bookings.get(i).getBookingID());
            }
        }
    }

    // private static void makeBooking(User user, Booking booking){
    //     DataManager.AddBooking(booking);
    // }
 
    public static Booking createBooking(User user, Cinema cinema, int seatNO){
        //Cinema class need to be added into Cinema
        //movieType need to be added into Movie

        //showtime, movieclass, price removed from parameters

        String bookingID = genBookingID();
        String customer_temp;

        if(user.getAge()>=65){
            customer_temp = "senior";
        }
        else if(user.getAge()<15){
            customer_temp = "child";
        }else{
            customer_temp="adult";
        }
        //missing customer type logic for student

        LocalDateTime dateTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM HH::mm");
        String bookingTime = dateTime.format(formatter);
        int final_price = calc_price();
        Booking newBooking = new Booking(bookingID, cinema.getCinplexID(), cinema.getCinemaID(), cinema.getMovieID(), cinema.getTime(), cinema.getCinemaClass(), cinema.getMovieType(), user.getName(), user.getmobileNumber(), user.getEmail(), customer_temp, seatNO, bookingTime, final_price);

        DataManager.AddBooking(newBooking);
        cinema.addSeats(seatNO);
        DataManager.UpdateShowTime(cinema);
        return newBooking;
    }

    private static int calc_price(){
        return 1;
    }
    private static String genBookingID(){
        Random rand = new Random();
        int newID;
        while(true){
            newID = rand.nextInt(99999);
            if(bookingIDset.contains(Integer.toString(newID))==false){
                break;
            }
        }
        return Integer.toString(newID);
    }
}
