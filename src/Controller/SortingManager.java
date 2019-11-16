/*
Author: Bingyan
Methods:
1.sortMovies

Helpers:
1.SortByName  2. SortByRating
 */
package Controller;

import Entity.Movie;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class SortingManager{


    public SortingManager(){}


    public static HashMap<Integer, Integer> sortByValue(HashMap<Integer, Integer> hm)
    {
        // Create a list from elements of HashMap
        List<Map.Entry<Integer, Integer> > list = new LinkedList<Map.Entry<Integer, Integer> >(hm.entrySet());

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

    public static ArrayList<Movie> sortByRating(ArrayList<Movie> movies, int size){
        ArrayList<Movie> to_return = new ArrayList<Movie>();
        Collections.sort(movies,new SortByRating());
        for(int i = 0;i<Math.min(size,movies.size());i++){
            to_return.add(movies.get(i));
        }
        return to_return;
    }
}

// class SortByName implements Comparator<Movie> {
//     @Override
//     public int compare(Movie o1, Movie o2){
//         return o1.getName().compareTo(o2.getName());
//     }

// }

class SortByRating implements Comparator<Movie>{
    @Override
    public int compare( Movie o1, Movie o2){

        if(o1.getAvgRating()<o2.getAvgRating()){
            return 1;
        }
        else if(o1.getAvgRating()==o2.getAvgRating()){
            return 0;
        }
        else{
            return -1;
        }
    }
}
