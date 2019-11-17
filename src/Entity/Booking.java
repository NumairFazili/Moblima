package Entity;

import java.util.List;

import Controller.DataManager;

/**
 Represents the booking object when a movie-goer books a seat for a particular showtime in a Cinema.
 @author CZ2002 Group 1
 @version 1.0
 @since 15-11-2019
 */
public class Booking {

    /**
     * Reference to a Cinema object
     */
    Cinema cinema;

    /**
     * Reference to a User object
     */
    User user;

    /**
     * ID parameters for seat number of booking
     */
    private int seatNO;

    /**
     * Price of booking
     */
    private double price;

    /**
     * Booking ID, time of booking
     */
    private String bookingID, bookingTime;

    /**
     * Creates a new booking object with the following parameters (from database)
     * @param bookingID booking ID
     * @param mobileNumber mobile number of customer
     * @param cinplexID Cineplex ID of booking
     * @param cinemaID Cinema ID of booking
     * @param movieID Movie ID of booking
     * @param seatNO Seat number of booking
     * @param price price of booking
     * @param showTime showtime for cinema of booking
     * @param bookingTime time that booking was made
     * @param customerName customer name
     * @param customerType customer type
     * @param cinemaClass cinema class of booking
     * @param movieType movie type of booking
     * @param email customer email of booking
     */
    public Booking(String bookingID,int cinplexID,int cinemaID,int movieID,String showTime,String cinemaClass,String movieType,String customerName,String mobileNumber,String email,String customerType,int seatNO,String bookingTime,double price) {
        this.cinema = getCinema(movieID, cinplexID, cinemaID, showTime);
        this.user = getUser(customerName, mobileNumber, email);
        this.bookingID = bookingID;
        this.seatNO = seatNO;
        this.price = price;
        this.bookingTime = bookingTime;
        // if(this.cinema == null){
        //     throw new IllegalArgumentException("No matching showtime");
        // }
        // if(this.user == null){
        //     throw new IllegalArgumentException("No matching user");
        // }
    }

    /**
     * Creates a new booking object with the following parameters
     * @param cinema Cinema object to be booked
     * @param user User object that made the booking
     * @param bookingID booking ID
     * @param price price of booking
     * @param bookingTime time that booking was made
     */
    public Booking(Cinema cinema, User user, int seatNO, String bookingID, String bookingTime, double price){
        this.cinema = cinema;
        this.user = user;
        this.bookingID = bookingID;
        this.price = price;
        this.bookingTime = bookingTime;
        if(this.user == null){
            throw new IllegalArgumentException("No user provided");
        }
        if(this.cinema == null){
            throw new IllegalArgumentException("No showtime provided");
        }
    }

    /**
     * Get the Cinema object that matches with the parameters
     * @param movieID Movie ID of booking
     * @param cinplexID Cineplex ID of booking
     * @param cinemaID Cinema ID of booking
     * @param showTime showtime for cinema of booking
     * @return Cinema object (or null if not found)
     */
    private Cinema getCinema(int movieID, int cinplexID, int cinemaID, String showTime){
        List<Cinema> c_list = DataManager.loadShowTimes(movieID);
        for(Cinema c: c_list){
            if (c.getCinplexID() == cinplexID && c.getCinemaID() == cinemaID && c.getTime().equals(showTime)){
                return c;
            }
        }
        return null;
    }

    /**
     * Get the User object that matches with the parameters
     * @param customerName customer name
     * @param mobileNumber mobile number of customer
     * @param email customer email of booking
     * @return User object (or null if not found)
     */
    private User getUser(String customerName,String mobileNumber,String email){
        List<User> u_list = DataManager.loadUser();
        for(User u: u_list){
            if(u.getName().equals(customerName) && u.getmobileNumber().equals(mobileNumber) && u.getEmail().equals(email)){
                return u;
            }
        }
        return null;
    }

    /**
     * Get Booking ID
     * @return Booking ID
     */
    public String getBookingID() {
        return bookingID;
    }

    /**
     * Change Booking ID
     * @param bookingID Booking ID
     */
    public void setBookingID(String bookingID) {
        this.bookingID = bookingID;
    }

    /**
     * Get Cineplex ID of booking
     * @return Cineplex ID
     */
    public int getCinplexID() {
        return cinema.getCinplexID();
    }

    /**
     * Get Cinema ID of booking
     * @return Cinema ID
     */
    public int getCinemaID() {
        return cinema.getCinemaID();
    }

    /**
     * Get movie ID of booking
     * @return movie ID
     */
    public int getMovieID() {
        return cinema.getMovieID();
    }

    /**
     * Get seat number of booking
     * @return seat number
     */
    public int getSeatNO() {
        return seatNO;
    }

    /**
     * Change seat number of booking
     * @param seatNO seat number
     */
    public void setSeatNO(int seatNO) {
        this.seatNO = seatNO;
    }

    /**
     * Get price of booking
     * @return price
     */
    public double getPrice() {
        return price;
    }

    /**
     * Get showtime for cinema of booking
     * @return showtime
     */
    public String getShowTime() {
        return cinema.getTime();
    }

    /**
     * Get time that booking was made
     * @return time
     */
    public String getBookingTime() {
        return bookingTime;
    }

    /**
     * Change time booking was made
     * @param bookingTime booking time
     */
    public void setBookingTime(String bookingTime) {
        this.bookingTime = bookingTime;
    }

    /**
     * Get Cinema class
     * @return Cinema class
     */
    public String getCinemaClass() {
        return cinema.getCinemaClass();
    }

    /**
     * Get movie type
     * @return movie type
     */
    public String getMovieType() {
        return cinema.getMovieType();
    }

    /**
     * Get customer type
     * @return customer type
     */
    public String getCustomerType() {
        return user.getCustomerType();
    }

    /**
     * Get customer name
     * @return customer name
     */
    public String getCustomerName() {
        return user.getName();
    }

    /**
     * Change name of customer
     * @param customerName name of customer
     */
    public void setCustomerName(String customerName) {
        this.user.setName(customerName);
    }

    /**
     * Get customer email
     * @return customer email
     */
    public String getEmail() {
        return user.getEmail();
    }

    /**
     * Change email of customer
     * @param email email of customer
     */
    public void setEmail(String email) {
        this.user.setEmail(email);
    }

    /**
     * Get mobile number of customer
     * @return mobile number of customer
     */
    public String getMobileNumber() {
        return user.getmobileNumber();
    }

    /**
     * Change customer mobile number
     * @param mobileNumber customer mobile number
     */
    public void setMobileNumber(String mobileNumber) {
        this.user.setmobileNumber(mobileNumber);
    }

}
