import java.util.ArrayList;

public class Cinema {
    int ROW_OF_SEATS = 10;
    int COL_OF_SEATS = 10;
    ArrayList<MovieShow> upComingMovies;
    CinePlex location;
    String type;
    public Cinema(){

    }

    public ArrayList<MovieShow> get_ComingMovies(){
        return upComingMovies;
    }
    public void printMovies(){
        System.out.println();
    }
}
