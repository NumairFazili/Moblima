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
import Entity.Movie;
import Entity.User;
import View.Boundary;
//import org.junit.Test;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class BookingManager {

    private static HashSet<String> bookingIDset = new HashSet<String>() ;
    MovieManager movieManager;

    public BookingManager(){
       movieManager  = new MovieManager();
    }

    public static void init(){
        ArrayList<Booking> bookings = DataManager.LoadBookings();
        for(int i = 0;i<bookings.size();i++){
            if(bookingIDset.contains(bookings.get(i).getBookingID())==false){
                bookingIDset.add(bookings.get(i).getBookingID());
            }
        }
    }

    public  Booking generateBooking(User user, Cinema cinema, int seatNO){
        String bookingID = genBookingID();
        LocalDateTime dateTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        String bookingTime = dateTime.format(formatter);
        double final_price = calc_price(user,cinema);
        return new Booking(bookingID, cinema.getCinplexID(), cinema.getCinemaID(), cinema.getMovieID(), cinema.getTime(), cinema.getCinemaClass(), cinema.getMovieType(), user.getName(), user.getmobileNumber(), user.getEmail(), user.getCustomerType(), seatNO, bookingTime, final_price);
    }


    public Boolean createBooking(User user,Cinema cinema, int seatNO){


        Movie m = movieManager.selectMovieByID(cinema.getMovieID());

        if(m.getStatus().equals("End of Show")){
            System.out.println("The movie has Ended");
            return false;
        }

        BookingManager.init();
        Booking booking=this.generateBooking(user, cinema, seatNO);
        Boundary.DisplayBookings(Arrays.asList(booking));
        System.out.println("Press 1 to confirm Booking  0 to Cancel");

        Scanner input=new Scanner(System.in);
        int x=input.nextInt();

        if(x==1)
            if(BookingManager.saveBooking(booking,cinema)){
                System.out.println("Booking Created");
                return true;
            }
            else
                System.out.println("Seat Already Taken");

        return false;
    }




    public static Boolean saveBooking(Booking booking,Cinema cinema){

        if(!SeatCheck(cinema.getSeats(),booking.getSeatNO()))
            return false;

        DataManager.AddBooking(booking);
        cinema.addSeats(booking.getSeatNO());
        DataManager.UpdateShowTime(cinema,false);
        return true;
    }


    private static double calc_price(User user,Cinema cinema){
        priceManager priceManager = new priceManager(user.getAge(),cinema);
        return  priceManager.getPrice();
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

    private static boolean SeatCheck(List<Integer> seats,int curSeat){

        if(seats.contains(curSeat))
            return false;
        return true;

    }





}
