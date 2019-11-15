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
        Movie mymovie = this.selectMovieByID(this.getAllMovies(), inputsearchint);
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
                mymovie.setName(input.nextLine());
            }
            //2. Prompt input for language and edit movie object
            else if (choice == 2){
                System.out.println("Enter new language:");
                mymovie.setLanguage(input.nextLine());
            }
            //3. Prompt input for runtime and edit movie object
            else if (choice == 3){
                System.out.println("Enter new runtime:");
                mymovie.setRunTime(input.nextLine());
            }
            //4. Prompt input for Cast member and edit movie object
            else if (choice == 4){
                System.out.println("Enter new cast members:");
                mymovie.setCast(Arrays.asList((input.nextLine().split(","))));
            }
            //5. Prompt input for description and edit movie object
            else if (choice == 5){
                System.out.println("Enter new Synopsis:");
                mymovie.setSynopsis(input.nextLine());
            }
            //6. Prompt input for director and edit movie object
            else if (choice == 6){
                System.out.println("Enter new director:");
                mymovie.setDirector(input.nextLine());
            }
            //7. Prompt input for Minimum age and edit movie object
            else if (choice == 7){
                System.out.println("Enter Status");
                mymovie.setStatus(input.nextLine());
            }

        }
        //Save edited movie object to database
        if (DataManager.manageMovie(mymovie) == Boolean.TRUE){
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
        this.createNewMovie(movieid, moviename, language, Arrays.asList(), runtime, cast, director,Synopsis,new ArrayList<>(), status);

    }

    public void createNewMovie(int id, String name, String Language, List<Integer> rating, String runTime, List<String> cast, String Synopsis, String Director,ArrayList<String> reviews, String status){
        Movie m = new Movie(id, name, Language, rating, runTime, cast, Director,Synopsis,reviews, status);
        DataManager.SaveMovies(m);
        System.out.println("Movie Created Successfully\n");
    }













}
