package Controller;

import Entity.Booking;
import Entity.Cinema;
import Entity.User;
import View.Boundary;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

/**
 Represents the BookingManager class, to manage all booking objects
 @author CZ2002 Group 1
 @version 1.0
 @since 15-11-2019
 */
public class BookingManager {


    /**
     * ArrayList of all Booking objects in database
     */
    private static ArrayList<Booking> bookingArrayList;
    private User user;
    private Cinema cinema;


    /**
     * Creates a new BookingManager object with the details of every booking in the database
     * @param user
     * @param cinema
     */


    public BookingManager(User user,Cinema cinema){

        bookingArrayList = DataManager.loadBookings();
        this.user=user;
        this.cinema=cinema;
    }

    /**
     * Generates a Booking object with details from input User and Cinema object, and seat number
     * @param seatNO seat chosen in Cinema object
     * @return Booking object
     */
    private  Booking generateBooking(int seatNO){
        String bookingID = genBookingID();
        LocalDateTime dateTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        String bookingTime = dateTime.format(formatter);
        double final_price = calculatePrice();
        return new Booking(cinema, user, seatNO, bookingID, bookingTime, final_price);
    }


    /**
     * Creates a Booking object with details from input User and Cinema object, and seat number. Then saves details of booking object in database if booking is confirmed by user. Does not save if movie showtime has passed or seat is taken.
     * @param seatNO seat chosen in Cinema object
     * @return True if booking is confirmed and details are successfully saved in database, False otherwise
     */
    public Boolean createBooking(int seatNO){

        if(!dateCheck(cinema.getTime())){
            System.out.println("The Movie has Already Passed");
            return false;
        }


        Booking booking=this.generateBooking(seatNO);
        Boundary.displayBookings(Arrays.asList(booking));
        System.out.println("Press 1 to confirm Booking  0 to Cancel");

        Scanner input=new Scanner(System.in);
        int x=input.nextInt();

        if(x==1)
            if(this.saveBooking(booking)){
                System.out.println("Booking Created");
                return true;
            }
            else
                System.out.println("Seat Already Taken");

        return false;
    }


    /**
     * Saves details of input Booking object into database and updates seats in input cinema object. Also checks if seat of corresponding Cinema is taken.
     * @param booking Booking object
     * @return True if details of Booking object are successfully saved in database and Cinema object seats is updated , False if seat chosen from Booking object is unavailable
     */
    private Boolean saveBooking(Booking booking){

        if(!seatCheck(cinema.getSeats(),booking.getSeatNO()))
            return false;

        if(DataManager.addBooking(booking)){
            cinema.addSeats(booking.getSeatNO());
            if(DataManager.updateShowTime(cinema,cinema))
                return true;
        }
        return false;
    }


    /**
     * Calculates and returns the price of a movie booking from input User and Cinema object
     * @return price of movie booking
     */
    private double calculatePrice(){
        PriceManager priceManager = new PriceManager(user.getCustomerType(),cinema);
        return  priceManager.getPrice();
    }

    /**
     * Generates a unique random number between 10000(inclusive) to 40000(exclusive) as booking id
     * @return booking id
     */
    private static String genBookingID(){
        int id;
        while(true){
            Random rand = new Random( System.currentTimeMillis() );
            id=(1 + rand.nextInt(2)) * 10000 + rand.nextInt(10000);
            if(bookingCheck(id))
                break;
        }
        return Integer.toString(id);
    }

    /**
     * Checks if the id is unique by comparing it with the past booking ids
     * @param id booking id
     * @return True if the id is unique, False otherwise
     */
    private static Boolean bookingCheck(int id){
        for(Booking booking:bookingArrayList){
            if(booking.getBookingID().equals(String.valueOf(id)))
                return false;
        }
        return true;
    }

    /**
     * Checks if the the date of booking is after current date
     * @param date date to be checked
     * @return True if the date of booking is after current date, False otherwise
     */
    protected static Boolean dateCheck(String date){
        SimpleDateFormat dfParse = new SimpleDateFormat("dd/MM/yyyy");
        Date ShowDate;
        Date currentDate;
        try {
           ShowDate=dfParse.parse(date);
           currentDate=dfParse.parse(dfParse.format(new Date()));
            int result=currentDate.compareTo(ShowDate);

            if(result==1)
                return false;

        } catch (ParseException e) {
            e.printStackTrace();
        }

        return true;

    }

    /**
     * Checks if input integer curSeat is in a list of integers denoting seats taken
     * @param seats List of integer values
     * @param curSeat integer value denoting seat number
     * @return True if List does not contain integer value of curSeat, False otherwise
     */
    private static boolean seatCheck(List<Integer> seats,int curSeat){
        if(seats.contains(curSeat))
            return false;
        return true;

    }


    /**
     * This function calculates Sales for each movie, it takes booking records as input, and returns a HashMap of MovieID, Sales
     * @return HashMap of MovieID, Sales
     */
    protected static HashMap<Integer,Integer> calculateSales(){
        ArrayList<Booking> bookingArrayList=DataManager.loadBookings();
        HashMap<Integer,Integer> map = new HashMap<Integer, Integer>();
        for(int i = 0;i<bookingArrayList.size();i++){
            int movie_id = bookingArrayList.get(i).getMovieID();
            int count = map.containsKey(movie_id)? map.get(movie_id):0;
            map.put(movie_id, count+1);
        }
        return map;
    }


}
