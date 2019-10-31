package Entity;

public class Booking {

    private long bookingID,mobileNumber;
    private int cinplexID,cinemaID,movieID,seatNO,Price;
    private String showTime,bookingTime,customerName,customerType,cinemaClass,movieType,email;

    public Booking(long bookingID,int cinplexID,int cinemaID,int movieID,String showTime,String cinemaClass,String movieType,String customerName,long mobileNumber,String email,String customerType,int seatNO,String bookingTime,int price) {
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

    public long getBookingID() {
        return bookingID;
    }

    public void setBookingID(long bookingID) {
        this.bookingID = bookingID;
    }

    public long getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(long mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public int getCinplexID() {
        return cinplexID;
    }

    public void setCinplexID(int cinplexID) {
        this.cinplexID = cinplexID;
    }

    public int getCinemaID() {
        return cinemaID;
    }

    public void setCinemaID(int cinemaID) {
        this.cinemaID = cinemaID;
    }

    public int getMovieID() {
        return movieID;
    }

    public void setMovieID(int movieID) {
        this.movieID = movieID;
    }

    public int getSeatNO() {
        return seatNO;
    }

    public void setSeatNO(int seatNO) {
        this.seatNO = seatNO;
    }

    public int getPrice() {
        return Price;
    }

    public void setPrice(int price) {
        Price = price;
    }

    public String getShowTime() {
        return showTime;
    }

    public void setShowTime(String showTime) {
        this.showTime = showTime;
    }

    public String getBookingTime() {
        return bookingTime;
    }

    public void setBookingTime(String bookingTime) {
        this.bookingTime = bookingTime;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerType() {
        return customerType;
    }

    public void setCustomerType(String customerType) {
        this.customerType = customerType;
    }

    public String getCinemaClass() {
        return cinemaClass;
    }

    public void setCinemaClass(String cinemaClass) {
        this.cinemaClass = cinemaClass;
    }

    public String getMovieType() {
        return movieType;
    }

    public void setMovieType(String movieType) {
        this.movieType = movieType;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
