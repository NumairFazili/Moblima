package Data;

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

    private static ArrayList<Movie> loadMovies(String search){
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
                    List<String> str =  Arrays.asList(tokens[4].split("\\."));
                    List<Integer> items=new ArrayList<>();
                    for(String s : str)items.add(Integer.valueOf(s));
                    Cinema cinema=new Cinema(Integer.parseInt(tokens[0]),Integer.parseInt(tokens[1]),Integer.parseInt(tokens[2]),tokens[3],items);
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
            writer.append(Joiner.on('.').join(cinema.getSeats()));
            writer.append("\n");
            writer.flush();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
//


    public static void main(String[] args){

//        Movie movie=new Movie(1004,"Joker","English",9.8,"3:15",Arrays.asList("a,b","c"),"sampleText","sampleText");
//
//        SaveMovies(movie);

//        ArrayList<Movie>test=loadMovies("h");
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

        AddShowTimes(new Cinema(2,2,1004,"11/10/2019 17:30",Arrays.asList(1,1,0,0)));


    }


}
