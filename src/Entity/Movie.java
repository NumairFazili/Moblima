package Entity;



import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Movie {


    int id;
    String name,Language,runTime,Synopsis,Director,status;
    double avgRating;
    List<Integer> ratings;
    ArrayList<String> reviews;
    List<String> cast;
    public Movie(int id,String name,String Language,List<Integer>ratings,String runTime,List<String> cast,String Director,String Synopsis,ArrayList<String> reviews, String status) {
        this.id = id;
        this.ratings = ratings;
        this.name = name;
        this.Language=Language;
        this.runTime = runTime;
        this.Synopsis = Synopsis;
        this.Director = Director;
        this.cast = cast;
        this.reviews = reviews;
        this.status = status;
    }

    @Override
    public String toString() {
        String cast_str ="";
        for(int i = 0;i<this.getCast().size();i++){
            cast_str += this.getCast().get(i);
            cast_str += "|";
        }
        return new String("MovieName: "+this.getName()+"| Rating"+this.getRating()+"\nCasts: "+cast_str);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<Integer> getRating() {
        return ratings;
    }

    public void setRating(List<Integer> ratings) {
        this.ratings = ratings;
    }

    public void addRating(Integer rating){
        this.ratings.add(rating);
    }
    
    public double getAvgRating(){
            if(ratings.size()> 1){
                avgRating = 0;
                for(int i = 0; i < ratings.size(); i++)
                    avgRating += ratings.get(i);
                return avgRating /= ratings.size();
            }
        return -1;
    }

    public String getRunTime() {
        return runTime;
    }

    public void setRunTime(String runTime) {
        this.runTime = runTime;
    }

    public String getSynopsis() {
        return Synopsis;
    }

    public void setSynopsis(String Synopsis) {this.Synopsis = Synopsis; }

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

    public ArrayList<String> getReviews(){
        if(reviews != null){
            return reviews;
        }else{
            return new ArrayList<String>();
        }
    }

    public void addReview(String review){
        this.reviews.add(review);
    }

    public void setReviews(ArrayList<String> reviews){
        this.reviews = reviews;
    }

    public String getStatus(){
        return status;
    }

    public void setStatus(String status){
        this.status = status;
    }



}
