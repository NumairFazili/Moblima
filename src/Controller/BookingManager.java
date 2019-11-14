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
//import org.junit.Test;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class BookingManager {
 
    // Initialize booking Manager class by BookingManager(DataManager.LoadBookings());
    private static HashSet<String> bookingIDset = new HashSet<String>() ;

    public static void init(){
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
    /*@Test
    public void test1(){
        init();
        genBookingID();
    }*/

    public static Booking createBooking(User user, Cinema cinema, int seatNO){
        //Cinema class need to be added into Cinema
        //movieType need to be added into Movie

        //showtime, movieclass, price removed from parameters



        String bookingID = genBookingID();

        LocalDateTime dateTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH::mm");
        String bookingTime = dateTime.format(formatter);
        double final_price = calc_price(user,cinema);
        return new Booking(bookingID, cinema.getCinplexID(), cinema.getCinemaID(), cinema.getMovieID(), cinema.getTime(), cinema.getCinemaClass(), cinema.getMovieType(), user.getName(), user.getmobileNumber(), user.getEmail(), user.getCustomerType(), seatNO, bookingTime, final_price);
    }

    public static void saveBooking(Booking booking,Cinema cinema){
        DataManager.AddBooking(booking);
        cinema.addSeats(booking.getSeatNO());
        DataManager.UpdateShowTime(cinema,false);
    }


    private static double calc_price(User user,Cinema cinema){
        priceManager calc_price = new priceManager(user.getAge(),cinema.getCinemaClass());
        return  calc_price.getPrice();
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
