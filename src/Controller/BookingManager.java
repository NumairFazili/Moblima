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

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class BookingManager {

    private static ArrayList<Booking> bookingArrayList;
    MovieManager movieManager;

    public BookingManager(){
       movieManager  = new MovieManager();
        bookingArrayList = DataManager.LoadBookings();
    }

    public  Booking generateBooking(User user, Cinema cinema, int seatNO){
        String bookingID = genBookingID();
        LocalDateTime dateTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        String bookingTime = dateTime.format(formatter);
        double final_price = calculatePrice(user,cinema);
        return new Booking(bookingID, cinema.getCinplexID(), cinema.getCinemaID(), cinema.getMovieID(), cinema.getTime(), cinema.getCinemaClass(), cinema.getMovieType(), user.getName(), user.getmobileNumber(), user.getEmail(), user.getCustomerType(), seatNO, bookingTime, final_price);
    }


    public Boolean createBooking(User user,Cinema cinema, int seatNO){


        Movie m = movieManager.selectMovieByID(cinema.getMovieID());

        if(m.getStatus().equals("End of Show")){
            System.out.println("The movie has Ended");
            return false;
        }

        Booking booking=this.generateBooking(user, cinema, seatNO);
        Boundary.DisplayBookings(Arrays.asList(booking));
        System.out.println("Press 1 to confirm Booking  0 to Cancel");

        Scanner input=new Scanner(System.in);
        int x=input.nextInt();

        if(x==1)
            if(this.saveBooking(booking,cinema)){
                System.out.println("Booking Created");
                return true;
            }
            else
                System.out.println("Seat Already Taken");

        return false;
    }


    public Boolean saveBooking(Booking booking,Cinema cinema){

        if(!SeatCheck(cinema.getSeats(),booking.getSeatNO()))
            return false;

        DataManager.AddBooking(booking);
        cinema.addSeats(booking.getSeatNO());
        DataManager.UpdateShowTime(cinema,false);
        return true;
    }


    private static double calculatePrice(User user, Cinema cinema){
        priceManager priceManager = new priceManager(user.getAge(),cinema);
        return  priceManager.getPrice();
    }
    private static String genBookingID(){
        int id;
        while(true){
            Random rand = new Random( System.currentTimeMillis() );
            id=(1 + rand.nextInt(2)) * 10000 + rand.nextInt(10000);
            if(BookingCheck(id))
                break;
        }
        return Integer.toString(id);
    }

    private static Boolean BookingCheck(int id){
        for(Booking booking:bookingArrayList)
            if(!booking.getBookingID().equals(String.valueOf(id)))
                return true;
            return false;
    }

    private static boolean SeatCheck(List<Integer> seats,int curSeat){

        if(seats.contains(curSeat))
            return false;
        return true;

    }





}
