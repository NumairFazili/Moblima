package Entity;

import Controller.DataManager;
import Controller.SearchManager;
import View.Boundary;

import java.util.*;

public class Staff extends Person {
    String password;
    
    public Staff(String name, String password){
        this.setName_Staff(name);         //using base class method
        this.password = password;
        //ID should be generated automatically
    }

    public void setPassword(String password){
        this.password = password;
    }
    public void setName_Staff(String name){
        this.setName(name);
    }
    public String getName(){
        return name;
    }
    public boolean checkPassword(String password){
        if (password.equals(this.password)){
            return true;
        }
        return false;
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
        System.out.println("Enter description:");
        String description = input.nextLine();
        System.out.println("Enter director:");
        String director = input.nextLine();
        System.out.println("Enter Minimum age:");
        int minage = input.nextInt();
        input.nextLine();
        this.createNewMovie(movieid, moviename, language, Arrays.asList(), runtime, cast, description, director, Arrays.asList(), minage);

    }

    public void removeMovieListing(Scanner input){
        //List all movies
        Boundary.DisplayMovie(this.getAllMovies());
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

    public void createCinemaShowtime(Scanner input){
        //Get user input for new showtime details
        System.out.println("Enter Cineplex_ID:");
        int cineplexid = input.nextInt();
        input.nextLine(); //Catch newline from .nextInt()
        System.out.println("Enter Cinema_ID:");
        int cinemaid = input.nextInt();
        input.nextLine(); //Catch newline from .nextInt()
        System.out.println("Enter Movie ID:");//might need to error check if movieid is legit
        int movieid = input.nextInt();
        input.nextLine(); //Catch newline from .nextInt()
        System.out.println("Enter ShowTime:");
        String showtime = input.nextLine();
        System.out.println("Enter Status");
        String status = input.nextLine();
        System.out.println("Enter class:");
        String cinemaclass = input.nextLine();
        //System.out.println("Enter Seats:"); //Staff probably does not need to enter seat details
        //seats = input.nextLine();
        System.out.println("Enter MovieType:");
        String movietype = input.nextLine();
        //Create new showtime object and save movie listing to database using DataManager
        this.createShowTime(cineplexid, cinemaid, movieid, showtime, status, cinemaclass, Arrays.asList(), movietype);
    }

    public void updateCinemaShowtime(Scanner input){
        //List all showtimes
        Boundary.DisplayCinemas(this.getAllShowTimes());
        //Select showtime to update by index
        System.out.println("Enter Index of the showtime to be updated: " );
        int inputsearchint = input.nextInt();
        input.nextLine();//Catch newline from input.nextInt()
        Cinema mycinema = this.getAllShowTimes().get(inputsearchint);

        //Choose which attribute of the showtime to be edited
        System.out.println("Choose attribute of showtime to be edited: " );
        int choice = -1;
        while (choice != 0){
            choice = -1;
            while (choice <= -1 || choice >= 8){
                try{
                    Scanner in = new Scanner(System.in);
                    System.out.println("1. Cineplex_ID");
                    System.out.println("2. Cinema_ID");
                    System.out.println("3. Movie_ID");
                    System.out.println("4. ShowTime");
                    System.out.println("5. Status");
                    System.out.println("6. class");
                    System.out.println("7. MovieType");
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
            //0. Done, save showtime to database
            if (choice == 0){
                choice = -1;
                break;
            }
            //1. Prompt input for Cineplex_ID name and edit cinema object
            else if (choice == 1){
                System.out.println("Enter new Cineplex_ID:");
                this.updateCinemaID(mycinema, input.nextInt());
                input.nextLine();//Catch newline from input.nextInt
            }
            //2. Prompt input for Cinema_ID and edit cinema object
            else if (choice == 2){
                System.out.println("Enter new Cinema_ID:");
                this.updateCinemaID(mycinema, input.nextInt());
                input.nextLine();//Catch newline from input.nextInt
            }
            //3. Prompt input for Movie_ID and edit cinema object
            else if (choice == 3){
                System.out.println("Enter new Movie_ID:");
                this.updateMovieID(mycinema, input.nextInt());
                input.nextLine();//Catch newline from input.nextInt
            }
            //4. Prompt input for ShowTime and edit cinema object
            else if (choice == 4){
                System.out.println("Enter new ShowTime:");
                this.updateTime(mycinema, input.nextLine());
            }
            //5. Prompt input for Status and edit cinema object
            else if (choice == 5){
                System.out.println("Enter new Status:");
                this.updateStatus(mycinema, input.nextLine());
            }
            //6. Prompt input for cinema class and edit cinema object
            else if (choice == 6){
                System.out.println("Enter new class:");
                this.updateClass(mycinema, input.nextLine());
            }
            //7. Prompt input for MovieType and edit cinema object
            else if (choice == 7){
                System.out.println("Enter new MovieType:");
                this.updateMovieType(mycinema, input.nextLine());
            }

        }
        //Save edited movie object to database
        if (this.saveShowTimeChanges(mycinema) == Boolean.TRUE){
            System.out.println("Showtime successfully updated!");
        }
        else{
            System.out.println("Error! Showtime failed to be updated!");
        }
    }

    public void removeCinemaShowtime(Scanner input){
        //List all showtimes
        Boundary.DisplayCinemas(this.getAllShowTimes());
        //Select showtime to remove by index
        System.out.println("Enter the index of showtime to be removed: " );
        System.out.println("Otherwise enter -2 to go back" );
        int inputsearchint = input.nextInt();
        if (inputsearchint == -2){
            //Do nothing
        }
        else{
            Cinema mycinema = this.getAllShowTimes().get(inputsearchint);
            this.deleteShowTime(mycinema); //should remove entry line instead of setting status to "ended" and return boolean instead of void
            /*if (mystaff.deleteShowTime(mycinema) == Boolean.TRUE){
                System.out.println("Showtime successfully removed!");
            }
            else{
                System.out.println("Error! Showtime failed to be removed!");
            }*/
        }
    }
    public void configureSettings(Scanner input){
        //Choose which setting to configure
        System.out.println("Choose which setting to configure: " );
        Settings mysettings = this.showSettings();
        int choice = -1;
        while (choice != 0){
            choice = -1;
            while (choice <= -1 || choice >= 9){
                try{
                    Scanner in = new Scanner(System.in);
                    System.out.println("1. Base price");
                    System.out.println("2. Child price");
                    System.out.println("3. Senior price");
                    System.out.println("4. Holiday price");
                    System.out.println("5. Silver price");
                    System.out.println("6. Gold price");
                    System.out.println("7. Platinum price");
                    System.out.println("8. Holidays");
                    System.out.println("0. Done");
                    choice = in.nextInt();
                    if (choice <= -1 || choice >= 9){
                        System.out.println("Error! Please enter either 0, 1, 2, 3, 4, 5, 6, 7 or 8:");
                    }
                }
                catch(InputMismatchException e){
                    System.out.println("That is not an integer, please try again." );
                }
            }
            //0. Done, save new settings to database
            if (choice == 0){
                break;
            }
            //1. Prompt input for new base price
            else if (choice == 1){
                System.out.println("Enter new base price:");
                this.updateBasePrice(mysettings, input.nextDouble());
            }
            //2. Prompt input for child price
            else if (choice == 2){
                System.out.println("Enter new child price:");
                this.updateChildPrice(mysettings, input.nextDouble());
            }
            //3. Prompt input for senior price
            else if (choice == 3){
                System.out.println("Enter new senior price:");
                this.updateSeniorPrice(mysettings, input.nextDouble());
            }
            //4. Prompt input for holiday price
            else if (choice == 4){
                System.out.println("Enter new holiday price:");
                this.updateHolidayPrice(mysettings, input.nextDouble());
            }
            //5. Prompt input for silver price
            else if (choice == 5){
                System.out.println("Enter new silver price:");
                this.updateSilverPrice(mysettings, input.nextDouble());
            }
            //6. Prompt input for gold price
            else if (choice == 6){
                System.out.println("Enter new gold price:");
                this.updateGoldPrice(mysettings, input.nextDouble());
            }
            //7. Prompt input for platinum price
            else if (choice == 7){
                System.out.println("Enter new platinum price:");
                this.updatePlatinumPrice(mysettings, input.nextDouble());
                input.nextLine();//Catch newline from input.nextDouble()
            }
            //8. Prompt input for holidays
            else if (choice == 8){
                System.out.println("Enter new holidays:");
                this.updateHoliday(mysettings, Arrays.asList(input.nextLine().split(",")));
            }
        }
        //Save edited movie object to database
        if (this.saveSettingsChanges(mysettings) == Boolean.TRUE){
            System.out.println("Settings successfully updated!");
        }
        else{
            System.out.println("Error! Settings failed to be updated!");
        }
    }
    public void createNewMovie(int id, String name, String Language, List<Integer> rating, String runTime, List<String> cast, String Description, String Director, List<String> comments, int minAge){

        Movie m = new Movie(id, name, Language, rating, runTime, cast, Description, Director, comments, minAge);
        DataManager.SaveMovies(m);
    }
    public void UpdateMovie(Scanner input){
        //List all movies

        Boundary.DisplayMovies(mystaff.getAllMovies());
        //Boundary.DisplayMovies(DataManager.LoadMovies(""));
        //Select movie to update by movieID
        System.out.println("Enter ID of the movie to be updated: " );
        inputsearchint = input.nextInt();
        input.nextLine();//Catch newline from input.nextInt()
        mymovie = SearchManager.find_Movie_byID(mystaff.getAllMovies(), inputsearchint);

        //Choose which attribute of the movie to be edited
        System.out.println("Choose attribute of movie to be edited: " );
        choice = -1;
        while (choice != 0){
            choice = -1;
            while (choice <= -1 || choice >= 8){
                try{
                    Scanner in = new Scanner(System.in);
                    System.out.println("1. movie name");
                    System.out.println("2. language");
                    System.out.println("3. rating");
                    System.out.println("4. runtime");
                    System.out.println("5. Cast member");
                    System.out.println("6. description");
                    System.out.println("7. director");
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
                mystaff.updateMovieName(mymovie, input.nextLine());
            }
            //2. Prompt input for language and edit movie object
            else if (choice == 2){
                System.out.println("Enter new language:");
                mystaff.updateMovieLanguage(mymovie, input.nextLine());
            }
            //3. Prompt input for rating and edit movie object
            else if (choice == 3){// Currently not in use
                System.out.println("Enter new rating:");
                //mymovie.setRating(Arrays.asList(input.nextInt()));
                //input.nextLine();//Catch newline from input.nextDouble()
            }
            //4. Prompt input for runtime and edit movie object
            else if (choice == 4){
                System.out.println("Enter new runtime:");
                mystaff.updateMovieRunTime(mymovie, input.nextLine());
            }
            //5. Prompt input for Cast member and edit movie object
            else if (choice == 5){
                System.out.println("Enter new cast members:");
                mystaff.updateMovieCast(mymovie, Arrays.asList((input.nextLine().split(","))));
            }
            //6. Prompt input for description and edit movie object
            else if (choice == 6){
                System.out.println("Enter new description:");
                mystaff.updateMovieDescription(mymovie, input.nextLine());
            }
            //7. Prompt input for director and edit movie object
            else if (choice == 7){
                System.out.println("Enter new director:");
                mystaff.updateMovieDirector(mymovie, input.nextLine());
            }

        }
        //Save edited movie object to database
        if (mystaff.saveMovieChanges(mymovie) == Boolean.TRUE){
            System.out.println("Movie listing successfully updated!");
        }
        else{
            System.out.println("Error movie listing failed to be updated!");
        }

    }
    public void updateMovieName(Movie m, String s){
        m.setName(s);
    }
    public void updateMovieLanguage(Movie m, String s){ m.setLanguage(s); }
    public void updateMovieRunTime(Movie m, String s){
        m.setRunTime(s);
    }
    public void updateMovieDescription(Movie m, String s){
        m.setDescription(s);
    }
    public void updateMovieDirector(Movie m, String s){
        m.setDirector(s);
    }
    public void updateMovieCast(Movie m, List<String> cast){
        m.setCast(cast);
    }
    public void updateMovieMinAge(Movie m, int age){
        m.setMinAge(age);
    }
    public boolean saveMovieChanges(Movie m){
        return DataManager.manageMovie(m, false);
    }
    public boolean deleteMovie(Movie m){
        return DataManager.manageMovie(m, true);
    }
    public void createShowTime(int cinplexID, int cinemaID, int movieID, String time, String status, String cinemaClass, List<Integer> seats, String movieType){
        Cinema c = new Cinema(cinplexID, cinemaID, movieID, time, status, cinemaClass, seats, movieType);
        DataManager.AddShowTimes(c);
    }
    public void updateCinemaID(Cinema c, int i){
        c.setCinemaID(i);
    }
    public void updateMovieID(Cinema c, int i){
        c.setMovieID(i);
    }
    public void updateTime(Cinema c, String s){
        c.setTime(s);
    }
    public void updateStatus(Cinema c, String s){
        c.setStatus(s);
    }
    public void updateClass(Cinema c, String s){
        c.setCinemaClass(s);
    }
    public void updateMovieType(Cinema c, String s){
        c.setMovieType(s);
    }
    public boolean saveShowTimeChanges(Cinema c){
        return DataManager.UpdateShowTime(c,false);
    }
    public void deleteShowTime(Cinema c){
        c.setStatus("ended");
    }
    public Settings showSettings(){
        Settings s = DataManager.LoadSettings();
        return s;
    }
    public void updateBasePrice(Settings s, double base){
        s.setBasePrice(base);
    }
    public void updateChildPrice(Settings s, double child){
        s.setChildPrice(child);
    }
    public void updateSeniorPrice(Settings s, double senior){
        s.setSeniorPrice(senior);
    }
    public void updateHolidayPrice(Settings s, double holiday){
        s.setHolidayPrice(holiday);
    }
    public void updateSilverPrice(Settings s, double silver){
        s.setSilverPrice(silver);
    }
    public void updateGoldPrice(Settings s, double gold){
        s.setGoldPrice(gold);
    }
    public void updatePlatinumPrice(Settings s, double platinum){
        s.setPlatinumPrice(platinum);
    }
    public void updateHoliday(Settings s, List<String> holiday){
        s.setHolidays(holiday);
    }
    public boolean saveSettingsChanges(Settings s){
        return DataManager.manageSettings(s);
    }
}