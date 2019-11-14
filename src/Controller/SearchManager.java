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
import Entity.User;
import javafx.css.Size;

import java.lang.reflect.Array;
import java.util.*;

public class SearchManager {

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
    public static ArrayList<Movie> get_topN_byRating(ArrayList<Movie> movies){
        ArrayList<Movie> to_return = new ArrayList<Movie>();
            Collections.sort(movies,new SortByRating());
            for(int i = 0;i<Math.min(SIZE_OF_PQ,movies.size());i++){
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
                return (o1.getValue()).compareTo(o2.getValue());
            }
        });

        // put data from sorted list to hashmap
        HashMap<Integer, Integer> temp = new LinkedHashMap<Integer, Integer>();
        for (Map.Entry<Integer, Integer> aa : list) {
            temp.put(aa.getKey(), aa.getValue());
        }
        return temp;
    }
    public static ArrayList<Movie> get_topN_bySale(ArrayList<Movie> movies, ArrayList<Booking> bookings){
        ArrayList<Movie> to_return = new ArrayList<Movie>();
        HashMap<Integer,Integer> sales_map = SearchManager.calculateSales(bookings);
        Map<Integer, Integer> sorted_map = sortByValue(sales_map);
        for(Map.Entry<Integer,Integer> entry: sorted_map.entrySet()){

            Movie m = find_Movie_byID(movies,entry.getKey());
            if(m == null){
                continue;
            }
            to_return.add(m);
            if(to_return.size()>=SIZE_OF_PQ){
                break;
            }
        }
        return to_return;
    }

    public static ArrayList<Booking> getBookingHistory(String userName, String mobileNumber, ArrayList<Booking> bookings){
        ArrayList<Booking> to_return = new ArrayList<Booking>();
        for(int i = 0;i<bookings.size();i++){
            String get_mobnum = bookings.get(i).getMobileNumber();

            if(bookings.get(i).getCustomerName()==userName && get_mobnum.equalsIgnoreCase(mobileNumber)){
                to_return.add(bookings.get(i));
            }
        }
        return to_return;

    }
    /*
    public static void main(String args[]){
        ArrayList<Booking> ar = DataManager.LoadBookings();
        for(int i = 0 ;i<ar.size();i++){
            System.out.println(ar.get(i).toString());
            System.out.println(ar.get(i).getBookingID());
        }
        System.out.println(getBookingHistory("Test User","123456789",ar))
    }

     */

}
