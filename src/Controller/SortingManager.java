package Controller;

import Entity.Movie;

import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 Represents the SortingManager class to handle sorting
 @author CZ2002 Group 1
 @version 1.0
 @since 15-11-2019
 */

public class SortingManager{


    /**
     * Sorts a HashMap by its value, in descending order. This function is used in calculate top 5 sales.
     * @param hm HashMap of <MovieID, Sales>
     * @return HashMap of sorted hm
     */
    public static HashMap<Integer, Integer> sortByValue(HashMap<Integer, Integer> hm)
    {
        // Create a list from elements of HashMap
        List<Map.Entry<Integer, Integer> > list =
                new LinkedList<Map.Entry<Integer, Integer> >(hm.entrySet());

        // Sort the list
        Collections.sort(list, new Comparator<Map.Entry<Integer, Integer> >() {
            public int compare(Map.Entry<Integer, Integer> o1,
                               Map.Entry<Integer, Integer> o2)
            {
                return -(o1.getValue()).compareTo(o2.getValue());
            }
        });

        // put data from sorted list to hashmap
        HashMap<Integer, Integer> temp = new LinkedHashMap<Integer, Integer>();
        for (Map.Entry<Integer, Integer> aa : list) {
            temp.put(aa.getKey(), aa.getValue());
        }
        return temp;
    }

    

}
/**
 * Sort helpers
 @author CZ2002 Group 1
 @version 1.0
 @since 15-11-2019
 */
class SortByName implements Comparator<Movie> {
    @Override
    public int compare(Movie o1, Movie o2){
        return o1.getName().compareTo(o2.getName());
    }

}
/**
 * Sort helpers
 @author CZ2002 Group 1
 @version 1.0
 @since 15-11-2019
 */
class SortByRating implements Comparator<Movie>{
    @Override
    public int compare( Movie o1, Movie o2){

        if(MovieManager.getAvgRating(o1)<MovieManager.getAvgRating(o2)){
            return 1;
        }
        else if(MovieManager.getAvgRating(o1) == MovieManager.getAvgRating(o2)){
            return 0;
        }
        else{
            return -1;
        }
    }
}
