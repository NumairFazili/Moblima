package Entity;

import java.util.ArrayList;
import java.util.List;

public class Movie {


    int id;
    double rating;
    String name,Language,runTime,Description,Director;
    List<String> cast;

    public Movie(int id,String name,String Language,double rating,String runTime,List<String> cast,String Description,String Director) {
        this.id = id;
        this.rating = rating;
        this.name = name;
        this.Language=Language;
        this.runTime = runTime;
        this.Description = Description;
        this.Director = Director;
        this.cast = cast;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }





    public String getRunTime() {
        return runTime;
    }

    public void setRunTime(String runTime) {
        this.runTime = runTime;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public String getDirector() {
        return Director;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLanguage() {
        return Language;
    }

    public void setLanguage(String language) {
        Language = language;
    }

    public void setDirector(String director) {
        Director = director;
    }

    public List<String> getCast() {
        return cast;
    }

    public void setCast(List<String> cast) {
        this.cast = cast;
    }
}
