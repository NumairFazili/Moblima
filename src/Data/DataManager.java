package Data;

import Entity.Movie;

import java.io.*;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DataManager {
    


    public DataManager(String Data){

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

    }




}
