package Entity;

import java.util.List;

public class Cinema {

    int cinplexID,cinemaID,movieID;
    String time;
    List<Integer> seats;

    public Cinema(int cinplexID, int cinemaID, int movieID, String time, List<Integer> seats) {
        this.cinplexID = cinplexID;
        this.cinemaID = cinemaID;
        this.movieID = movieID;
        this.time = time;
        this.seats = seats;
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

    public List<Integer> getSeats() {
        return seats;
    }

    public void setSeats(List<Integer> seats) {
        this.seats = seats;
    }
}
