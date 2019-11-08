package Entity;

import java.util.List;

public class Cinema {

    int cinplexID,cinemaID,movieID;
    String time,status,cinemaClass;
    List<Integer> seats;
    String movieType;


    public Cinema(int cinplexID, int cinemaID, int movieID, String time, String status, String cinemaClass, List<Integer> seats, String movieType) {
        this.cinplexID = cinplexID;
        this.cinemaID = cinemaID;
        this.movieID = movieID;
        this.time = time;
        this.status=status;
        this.cinemaClass=cinemaClass;
        this.seats = seats;
        this.movieType = movieType;        
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

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    public String getCinemaClass() {
        return cinemaClass;
    }

    public void setCinemaClass(String cinemaClass) {
        this.cinemaClass = cinemaClass;
    }


    public List<Integer> getSeats() {
        return seats;
    }

    public void setSeats(List<Integer> seats) {
        this.seats = seats;
    }
    public void addSeats(Integer a){
        this.seats.add(a);
    }

    public String getMovieType(){
        return movieType;
    }

    public void setMovieType(String movieType){
        this.movieType = movieType;
    }

}
