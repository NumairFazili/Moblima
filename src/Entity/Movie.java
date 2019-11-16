package Entity;


import java.util.ArrayList;
import java.util.List;

public class Movie {


    int id;
    String name,Language,runTime,Synopsis,Director,status;
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
