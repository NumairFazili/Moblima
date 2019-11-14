package Controller;

import Entity.Cinema;
import java.util.List;

public class ShowTimeManager {

    public List<Cinema> getAllShowTimes(){
        return DataManager.LoadShowTimes(-1);

    }
    public  List<Cinema> getShowTimesByMovie(int movieID){
        return DataManager.LoadShowTimes(movieID);

    }




}
