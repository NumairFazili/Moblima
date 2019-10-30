import java.lang.reflect.Array;
import java.util.ArrayList;

public class Customer extends Person {
    String searchargs = "Name";
    ArrayList<Booking> booking_history;

    public void SearchMovie(CinePlex[] cinePlexes, String args){
        ArrayList<MovieShow> movieList = new ArrayList<MovieShow>();
        for(int i = 0;i<cinePlexes.length;i++){
            movieList.addAll(cinePlexes[i].getUpcomingMovies());
        }
        switch (args){
            
        }
    }
    public void
    public void SearchMovie(){
        this.SearchMovie(CinePlex[] cinePlexes, String searchargs);

    }
}
