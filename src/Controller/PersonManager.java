package Controller;

import java.util.ArrayList;
import java.util.List;

import Entity.*;
import View.Boundary;

//put some common functions here
/*
(1) Movie
    getAllMovies
    getMovieByName
    selectMovieByID
    listTopBySales
    listTopByRatings
(2) ShowTime
    getShowTimesByCineplex
    getShowTimesByMovie
*/
//note: only methods the return single object would return null, others would at least return empty list


public class PersonManager{

    public ArrayList<Movie> getAllMovies(){
        ArrayList<Movie> m_list = DataManager.LoadMovies("");
        ArrayList<Movie> result_list = new ArrayList<Movie>();
        if(m_list != null){
            return m_list;
        }else{
            return result_list;
        }
    }

    public ArrayList<Movie> getMovieByName(String s){
        ArrayList<Movie> m_list =  DataManager.LoadMovies(s);
        ArrayList<Movie> result_list = new ArrayList<Movie>();
        if(m_list != null){
            return m_list;
        }else{
            return result_list;
        }
    }

    public Movie selectMovieByID(ArrayList<Movie> m_list, int id){
        if(m_list != null){
            for (Movie m: m_list){
                if (m.getId() == id){
                    return m;
                }
            }    
        }
        return null;
    }
    //slight variation of the upper method
    public Movie selectMovieByID(int id){
        ArrayList<Movie> m_list = DataManager.LoadMovies("");
        if(m_list != null){
            for (Movie m: m_list){
                if (m.getId() == id){
                    return m;
                }
            }    
        }
        return null;
    }

    public ArrayList<Movie> getTopByRatings(){
        ArrayList<Movie> m_list = getAllMovies();
        ArrayList<Movie> result_list = new ArrayList<Movie>();
        if(m_list != null){
            try{
                result_list = SearchManager.get_topN_byRating(m_list);
            }catch (Exception e){
                System.out.println(e.getMessage());
            }
        }
        return result_list;
    }

    public ArrayList<Movie> getTopBySales(){
        ArrayList<Booking> b_list = DataManager.LoadBookings();
        ArrayList<Movie> result_list = new ArrayList<Movie>();
        if (b_list != null){
            ArrayList<Movie> m_list = getAllMovies();
            if(m_list.size() != 0){
                try{
                    result_list = SearchManager.get_topN_bySale(getAllMovies(), DataManager.LoadBookings());
                }catch(Exception e){
                    System.out.println(e.getMessage());
                }
            }
        }
        return result_list;
    }
 
    public List<Cinema> getAllShowTimes(){
        List<Cinema> c_list = DataManager.LoadShowTimes(-1);
        List<Cinema> return_list = new ArrayList<Cinema>();
        if(c_list != null){
            for(Cinema c: c_list){
                if(!c.getStatus().equals("Ended")){
                    return_list.add(c);
                }
            }
        }
        return return_list;
    }

    public List<Cinema> getShowTimesByCineplex(int cineplexID){
        List<Cinema> c_list = getAllShowTimes();
        List<Cinema> return_list = new ArrayList<Cinema>();
        if(c_list != null){
            for(Cinema c: c_list){
                if(c.getCinplexID() == cineplexID){
                    return_list.add(c);
                }
            }    
        }
        return return_list;
    }

    public List<Cinema> getShowTimesByMovie(int movieID){
        List<Cinema> c_list = DataManager.LoadShowTimes(movieID);
        List<Cinema> result_list = new ArrayList<Cinema>();
        if(c_list != null){
            for(Cinema c: c_list){
                if(!c.getStatus().equals("Ended")){
                    result_list.add(c);
                }
            }    
        }
        return result_list;
    }


    public static void main(String args[]){
        StaffManager s = AuthManager.getStaff("username", "123");
        if(s != null){
            List<Movie> c_list = s.getMovieByName("Avenger");
            Boundary.DisplayMovies(c_list);
        }else{
            System.out.println("Not authorized");
        }
    }
}