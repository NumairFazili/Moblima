package Entity;


import java.util.ArrayList;
import java.util.List;

public class Movie {


    int id;
    String name,language,runTime,synopsis,director,status;
    double avgRating;
    List<Integer> ratings;
    List<String> reviews;
    List<String> cast;
    public Movie(int id,String name,String language,List<Integer>ratings,String runTime,List<String> cast,String director,String synopsis,List<String> reviews, String status) {
        this.id = id;
        this.ratings = ratings;
        this.name = name;
        this.language=language;
        this.runTime = runTime;
        this.synopsis = synopsis;
        this.director = director;
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
        return synopsis;
    }

    public void setSynopsis(String synopsis) {this.synopsis = synopsis; }

    public String getDirector() {
        return director;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public List<String> getCast() {
        return cast;
    }

    public void setCast(List<String> cast) {
        this.cast = cast;
    }

    public List<String> getReviews(){
        if(reviews != null){
            return reviews;
        }else{
            return new ArrayList<String>();
        }
    }

    public void addReview(String review){
        this.reviews.add(review);
    }

    public void setReviews(List<String> reviews){
        this.reviews = reviews;
    }

    public String getStatus(){
        return status;
    }

    public void setStatus(String status){
        this.status = status;
    }

}
