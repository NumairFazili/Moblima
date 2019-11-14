package Controller;

import Entity.Cinema;

import java.util.ArrayList;
import java.util.List;

public class ShowTimeManager {

    public List<Cinema> getAllShowTimes(){
        List<Cinema> c_list = DataManager.LoadShowTimes(-1);
        for(int i = 0; i < c_list.size(); i++){
            if(c_list.get(i).getStatus().equals("Ended")){
                c_list.remove(i);
                i--;
            }
        }
        return c_list;
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
        List<Cinema> c_list = DataManager.LoadShowTimes(movieID);
        for(int i = 0; i < c_list.size(); i++){
            if(c_list.get(i).getStatus().equals("Ended")){
                c_list.remove(i);
                i--;
            }
        }
        return c_list;
    }




}
