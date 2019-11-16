package Entity;


import java.util.ArrayList;
import java.util.List;

/**
 Represents a movie in each cinema, a cinema can have many movies
 @author CZ2002 Group 1
 @version 1.0
 @since 15-11-2019
 */
public class Movie {

    /**
     * Movie ID
     */
    int id;
    /**
     * Parameters for name, language, runtime, synopsis, director and status of movie
     */
    String name,Language,runTime,Synopsis,Director,status;
    /**
     * Ratings of movie
     */
    List<Integer> ratings;
    /**
     * Reviews of movie
     */
    ArrayList<String> reviews;
    /**
     * Cast of movie
     */
    List<String> cast;

    /**
     * Creates a new movie object with the following parameters
     * @param id movie id
     * @param name movie name
     * @param Language movie language
     * @param ratings movie rating
     * @param runTime movie runtime
     * @param cast movie cast
     * @param Synopsis movie synopsis
     * @param Director movie director
     * @param reviews movie reviews
     * @param status movie status
     */
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

    /**
     * Get the movie ID
     * @return movie ID
     */
    public int getId() { return id; }

    /**
     * Change the movie ID
     * @param id movie ID
     */
    public void setId(int id) {
        this.id = id;
    }

        /**
     * Get the movie ratings
     * @return movie ratings
     */
    public List<Integer> getRating() {
        return ratings;
    }

    /**
     * Change the movie ratings
     * @param ratings movie ratings
     */
    public void setRating(List<Integer> ratings) {
        this.ratings = ratings;
    }

    /**
     * Add a rating to movie
     * @param rating movie rating
     */
    public void addRating(Integer rating){
        this.ratings.add(rating);
    }

        /**
     * Get the movie runtime
     * @return movie runtime
     */
    public String getRunTime() {
        return runTime;
    }

    /**
     * Change the movie runtime
     * @param runTime movie runtime
     */
    public void setRunTime(String runTime) {
        this.runTime = runTime;
    }

        /**
     * Get the movie synopsis
     * @return movie synopsis
     */
    public String getSynopsis() {
        return Synopsis;
    }

    /**
     * Change the movie synopsis
     * @param Synopsis movie synopsis
     */
    public void setSynopsis(String Synopsis) {this.Synopsis = Synopsis; }

    /**
     * Get the movie director
     * @return movie director
     */
    public String getDirector() {
        return Director;
    }

    /**
     * Get the movie name
     * @return movie name
     */
    public String getName() {
        return name;
    }

    /**
     * Change the movie name
     * @param name movie name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Get the movie language
     * @return movie language
     */
    public String getLanguage() {
        return Language;
    }

    /**
     * Change the movie language
     * @param language movie language
     */
    public void setLanguage(String language) {
        Language = language;
    }

    /**
     * Change the movie director
     * @param director movie director
     */
    public void setDirector(String director) {
        Director = director;
    }

    /**
     * Get the movie cast
     * @return movie cast
     */
    public List<String> getCast() {
        return cast;
    }

    /**
     * Change the movie cast
     * @param cast movie cast
     */
    public void setCast(List<String> cast) {
        this.cast = cast;
    }

    /**
     * Get the movie reviews
     * @return movie reviews
     */
    public ArrayList<String> getReviews(){
        if(reviews != null){
            return reviews;
        }else{
            return new ArrayList<String>();
        }
    }

    /**
     * Add a review to movie
     * @param review movie review
     */
    public void addReview(String review){
        this.reviews.add(review);
    }

    /**
     * Change the movie reviews
     * @param reviews movie reviews
     */
    public void setReviews(ArrayList<String> reviews){
        this.reviews = reviews;
    }

    /**
     * Get the movie status
     * @return movie status
     */
    public String getStatus(){
        return status;
    }

    /**
     * Change the movie status
     * @param status movie status
     */
    public void setStatus(String status){
        this.status = status;
    }


}
