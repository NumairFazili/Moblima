public class Staff extends Person{
    String password;
    
    //basic methods
    public Staff(String name, String password){
        this.name = name;
        this.password = password;
        this.ID = generateID();
    }
    void setPassword(String password){
        this.password = password;
    }
    //end of basic methods

    //login implemented in main

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

    //CALENDER
    

}