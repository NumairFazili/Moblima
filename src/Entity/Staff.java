package Entity;

public class Staff extends Person{
    String password;
    
    //basic methods
    public Staff(String name, String password){
        this.setName(name);         //using base class method
        this.password = password;
        //ID should be generated automatically
    }
    public void setPassword(String password){
        this.password = password;
    }
    //end of basic methods

    //login implemented in main
    //mechanism_1: Get staff object of the name inputted --> s.verifyPassword(password inputted)
    //mechanism_2: Directly get the staff object of the name and password, not exist then login failed
    public boolean verifyPassword(String password){
        if (password == this.password)
            return true;
        return false;
    }

    //MOVIE 
    /*
    void editName(Movie m, String s){
        m.setName(s);
    }
    void editStatus(Movie m, int s){
        m.setStatus(s);
    }
    void editMovieSynopsis(Movie m, String s){
        m.setSynopsis(s);
    }
    void editMovieDirector(Movie m, String s){
        m.setDirector(s);
    }
    // void editCast(Movie m, String cast);
    void editBasePrice(Movie m, int p){
        m.setBasePrice(p);
    }
    */


    //SHOWTIME

    //USER
    /*
    void viewUserBookingHistory(User u){
        u.viewBookingHistory();
    }
    */

    //CALENDER?
    
    //to verify the attributes
    public static void main(String args[]){
        Staff s = new Staff("Cindy", "passwordhere");
        System.out.println(s.ID);
        System.out.println(s.name);
        System.out.println(s.password);
    }

}