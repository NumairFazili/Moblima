package Controller;
import Entity.Movie;
import Entity.Staff;
import View.Boundary;
import java.util.*;

public class StaffMovieManager extends MovieManager {


    MovieManager movieManager;
    Staff staff;





    public StaffMovieManager(){
        movieManager=new MovieManager();
        staff=new Staff();
    }

    public void removeMovieListing(Scanner input){
        //List all movies
        Boundary.DisplayMovie(movieManager.getAllMovies());
        //Select movie to remove by movieID
        System.out.println("Enter ID of the movie to remove: " );
        System.out.println("Otherwise enter -2 to go back" );

        int inputsearchint = input.nextInt();
        if (inputsearchint == -2){
            //Do nothing
        }
        else{
            try {
                if (this.deleteMovie(SearchManager.find_Movie_byID(this.getAllMovies(), inputsearchint)) == Boolean.TRUE) {
                    System.out.println("Movie listing successfully removed!");
                }
            }
            catch (NoSuchElementException e){
                System.out.println("Error movie listing failed to be removed!");
            }
        }
    }
    public void createNewMovie(int id, String name, String Language, List<Integer> rating, String runTime, List<String> cast, String Synopsis, String Director, List<String> comments, String status){

        Movie m = new Movie(id, name, Language, rating, runTime, cast, Synopsis, Director, comments, status);
        DataManager.SaveMovies(m);
    }

    public void UpdateMovie(Scanner input){
        //List all movies
        Boundary.DisplayMovie(this.getAllMovies());
        //Boundary.DisplayMovies(DataManager.LoadMovies(""));
        //Select movie to update by movieID
        System.out.println("Enter ID of the movie to be updated: " );
        System.out.println("Otherwise enter -2 to go back" );
        int inputsearchint = input.nextInt();
        if (inputsearchint == -2){
            return;
        }
        input.nextLine();//Catch newline from input.nextInt()
        Movie mymovie = SearchManager.find_Movie_byID(this.getAllMovies(), inputsearchint);
        Boundary.DisplayMovie(mymovie);

        //Choose which attribute of the movie to be edited
        System.out.println("Choose attribute of movie to be edited: " );
        int choice = -1;
        while (choice != 0){
            choice = -1;
            while (choice <= -1 || choice >= 8){
                try{
                    Scanner in = new Scanner(System.in);
                    System.out.println("1. movie name");
                    System.out.println("2. language");
                    System.out.println("3. runtime");
                    System.out.println("4. Cast member");
                    System.out.println("5. Synopsis");
                    System.out.println("6. director");
                    System.out.println("7. Status");
                    System.out.println("0. Done");
                    choice = in.nextInt();
                    if (choice <= -1 || choice >= 8){
                        System.out.println("Error! Please enter either 0, 1, 2, 3, 4, 5, 6 or 7:");
                    }
                }
                catch(InputMismatchException e){
                    System.out.println("That is not an integer, please try again." );
                }
            }
            //0. Done, save new movie object to database
            if (choice == 0){
                choice = -1;
                break;
            }
            //1. Prompt input for movie name and edit movie object
            else if (choice == 1){
                System.out.println("Enter new movie name:");
                this.updateMovieName(mymovie, input.nextLine());
            }
            //2. Prompt input for language and edit movie object
            else if (choice == 2){
                System.out.println("Enter new language:");
                this.updateMovieLanguage(mymovie, input.nextLine());
            }
            //3. Prompt input for runtime and edit movie object
            else if (choice == 3){
                System.out.println("Enter new runtime:");
                this.updateMovieRunTime(mymovie, input.nextLine());
            }
            //4. Prompt input for Cast member and edit movie object
            else if (choice == 4){
                System.out.println("Enter new cast members:");
                this.updateMovieCast(mymovie, Arrays.asList((input.nextLine().split(","))));
            }
            //5. Prompt input for description and edit movie object
            else if (choice == 5){
                System.out.println("Enter new Synopsis:");
                this.updateMovieSynopsis(mymovie, input.nextLine());
            }
            //6. Prompt input for director and edit movie object
            else if (choice == 6){
                System.out.println("Enter new director:");
                this.updateMovieDirector(mymovie, input.nextLine());
            }
            //7. Prompt input for Minimum age and edit movie object
            else if (choice == 7){
                System.out.println("Enter Status");
                this.updateMovieStatus(mymovie, input.next());
            }

        }
        //Save edited movie object to database
        if (this.saveMovieChanges(mymovie) == Boolean.TRUE){
            System.out.println("Movie listing successfully updated!");
        }
        else{
            System.out.println("Error movie listing failed to be updated!");
        }

    }

    public void createMovieListing(Scanner input){

        System.out.println("Enter movieID:");
        int movieid = input.nextInt();
        input.nextLine(); //Catch newline from .nextInt()
        System.out.println("Enter movie name:");
        String moviename = input.nextLine();
        System.out.println("Enter language:");
        String language = input.nextLine();
        System.out.println("Enter runtime:");
        String runtime = input.nextLine();
        System.out.println("Enter cast members, split by ','");
        List<String> cast = Arrays.asList(input.nextLine().split(","));
        System.out.println("Enter Synopsis:");
        String Synopsis = input.nextLine();
        System.out.println("Enter director:");
        String director = input.nextLine();
        System.out.println("Enter Minimum age:");
        String status = input.next();
        input.nextLine();
        this.createNewMovie(movieid, moviename, language, Arrays.asList(), runtime, cast, Synopsis, director, Arrays.asList(), status);

    }

































    public void updateMovieName(Movie m, String s){
        m.setName(s);
    }
    public void updateMovieLanguage(Movie m, String s){ m.setLanguage(s); }
    public void updateMovieRunTime(Movie m, String s){
        m.setRunTime(s);
    }
    public void updateMovieSynopsis(Movie m, String s){
        m.setSynopsis(s);
    }
    public void updateMovieDirector(Movie m, String s){
        m.setDirector(s);
    }
    public void updateMovieCast(Movie m, List<String> cast){
        m.setCast(cast);
    }
    public void updateMovieStatus(Movie m, String status){ m.setStatus(status); }
    public boolean saveMovieChanges(Movie m){
        return DataManager.manageMovie(m, false);
    }
    public boolean deleteMovie(Movie m){
        return DataManager.manageMovie(m, true);
    }












}
