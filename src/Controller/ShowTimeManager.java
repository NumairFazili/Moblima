package Controller;

import Entity.Cinema;

import java.util.ArrayList;
import java.util.List;

public class ShowTimeManager {

    public List<Cinema> getAllShowTimes(){
        return DataManager.LoadShowTimes(-1);

    }

    public List<Cinema> getShowTimesByCineplex(int cineplexID){
        List<Cinema> c_list = getAllShowTimes();
        List<Cinema> return_list = new ArrayList<Cinema>();
        for(Cinema c: c_list){
            if(c.getCinplexID() == cineplexID){
                return_list.add(c);
            }
        }
        return return_list;
    }
    public  List<Cinema> getShowTimesByMovie(int movieID){
        return DataManager.LoadShowTimes(movieID);

    }




}
