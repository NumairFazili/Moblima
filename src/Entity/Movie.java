package Entity;



import java.util.ArrayList;
import java.util.List;

public class Movie {


    int id;
    String name,Language,runTime,Description,Director;
    double avgRating;
    List<Double> ratings;
    List<String> reviews;
    List<String> cast;
    int minAge;
    public Movie(int id,String name,String Language,List<Double>ratings,String runTime,List<String> cast,String Description,String Director, List<String> reviews, int minAge) {
        this.id = id;
        this.ratings = ratings;
        this.name = name;
        this.Language=Language;
        this.runTime = runTime;
        this.Description = Description;
        this.Director = Director;
        this.cast = cast;
        this.reviews = reviews;
        this.minAge = minAge;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<Double> getRating() {
        return ratings;
    }

    public void setRating(List<Double> ratings) {
        this.ratings = ratings;
    }

    public void addRating(Double rating){
        this.ratings.add(rating);
    }
    
    public double getAvgRating(){
        if(ratings != null){
            if(ratings.size()> 1){
                avgRating = 0;
                for(int i = 0; i < ratings.size(); i++){
                    avgRating += ratings.get(i);
                }
                avgRating /= ratings.size();
                return avgRating;    
            }
        }
        return 0;
    }

    public void setAvgRating(double avgRating){
        this.avgRating = avgRating;
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

    public int getMinAge(){
        return minAge;
    }

    public void setMinAge(int minAge){
        this.minAge = minAge;
    }

}
