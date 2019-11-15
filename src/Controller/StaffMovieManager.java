package Controller;
import Entity.Movie;
import View.Boundary;
import java.util.*;

public class StaffMovieManager extends MovieManager {


    public StaffMovieManager(){
    }

    public void UpdateMovie(Scanner input){
        //List all movies
        Boundary.DisplayMovie(getAllMovies());
        //Boundary.DisplayMovies(DataManager.LoadMovies(""));
        //Select movie to update by movieID
        System.out.println("Enter ID of the movie to be updated: " );
        System.out.println("Otherwise enter -2 to go back" );
        int inputsearchint = input.nextInt();
        if (inputsearchint == -2){
            return;
        }
        input.nextLine();//Catch newline from input.nextInt()
        Movie mymovie = selectMovieByID(getAllMovies(), inputsearchint);
        Boundary.DisplayMovie(mymovie);

        //Choose which attribute of the movie to be edited
        System.out.println("Choose attribute of movie to be edited: " );
        int choice = -1;
        while (choice != 0){
            choice = -1;
            while (choice <= -1 || choice >= 8){
                try{
                    Scanner in = new Scanner(System.in);
                    Boundary.DisplayOptions("moviesMenu");
                    System.out.println("0. Done");
                    choice = in.nextInt();
                    in.nextLine();//catch newline
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
                this.updateMovieStatus(mymovie, input.nextLine());
            }

        }
        //Save edited movie object to database
        if (this.saveMovieChanges(mymovie) == Boolean.TRUE){
            System.out.println("Movie listing successfully updated!\n");
        }
        else{
            System.out.println("Error movie listing failed to be updated!\n");
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
        System.out.println("Enter Status:");
        String status = input.next();
        input.nextLine();
        this.createNewMovie(movieid, moviename, language, Arrays.asList(), runtime, cast, director,Synopsis,Arrays.asList(), status);

    }

    private void createNewMovie(int id, String name, String language, List<Integer> rating, String runTime, List<String> cast, String synopsis, String director, List<String> reviews, String status){
        Movie m = new Movie(id, name, language, rating, runTime, cast, director,synopsis,reviews, status);
        DataManager.SaveMovies(m);
        System.out.println("Movie Created Successfully\n");
    }

    private void updateMovieName(Movie movie, String name){
        movie.setName(name);
    }
    private void updateMovieLanguage(Movie movie, String language){ movie.setLanguage(language); }
    private void updateMovieRunTime(Movie movie, String runTime){
        movie.setRunTime(runTime);
    }
    private void updateMovieSynopsis(Movie movie, String synopsis){
        movie.setSynopsis(synopsis);
    }
    private void updateMovieDirector(Movie movie, String director){
        movie.setDirector(director);
    }
    private void updateMovieCast(Movie movie, List<String> cast){
        movie.setCast(cast);
    }
    private void updateMovieStatus(Movie movie, String status){ movie.setStatus(status); }
    private boolean saveMovieChanges(Movie movie){ return DataManager.manageMovie(movie);}

}
