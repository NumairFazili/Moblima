import java.util.ArrayList;
import java.util.Collection;

public class CinePlex {
    Cinema[] cinemas;
    String name;
    String location;
    String default_getMovie_arg = "Name";

    public ArrayList<MovieShow> getUpcomingMovies(){
        ArrayList<MovieShow> movieShows = new ArrayList<MovieShow>();
        for(int i = 0;i<cinemas.length;i++){
            movieShows.addAll(cinemas[i].get_ComingMovies());
        }
        return movieShows;
    }
}
