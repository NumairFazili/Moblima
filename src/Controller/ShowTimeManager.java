package Controller;

import Entity.Cinema;
import java.util.List;

public class ShowTimeManager {

    public static List<Cinema> getAllShowTimes(){
        return DataManager.LoadShowTimes(-1);

    }
    public static List<Cinema> getShowTimesByMovie(int movieID){
        return DataManager.LoadShowTimes(movieID);

    }

}
