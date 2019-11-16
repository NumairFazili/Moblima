package Entity;

import java.util.List;

/**
 Represents the cinema class, a cinema can hold multiple movies
 @author CZ2002 Group 1
 @version 1.0
 @since 15-11-2019
 */
public class Cinema {

    /**
     * ID parameters for Cineplex, Cinema and Movie
     */
    int cinplexID,cinemaID,movieID;

    /**
     * Showtime of movie in cinema and cinema class(Silver, Gold, Platinum)
     */
    String time,cinemaClass;

    /**
     * List of integer values denoting the seats taken for each cinema object
     */
    List<Integer> seats;

    /**
     * Type of movie(2D or 3D) which is tagged to the cinema object
     */
    String movieType;

    /**
     * Creates a new Cinema object with the following parameters
     * @param cinplexID Cineplex ID of Cinema object
     * @param cinemaID Cinema ID of Cinema object
     * @param movieID Movie ID of Cinema object
     * @param time time of Cinema object
     * @param cinemaClass Cinema class of Cinema object
     * @param seats Seats taken of Cinema object
     * @param movieType Movie type of Cinema object
     */
    public Cinema(int cinplexID, int cinemaID, int movieID, String time,String cinemaClass, List<Integer> seats, String movieType) {
        this.cinplexID = cinplexID;
        this.cinemaID = cinemaID;
        this.movieID = movieID;
        this.time = time;
        this.cinemaClass=cinemaClass;
        this.seats = seats;
        this.movieType = movieType;        
    }

    /**
     * Get Cinema details in a String
     * @return Cinema details in a single String
     */
    @Override
    public String toString(){
        String s = this.getCinplexID() + "\t" + this.getCinemaID() + "\t" + this.getTime() + "\t" + "\t" +this.getCinemaClass() + "\t" + this.getMovieType();
        return s;
    }

    /**
     * Get Cineplex ID
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
     * Get Cinema ID
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
     * Get Movie ID
     * @return Movie ID
     */
    public int getMovieID() {
        return movieID;
    }

    /**
     * Change Movie ID
     * @param movieID Movie ID
     */
    public void setMovieID(int movieID) {
        this.movieID = movieID;
    }

    /**
     * Get showtime
     * @return showtime
     */
    public String getTime() {
        return time;
    }

    /**
     * Change showtime
     * @param time showtime
     */
    public void setTime(String time) {
        this.time = time;
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
     * Get seats taken
     * @return List of integers denoting taken seats
     */
    public List<Integer> getSeats() {
        return seats;
    }

    /**
     * Change seats taken
     * @param seats List of integer values denoting seats taken
     */
    public void setSeats(List<Integer> seats) {
        this.seats = seats;
    }

    /**
     * Add taken seat
     * @param a taken seat of cinema object
     */
    public void addSeats(Integer a){
        this.seats.add(a);
    }

    /**
     * Get Movie type
     * @return Movie type
     */
    public String getMovieType(){
        return movieType;
    }

    /**
     * Change Movie type
     * @param movieType Movie type
     */
    public void setMovieType(String movieType){
        this.movieType = movieType;
    }

}
