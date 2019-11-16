package Entity;


/**
 Represents the booking object when a movie-goer books a seat for a particular showtime in a Cinema.
 @author CZ2002 Group 1
 @version 1.0
 @since 15-11-2019
 */
public class Booking {

    /**
     * ID parameters for Cineplex, Cinema and Movie, and seat number of booking
     */
    private int cinplexID,cinemaID,movieID,seatNO;

    /**
     * Price of booking
     */
    private double Price;

    /**
     * Booking ID, cinema showtime, time of booking, customer name, customer type(regular/senior/child), cinema class(silver/gold/platinum), movie type(2D/3D), customer email, customer mobile number.
     */
    private String bookingID, showTime,bookingTime,customerName,customerType,cinemaClass,movieType,email,mobileNumber;

    /**
     * Creates a new booking object with the following parameters
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
        this.bookingID = bookingID;
        this.mobileNumber = mobileNumber;
        this.cinplexID = cinplexID;
        this.cinemaID = cinemaID;
        this.movieID = movieID;
        this.seatNO = seatNO;
        this.Price = price;
        this.showTime = showTime;
        this.bookingTime = bookingTime;
        this.customerName = customerName;
        this.customerType = customerType;
        this.cinemaClass = cinemaClass;
        this.movieType = movieType;
        this.email = email;
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
     * Get mobile number of customer
     * @return mobile number of customer
     */
    public String getMobileNumber() {
        return mobileNumber;
    }

    /**
     * Change customer mobile number
     * @param mobileNumber customer mobile number
     */
    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    /**
     * Get Cineplex ID of booking
     * @return Cineplex ID
     */
    public int getCinplexID() {
        return cinplexID;
    }

    /**
     * Change Cineplex ID
     * @param cinplexID Cineplex ID
     */
    public void setCinplexID(int cinplexID) {
        this.cinplexID = cinplexID;
    }

    /**
     * Get Cinema ID of booking
     * @return Cinema ID
     */
    public int getCinemaID() {
        return cinemaID;
    }

    /**
     * Change Cinema ID
     * @param cinemaID Cinema ID
     */
    public void setCinemaID(int cinemaID) {
        this.cinemaID = cinemaID;
    }

    /**
     * Get movie ID of booking
     * @return movie ID
     */
    public int getMovieID() {
        return movieID;
    }

    /**
     * Change movie ID
     * @param movieID movie ID
     */
    public void setMovieID(int movieID) {
        this.movieID = movieID;
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
        return Price;
    }

    /**
     * Change price of booking
     * @param price price of booking
     */
    public void setPrice(double price) {
        Price = price;
    }

    /**
     * Get showtime for cinema of booking
     * @return showtime
     */
    public String getShowTime() {
        return showTime;
    }

    /**
     * Change showtime for cinema of booking
     * @param showTime showtime for cinema
     */
    public void setShowTime(String showTime) {
        this.showTime = showTime;
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
     * Get customer name
     * @return customer name
     */
    public String getCustomerName() {
        return customerName;
    }

    /**
     * Change name of customer
     * @param customerName name of customer
     */
    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    /**
     * Get customer type
     * @return customer type
     */
    public String getCustomerType() {
        return customerType;
    }

    /**
     * Change customer type
     * @param customerType customer type
     */
    public void setCustomerType(String customerType) {
        this.customerType = customerType;
    }

    /**
     * Get Cinema class
     * @return Cinema class
     */
    public String getCinemaClass() {
        return cinemaClass;
    }

    /**
     * Change Cinema class
     * @param cinemaClass Cinema class
     */
    public void setCinemaClass(String cinemaClass) {
        this.cinemaClass = cinemaClass;
    }

    /**
     * Get movie type
     * @return movie type
     */
    public String getMovieType() {
        return movieType;
    }

    /**
     * Change movie type
     * @param movieType movie type
     */
    public void setMovieType(String movieType) {
        this.movieType = movieType;
    }

    /**
     * Get customer email
     * @return customer email
     */
    public String getEmail() {
        return email;
    }

    /**
     * Change email of customer
     * @param email email of customer
     */
    public void setEmail(String email) {
        this.email = email;
    }


}
