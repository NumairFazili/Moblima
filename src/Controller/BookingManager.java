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

import java.awt.print.Book;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Random;
import java.util.Set;

public class BookingManager {
    //Initialize booking Manager class by BookingManager(DataManager.LoadBookings());
    private static Set<Long> bookingIDset;
    public BookingManager(ArrayList<Booking> bookings){
        for(int i = 0;i<bookings.size();i++){
            if(bookingIDset.contains(bookings.get(i).getBookingID())==false){
                bookingIDset.add(bookings.get(i).getBookingID());
            }
        }
    }

    private static void makeBooking(User user, Booking booking){
        DataManager.AddBooking(booking);

    }
    private static Booking createBooking(User user, Cinema cinema, String showTime, String cinemaClass, String movieType, int seatNO, int price){
        //Cinema class need to be added into Cinema
        //movieType need to be added into Movie

        long bookingID = genBookingID();
        String customer_temp;
        if(user.getAge()>=65){
            customer_temp = "senior";
        }
        else if(user.getAge()<15)
            customer_temp = "child";

        else
            customer_temp="adult";
        //missing customer type logic for student

        LocalDateTime dateTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM HH::mm");
        String bookingTime = dateTime.format(formatter);
        int final_price = calc_price();
        Booking newBooking = new Booking(bookingID, cinema.getCinplexID(), cinema.getCinemaID(), cinema.getMovieID(), cinema.getTime(), cinemaClass, movieType, user.getName(), Long.parseLong(user.getmobileNumber()), user.getEmail(), customer_temp, seatNO, bookingTime, final_price);
        DataManager.AddBooking(newBooking);
        cinema.addSeats(seatNO);

        DataManager.UpdateShowTime(cinema);
        return newBooking;
    }

    private static int calc_price(){
        return 1;
    }
    private static long genBookingID(){
        Random rand = new Random();
        long newID;
        while(true){
            newID = rand.nextInt(99999);
            if(bookingIDset.contains(newID)==false){
                break;
            }
        }
        return newID;
    }
}
