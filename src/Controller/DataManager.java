package Controller;

import Entity.*;
import com.google.common.base.Joiner;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
public class DataManager {
    


    public DataManager(){

    }

    private static String getLocation(String str) {
        return "src/Data/" + str + ".csv";
    }


    private static ArrayList<Movie> LoadMovies(String search){
        BufferedReader reader = null;
        ArrayList<Movie> movieArrayList=new ArrayList<>();
        try {
            reader=new BufferedReader(new FileReader(getLocation("Movie")));
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
            writer = new FileWriter(getLocation("Movie"),true);
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
            reader=new BufferedReader(new FileReader(getLocation("Cinema")));
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
            writer = new FileWriter(getLocation("Cinema"),true);
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

    public static Boolean UpdateShowTime(Cinema cinema){


        File inputFile = new File("src/Data/Cinema.csv");
        File tempFile = new File("src/Data/myTempFile.csv");

        BufferedReader reader = null;
        BufferedWriter writer=null;
            try {
            reader = new BufferedReader(new FileReader(inputFile));
            writer = new BufferedWriter(new FileWriter(tempFile));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


        try {
            writer.append("Cineplex_ID");
            writer.append(",");
            writer.append("Cinema_ID");
            writer.append(",");
            writer.append("Movie ID");
            writer.append(",");
            writer.append("ShowTime");
            writer.append(",");
            writer.append("Seats");
            writer.append("\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
        Boolean Found=false;
        String line;
            try {
            reader.readLine();
            while ((line = reader.readLine()) != null) {
                String[] tokens = line.split(",");

                if((tokens[0].equals(String.valueOf(cinema.getCinplexID())))&&(tokens[1].equals(String.valueOf(cinema.getCinemaID())))&&(tokens[2].equals(String.valueOf(cinema.getMovieID())))&&(tokens[3].contains(String.valueOf(cinema.getTime())))){
                    Found=true;
                }
                    else{
                        writer.append(tokens[0]);
                        writer.append(",");
                        writer.append(tokens[1]);
                        writer.append(",");
                        writer.append(tokens[2]);
                        writer.append(",");
                        writer.append(tokens[3]);
                        writer.append(",");
                        writer.append(tokens[4]);
                        writer.append(",");
                        writer.append(tokens[5]);
                        writer.append("\n");
                    }

                if(Found){
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

                }

            }


        } catch (IOException e) {
            e.printStackTrace();
        }
            try {
    //
            writer.close();
            reader.close();

                if(Found){
                    Files.delete(Paths.get("src/Data/Cinema.csv"));
                }
        } catch (IOException e) {
            e.printStackTrace();
        }

            Boolean success=tempFile.renameTo(new File("src/Data/Cinema.csv"));
            return  success;


    }







    public static ArrayList<Booking> LoadBookings(){
        BufferedReader reader = null;
        ArrayList<Booking> bookingArrayList=new ArrayList<>();
        try {
            reader=new BufferedReader(new FileReader(getLocation("Booking")));
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
            writer = new FileWriter(getLocation("Booking"),true);
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


    public static ArrayList<User> LoadUser(){

        BufferedReader reader = null;
        ArrayList<User> userArrayList=new ArrayList<>();
        try {
            reader=new BufferedReader(new FileReader(getLocation("User")));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        String line = "";
        try {
            reader.readLine();
            while ((line = reader.readLine()) != null) {
                String[] tokens = line.split(",");
                    User user=new User(tokens[0],Integer.parseInt(tokens[1]),tokens[2],tokens[3]);
                    userArrayList.add(user);

            }

        } catch (IOException e) {
            e.printStackTrace();

        }
        return userArrayList;


    }

    public static void addUser(User user){

        FileWriter writer = null;
        try {
            writer = new FileWriter(getLocation("User"),true);
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            writer.append(user.getName());
            writer.append(",");
            writer.append(user.getmobileNumber());
            writer.append(",");
            writer.append(user.getEmail());
            writer.append("\n");
            writer.flush();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static ArrayList<Cineplex> LoadCineplex(){

        BufferedReader reader = null;
        ArrayList<Cineplex> cineplexArrayList =new ArrayList<>();
        try {
            reader=new BufferedReader(new FileReader(getLocation("Cineplex")));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        String line = "";
        try {
            reader.readLine();
            while ((line = reader.readLine()) != null) {
                String[] tokens = line.split(",");
                Cineplex cineplex =new Cineplex(Integer.parseInt(tokens[0]),tokens[1],tokens[2],Integer.parseInt(tokens[3]));
                cineplexArrayList.add(cineplex);

            }

        } catch (IOException e) {
            e.printStackTrace();

        }
        return cineplexArrayList;

    }


    public static void main(String[] args){

//        Movie movie=new Movie(1004,"Joker","English",9.8,"3:15",Arrays.asList("a","b","c"),"sampleText","sampleText");
//
//        SaveMovies(movie);

//        ArrayList<Movie> test=LoadMovies("BATMAN");
//
//        for(Movie movie:test)
//            System.out.println(movie.getName());

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



//        addUser(new User("test3",123456789,"test3@gmail.com"));
//
//        ArrayList<User> test=LoadUser();
////
//        for(User user:test)
//            System.out.println(user.getMobileNumber());

//
//        ArrayList<Cineplex> test=LoadCineplex();
////
//        for(Cineplex cineplex:test)
//            System.out.println(cineplex.getName());


//        System.out.println((UpdateShowTime(new Cinema(2,2,1004,"11/10/2019 17:30" ,"Show Ended",Arrays.asList(1,1,0,0)))));
        //2,2,1004,11/10/2019 17:30,Show Ended,1.1.0.0





    }


}
