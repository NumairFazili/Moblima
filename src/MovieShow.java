
import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.HashMap;
public class MovieShow {
    int movie_id;
    int[] seating;
    Cinema cinema;
    double discounts;
    TimeRange showtime;
    Movie movie;



    public void bookSeating(int seat){
        seating[seat] = 1;

    }
    public void displaySeating(){
        int r = cinema.ROW_OF_SEATS;
        int c = cinema.COL_OF_SEATS;
        int [][] seats = new int[r][c];
        int k = 0;
        for(int i = 0;i<r;i++){
            for(int j = 0;j<c;j++){
                if(seating[k]==1){
                    System.out.print('x');
                }
                else{
                    System.out.print('O');
                }
                System.out.print(' ');
            }
            System.out.println();
        }
    }

}
class SortByTime implements Comparator<MovieShow>{


    @Override
    public int compare(MovieShow o1, MovieShow o2) {
        return o1.showtime.start_time.compareTo(o2.showtime.end_time);
    }
}
class SortByName implements  Comparator<MovieShow>{
    @Override
    public int compare(MovieShow o1, MovieShow o2){
        return o1.movie.name.compareTo(o2.movie.name);
    }
}
class SortByRating implements  Comparator<MovieShow>{
    @Override
    public int compare(MovieShow o1, MovieShow o2){
        return o1.movie.rating.compareTo(o2.movie.rating);
    }
}
