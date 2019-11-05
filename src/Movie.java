import java.util.ArrayList;

public class Movie extends MovieShow{
    //variables
    private String name;
    private int status;
    private String synopsis;
    private String director;
    private String cast;
    private double rating;
    private ArrayList<String> reviews;
    private int base_price;
    private int number_of_reviews;

    //constructor
    public Movie(String name, int status, String synopsis, String director, String cast, int base_price){
        this.name = name;
        this.status = status;
        this.synopsis = synopsis;
        this.director = director;
        this.cast = cast;
        this.base_price = base_price;
        this.reviews = new ArrayList<String>();
        this.rating = 0;
        number_of_reviews = 0;
    }
    //Setters
    public void setName(String name){
        this.name = name;
    }
    public void setStatus(int status){
        System.out.println("Set movie status: 0 for coming soon, 1 for preview, 2 for showing, 3 for stopped");
        this.status = status;
    }
    public void setSynopsis(String synopsis) { this.synopsis = synopsis; }
    public void setDirector(String director) {this.director = director; }
    public void setCast(String cast) {this.cast = cast; }
    public void setBaseprice(int base_price) {this.base_price = base_price; }

    //Getters
    public String getName() { return name; }
    public int getStatus() { return status;}
    public String getSynopsis() { return synopsis; }
    public String getDirector() { return director; }
    public String getCast() { return cast; }
    public Double getRating() {
        if (number_of_reviews == 0)
            return -1.0;
        else
            return rating/number_of_reviews*1.0;
    }
    public ArrayList getReviews() { return reviews; }
    public int getBaseprice() { return base_price; }

    //methods
    public void addReview(String review, int rating){
        reviews.add(review);
        this.rating += rating;
        number_of_reviews += 1;
    }



}
