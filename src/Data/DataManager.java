package Data;

import Entity.Booking;
import Entity.Cinema;
import Entity.Movie;
import com.google.common.base.Joiner;

import java.io.*;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class DataManager {
    


    public DataManager(){

    }

    private static ArrayList<Movie> LoadMovies(String search){
        BufferedReader reader = null;
        ArrayList<Movie> movieArrayList=new ArrayList<>();
        try {
            reader=new BufferedReader(new FileReader("src/Data/"+"Movie"+".csv"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        String line = "";
        try {
            reader.readLine();
            while ((line = reader.readLine()) != null) {
                String[] tokens = line.split(",");
                if(tokens[1].toLowerCase().contains(search.toLowerCase())){
                    List<String> items =  Arrays.asList(tokens[5].split("\\."));
                    Movie movie=new Movie(Integer.parseInt(tokens[0]),tokens[1],tokens[2],Double.parseDouble(tokens[3]),tokens[4],items,tokens[6],tokens[7]);
                    movieArrayList.add(movie);
                }
            }

        } catch (IOException e) {
            e.printStackTrace();

        }
        return movieArrayList;
    }

    private static void SaveMovies(Movie movie){
        FileWriter writer = null;
        try {
            writer = new FileWriter("src/Data/"+"Movie"+".csv",true);
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            writer.append(String.valueOf(movie.getId()));
            writer.append(",");
            writer.append(movie.getName());
            writer.append(",");
            writer.append(movie.getLanguage());
            writer.append(",");
            writer.append(String.valueOf(movie.getRating()));
            writer.append(",");
            writer.append(movie.getRunTime());
            writer.append(",");
            writer.append(String.join(".",movie.getCast()));
            writer.append(",");
            writer.append(movie.getDescription());
            writer.append(",");
            writer.append(movie.getDirector());
            writer.append("\n");
            writer.flush();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }


    public static List<Cinema> LoadShowTimes(int cinplexID){
        BufferedReader reader = null;
        ArrayList<Cinema> cinemaArrayList=new ArrayList<>();
        try {
            reader=new BufferedReader(new FileReader("src/Data/"+"Cinema"+".csv"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        String line = "";
        try {
            reader.readLine();
            while ((line = reader.readLine()) != null) {
                String[] tokens = line.split(",");

                if(tokens[0].contains(String.valueOf(cinplexID))){
                    List<String> str =  Arrays.asList(tokens[5].split("\\."));
                    List<Integer> items=new ArrayList<>();
                    for(String s : str)items.add(Integer.valueOf(s));
                    Cinema cinema=new Cinema(Integer.parseInt(tokens[0]),Integer.parseInt(tokens[1]),Integer.parseInt(tokens[2]),tokens[3],tokens[4],items);
                    cinemaArrayList.add(cinema);
                }
            }

        } catch (IOException e) {
            e.printStackTrace();

        }
        return cinemaArrayList;
    }


    public static void  AddShowTimes(Cinema cinema){
        FileWriter writer = null;
        try {
            writer = new FileWriter("src/Data/"+"Cinema"+".csv",true);
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            writer.append(String.valueOf(cinema.getCinplexID()));
            writer.append(",");
            writer.append(String.valueOf(cinema.getCinemaID()));
            writer.append(",");
            writer.append(String.valueOf(cinema.getMovieID()));
            writer.append(",");
            writer.append(String.valueOf(cinema.getTime()));
            writer.append(",");
            writer.append(String.valueOf(cinema.getStatus()));
            writer.append(",");
            writer.append(Joiner.on('.').join(cinema.getSeats()));
            writer.append("\n");
            writer.flush();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static ArrayList<Booking> LoadBookings(){
        BufferedReader reader = null;
        ArrayList<Booking> bookingArrayList=new ArrayList<>();
        try {
            reader=new BufferedReader(new FileReader("src/Data/"+"Booking"+".csv"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        String line = "";
        try {
            reader.readLine();
            while ((line = reader.readLine()) != null) {
                String[] tokens = line.split(",");


                long bookingID=Long.parseLong(tokens[0]);
                int cinplexID= Integer.parseInt(tokens[1]);
                int cinemaID=Integer.parseInt(tokens[2]);
                int movieID=Integer.parseInt(tokens[3]);
                String showTime=tokens[4];
                String cinemaClass=tokens[5];
                String movieType=tokens[6];
                String customerName=tokens[7];
                long mobileNumber=Long.parseLong(tokens[8]);
                String email=tokens[9];
                String customerType=tokens[10];
                int seatNO=Integer.parseInt(tokens[11]);
                String bookingTime=tokens[12];
                int price=Integer.parseInt(tokens[13]);



                Booking booking=new Booking(bookingID,cinplexID,cinemaID,movieID,showTime,cinemaClass,movieType,customerName,mobileNumber,email,customerType,seatNO,bookingTime,price);
                bookingArrayList.add(booking);

            }

        } catch (IOException e) {
            e.printStackTrace();

        }
        return bookingArrayList;
    }


    public static void AddBooking(Booking booking){
        FileWriter writer = null;
        try {
            writer = new FileWriter("src/Data/"+"Booking"+".csv",true);
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            writer.append(String.valueOf(booking.getBookingID()));
            writer.append(",");
            writer.append(String.valueOf(booking.getCinplexID()));
            writer.append(",");
            writer.append(String.valueOf(booking.getCinemaID()));
            writer.append(",");
            writer.append(String.valueOf(booking.getMovieID()));
            writer.append(",");
            writer.append(booking.getShowTime());
            writer.append(",");
            writer.append(booking.getCinemaClass());
            writer.append(",");
            writer.append(booking.getMovieType());
            writer.append(",");
            writer.append(booking.getCustomerName());
            writer.append(",");
            writer.append(String.valueOf(booking.getMobileNumber()));
            writer.append(",");
            writer.append(booking.getEmail());
            writer.append(",");
            writer.append(booking.getCustomerType());
            writer.append(",");
            writer.append(String.valueOf(booking.getSeatNO()));
            writer.append(",");
            writer.append(booking.getBookingTime());
            writer.append(",");
            writer.append(String.valueOf(booking.getPrice()));
            writer.append("\n");
            writer.flush();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static void main(String[] args){

//        Movie movie=new Movie(1004,"Joker","English",9.8,"3:15",Arrays.asList("a","b","c"),"sampleText","sampleText");
//
//        SaveMovies(movie);

        ArrayList<Movie> test=LoadMovies("BATMAN1");

        for(Movie movie:test)
            System.out.println(movie.getName());

//
//        List<String> str=Arrays.asList("a","b","c");
//        System.out.println(String.join(".",str));

//        ArrayList<Cinema> test2= (ArrayList<Cinema>) LoadShowTimes(2);
//
//        for(Cinema cinema:test2)
//            System.out.println(cinema.getCinemaID());

//        AddShowTimes(new Cinema(2,2,1004,"11/10/2019 17:30","Coming Soon",Arrays.asList(1,1,0,0)));


//        ArrayList<Booking> test3=LoadBookings();
//
//        for(Booking booking:test3)
//            System.out.println(booking.getBookingID());

//        Booking booking=new Booking(15179,1,2,1002,"7/10/2019 16:30","Silver","2D","Test User 3",123456789,"xyz@gmail.com","student",12,"6/10/2019 16:30",11);
//        AddBooking(booking);


    }


}
