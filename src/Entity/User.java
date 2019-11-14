package Entity;

import java.lang.reflect.Array;
import java.util.*;
import java.util.List;

import Controller.BookingManager;
import Controller.DataManager;
import Controller.SearchManager;
import View.Boundary;

public class User extends Person{
    int age;
    String email; 
    String mobileNumber;
    public User(){};
    //for re-creating existing user
    public User(String name, int age, String mobileNumber, String email){
        this.age = age;
        setName_User(name);
        this.email = email;
        this.mobileNumber = mobileNumber;
    }
    public String getCustomerType(){
        if(this.age<15){
            return "student";
        }
        else if(this.age>65){
            return "senior citizen";
        }
        else{
            return "regular";
        }
    }
    public void setName_User(String name){
        this.setName(name);     //using base class function
    }
    public void setAge(int age){
        this.age = age;
    }
    public void setEmail(String email){
        this.email = email;
    }
    public void setmobileNumber(String mobileNumber){
        this.mobileNumber = mobileNumber;
    }

    public int getAge(){
        if(age == 0){
            return -1;
        }
        return age;
    }
    public String getName() {
        return name;
    }
    public String getEmail(){
        return email;
    }
    public String getmobileNumber(){
        return mobileNumber;
    }

    
    public String toString() {
        return name + "\nemail\t:" + email + "\nage\t:" + String.valueOf(age) + "\nmobile\t:" + mobileNumber + "\n";
    }
    // end of basic methods


    public void save(){
        DataManager.ManageUser(this,true);
    }


    public void createBooking(Cinema cinema, int seatNO){

        //Boundary.DisplayBookings(Arrays.asList());


        Movie m = super.selectMovieByID(cinema.getMovieID());
        BookingManager.init();

        if(this.getAge() >= m.getMinAge()){
            Booking booking=BookingManager.createBooking(this, cinema, seatNO);
            Boundary.DisplayBookings(Arrays.asList(booking));
            System.out.println("Press 1 to confirm Booking  0 to Cancel");

            Scanner input=new Scanner(System.in);
            int x=input.nextInt();

            if(x==1){
                BookingManager.saveBooking(booking,cinema);
                System.out.println("Booking Created");
            }
            else{
                System.out.println("Booking Cancelled");

                return;
            }

        }else{
            System.out.println("Minimum age requirement not reached.");
        }
    }

    public List<Booking> getBookings(){

        ArrayList<Booking> all_b = DataManager.LoadBookings();
        ArrayList<Booking> to_return = new ArrayList<Booking>();
        for(Booking b: all_b){
            if(b.getCustomerName().equals(this.getName())&&b.getMobileNumber().equals(this.getmobileNumber())){
                to_return.add(b);
            }
        }
        return to_return;
    }

    public void reviewMovie(Movie m, int rating, String review){
        m.addRating(rating);
        m.addReview(review);
    }
    //to verify the attributes
    public void SearchListMovie(Scanner input, int choice){
        ArrayList<Movie> mymovielist = new ArrayList<Movie>();
        //List all movies
        if (choice == 5){
            mymovielist = DataManager.LoadMovies("");
        }
        //Search and display movies
        if (choice == 1){
            System.out.println("Enter name of movie to search: " );
            String inputsearch = input.next();
            mymovielist = DataManager.LoadMovies(inputsearch);
            if (mymovielist.isEmpty()){
                System.out.println("No movie found!" );
                return;
            }
        }
        Boundary.DisplayMovie(mymovielist);
        //Select movie by movie ID and print movie details – including reviews and ratings
        System.out.println("Enter ID of the movie to see the details: " );
        System.out.println("Otherwise enter 0 to go back" );
        int inputsearchint = input.nextInt();
        if (inputsearchint == 0){
            return;
        }
        Movie mymovie = SearchManager.find_Movie_byID(mymovielist, inputsearchint);
        System.out.println("Movie Details: " );
        Boundary.DisplayMovie(mymovie);
        System.out.println("All ratings and reviews: " );
        Boundary.DisplayMovieReviews(mymovie);
        //Select movie, then search for all showtimes.
        choice = -1;
        while (choice <= -1 || choice >= 3){
            try{
                Scanner in = new Scanner(System.in);
                System.out.println("1. View all showtimes for this movie: ");
                System.out.println("2. Leave a rating and review for this movie: ");
                System.out.println("0. Go back");
                choice = in.nextInt();
                if (choice <= -1 || choice >= 3){
                    System.out.println("Error! Please enter either 0, 1, 2:");
                }
            }
            catch(InputMismatchException e){
                System.out.println("That is not an integer, please try again." );
            }
        }
        if (choice == 0){
            return;
        }
        //Select the showtime by index
        else if (choice == 1){
            Boundary.DisplayCinemas(this.getShowTimesByMovie(mymovie.getId()));
            //3. Check seat availability and selection of seat/s.
            boolean looper = Boolean.TRUE;
            while (looper){
                System.out.println("Choose index of the showtime to view seat availability: ");
                inputsearchint = input.nextInt();
                input.nextLine(); //Catch newline from .nextInt()
                Boundary.DisplaySeating(((DataManager.LoadShowTimes(mymovie.getId()).get(inputsearchint))));
                choice = -1;
                while (choice <= -1 || choice >= 3){
                    try{
                        Scanner in = new Scanner(System.in);
                        System.out.println("1. Select seats");
                        System.out.println("2. Select another showtime");
                        System.out.println("0. Cancel selection and return to main menu");
                        choice = in.nextInt();
                        if (choice <= -1 || choice >= 3){
                            System.out.println("Error! Please enter either 0, 1 or 2:");
                        }
                    }
                    catch(InputMismatchException e){
                        System.out.println("That is not an integer, please try again." );
                    }
                }
                //0. Cancel selection and return to main menu
                if (choice == 0){
                    break;
                }
                //1. Select seats
                else if (choice == 1){
                    looper = Boolean.FALSE; //stop while loop
                    System.out.println("Enter row index of seat:" );
                    int rowofseat = input.nextInt();
                    System.out.println("Enter col index of seat:" );
                    int colofseat = input.nextInt();
                    input.nextLine();//Catch newline from input
                    //4. Book and purchase ticket
                    Cinema mycinema = ((DataManager.LoadShowTimes(mymovie.getId()).get(inputsearchint)));
                    this.createBooking(mycinema, rowofseat*10 + colofseat);



                }
                //2. Select another showtime
                else if (choice == 2){
                    Boundary.DisplayCinemas(this.getShowTimesByMovie(mymovie.getId()));
                    //Boundary.DisplayCinemas(DataManager.LoadShowTimes(mymovie.getId()));
                }
            }
        }
        else if (choice == 2){
            System.out.println("Enter rating for the movie:" );
            int movierating = input.nextInt();
            System.out.println("Enter review for the movie:" );
            String moviereview = input.next();
            this.reviewMovie(mymovie, movierating, moviereview);
        }
    }
}