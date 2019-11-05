/*
Author: Bingyan
Methods:
1. calculateSales
2. getTopN_byRating
3. find_Movie_byID
4. getTopN_bySales
5. getBookingHistory

Helpers:
1. CompareMapByValue
 */
package Controller;

import Entity.Booking;
import Entity.Movie;
import javafx.css.Size;

import java.lang.reflect.Array;
import java.util.*;

public class SearchManager {
//Dont use PriorityQueue anymore
    //Delete the PriorityQueue line later
    private static PriorityQueue<Movie> q_rating = new PriorityQueue<Movie>(new SortByRating());
    //private static PriorityQueue<Movie> q_sale = new PriorityQueue<Movie>(new SortBySale());
    private static int SIZE_OF_PQ = 5;
    public SearchManager(){}

    public static HashMap<Integer,Integer> calculateSales(ArrayList<Booking> bookings){
        //map: m[Movie_ID] = Sales
        //if Movie_ID not found, m[Movie_ID] = 1;
        //else m[Movie_ID]++;

        HashMap<Integer,Integer> map = new HashMap<Integer, Integer>();
        for(int i = 0;i<bookings.size();i++){
            int movie_id = bookings.get(i).getMovieID();
            int count = map.containsKey(movie_id)? map.get(movie_id):0;
            map.put(movie_id, count+1);
        }
        return map;
    }

    private static ArrayList<Movie> get_topN_byRating(ArrayList<Movie> movies){
        ArrayList<Movie> to_return = new ArrayList<Movie>();

            Collections.sort(movies,new SortByRating());
            for(int i = 0;i<SIZE_OF_PQ;i++){
                to_return.add(movies.get(i));
            }

        return to_return;
    }

    public static Movie find_Movie_byID(ArrayList<Movie> movies, int id) throws NoSuchElementException{
        for(int i = 0;i<movies.size();i++){
            if(movies.get(i).getId()==id){
                return movies.get(i);
            }
        }
        throw new NoSuchElementException("No such id found");
    }

    private static ArrayList<Movie> get_topN_bySale(ArrayList<Movie> movies, ArrayList<Booking> bookings){
        ArrayList<Movie> to_return = new ArrayList<Movie>();
        HashMap<Integer,Integer> sales_map = SearchManager.calculateSales(bookings);
        CompareMapByValue temp = new CompareMapByValue(sales_map);
        TreeMap<Integer,Integer> sorted_map = new TreeMap<Integer, Integer>(temp);
        sorted_map.putAll(sales_map);
        for(Map.Entry<Integer,Integer> entry: sorted_map.entrySet()){
            to_return.add(find_Movie_byID(movies,entry.getKey()));
            if(to_return.size()>=5){
                break;
            }
        }
        return to_return;
    }

    private static ArrayList<Booking> getBookingHistory(String userName, String mobileNumber, ArrayList<Booking> bookings){
        ArrayList<Booking> to_return = new ArrayList<Booking>();
        for(int i = 0;i<bookings.size();i++){
            String temp_str = Long.toString(bookings.get(i).getMobileNumber());

            if(bookings.get(i).getCustomerName()==userName && temp_str.equalsIgnoreCase(mobileNumber)){
                to_return.add(bookings.get(i));
            }
        }
        return to_return;

    }

//Dont use Priority Queue anymore

    private static ArrayList<Movie> get_top5_PQ (String by) throws IllegalArgumentException{
        //Dont use this one
        ArrayList<Movie> to_return = new ArrayList<Movie>();
        if(by.equalsIgnoreCase(new String("rating"))){
            for(int i = 0; i<SIZE_OF_PQ;i++){
                to_return.add(q_rating.poll());
            }
        }
        else if(by.equalsIgnoreCase(new String("sale"))){

        }
        else{
            throw new IllegalArgumentException("Illegal input, enter rating or sale");
        }
        return to_return;
    }


}


class CompareMapByValue implements Comparator<Integer>{
    Map<Integer,Integer> base;
    public CompareMapByValue(Map<Integer,Integer> base){
        this.base = base;
    }
    @Override
    public int compare(Integer a, Integer b){
        if(base.get(a)>= base.get(b)){
            return -1;
        }
        else{
            return 1;
        }
    }
}