package Controller;

import Entity.Cinema;
import java.util.List;

/**
 Represents the ShowTimeManager object to manage showtimes, to be used by Staff object
 @author CZ2002 Group 1
 @version 1.0
 @since 15-11-2019
 */
public  class ShowTimeManager {



    /**
     * Get all showtimes in the database
     * @return A list of Cinema objects
     */
    public static List<Cinema> getAllShowTimes(){
        return DataManager.LoadShowTimes(-1);

        /**
         * Get all showtimes in the database which corresponds to input movie ID
         * @param movieID movie ID
         * @return A list of Cinema objects
         */
    }
    public static List<Cinema> getShowTimesByMovie(int movieID){
        return DataManager.LoadShowTimes(movieID);

    }


}
