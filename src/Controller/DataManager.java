package Controller;

import Entity.*;
import com.google.common.base.Joiner;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 Represents the DataManager class, with methods to pull, edit and save information in the database
 @author CZ2002 Group 1
 @version 1.0
 @since 15-11-2019
 */
public class DataManager {


    public DataManager() { }

    /**
     * Get path of csv file in database
     * @param str input String of csv file name
     * @return path of csv file
     */
    private static String getLocation(String str) {
        return "src/Data/" + str + ".csv";
    }

    /**
     * Load movie objects from database, searching by movie name corresponding to input String
     * @param search String to search for any substrings in all movie names in database
     * @return ArrayList of Movie objects
     */
    public static ArrayList<Movie> LoadMovies(String search) {
        BufferedReader reader = null;
        ArrayList<Movie> movieArrayList = new ArrayList<>();
        try {
            reader = new BufferedReader(new FileReader(getLocation("Movie")));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        String line = "";
        try {
            reader.readLine();
            while ((line = reader.readLine()) != null) {
                String[] tokens = line.split(",");
                if (tokens[1].toLowerCase().contains(search.toLowerCase())) {
                    List<String> cast = Arrays.asList(tokens[5].split("\\."));
                    ArrayList<String> reviews=new ArrayList<String>(Arrays.asList(tokens[8].split("\\.")));
                    if(reviews.get(0).equals(""))
                        reviews=new ArrayList<>();
                    List<String> temp= Arrays.asList(tokens[3].split("\\."));

                    ArrayList<Integer> ratings=new ArrayList<>();
                    if(!temp.get(0).equals(""))
                        for (String s : temp) ratings.add(Integer.valueOf(s));

                    Movie movie = new Movie(Integer.parseInt(tokens[0]), tokens[1], tokens[2],ratings, tokens[4], cast, tokens[6], tokens[7],reviews,tokens[9]);
                    movieArrayList.add(movie);
                }
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();

        }

        return movieArrayList;
    }

    /**
     *Update details of movie objects in the database by using movie ID from input Movie object as the key
     * @param movie Movie object to be updated
     * @return True if Movie is successfully updated, False otherwise
     */
    public static Boolean manageMovie(Movie movie){

        File inputFile = new File(getLocation("Movie"));
        File tempFile = new File(getLocation("Temp"));

        BufferedReader reader = null;
        BufferedWriter writer = null;
        try {
            reader = new BufferedReader(new FileReader(inputFile));
            writer = new BufferedWriter(new FileWriter(tempFile));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            writer.append("Movie ID");
            writer.append(",");
            writer.append("Movie Name");
            writer.append(",");
            writer.append("Language");
            writer.append(",");
            writer.append("Rating");
            writer.append(",");
            writer.append("RunTime");
            writer.append(",");
            writer.append("Cast");
            writer.append(",");
            writer.append("Director");
            writer.append(",");
            writer.append("Synopsis");
            writer.append(",");
            writer.append("Reviews");
            writer.append(",");
            writer.append("Status");
            writer.append("\n");


        } catch (IOException e) {
            e.printStackTrace();
        }
        Boolean Found = false;
        String line;
        try {
            reader.readLine();
            while ((line = reader.readLine()) != null) {
                String[] tokens = line.split(",");

                if (Integer.parseInt(tokens[0])==movie.getId()) {
                    Found = true;
                } else {
                    writer.append(tokens[0]);
                    writer.append(",");
                    writer.append(tokens[1]);
                    writer.append(",");
                    writer.append(tokens[2]);
                    writer.append(",");
                    writer.append(tokens[3]);
                    writer.append(",");
                    writer.append(tokens[4]);
                    writer.append(",");
                    writer.append(tokens[5]);
                    writer.append(",");
                    writer.append(tokens[6]);
                    writer.append(",");
                    writer.append(tokens[7]);
                    writer.append(",");
                    writer.append(tokens[8]);
                    writer.append(",");
                    writer.append(tokens[9]);
                    writer.append("\n");
                }

                if (Found) {
                    writer.append(String.valueOf(movie.getId()));
                    writer.append(",");
                    writer.append(movie.getName());
                    writer.append(",");
                    writer.append(movie.getLanguage());
                    writer.append(",");
                    writer.append(Joiner.on('.').join(movie.getRating()));
                    writer.append(",");
                    writer.append(movie.getRunTime());
                    writer.append(",");
                    writer.append(String.join(".", movie.getCast()));
                    writer.append(",");
                    writer.append(movie.getDirector());
                    writer.append(",");
                    writer.append(movie.getSynopsis());
                    writer.append(",");
                    writer.append(Joiner.on('.').join(movie.getReviews()));
                    writer.append(",");
                    writer.append(movie.getStatus());
                    writer.append("\n");
                    Found = false;

                }

            }


        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            //
            writer.close();
            reader.close();


            Files.delete(Paths.get(getLocation("Movie")));

        } catch (IOException e) {
            e.printStackTrace();
        }

        Boolean success = tempFile.renameTo(new File(getLocation("Movie")));
        return success;

    }


    /**
     * Save input Movie object details into database
     * @param movie Movie object
     * @return True if database is successfully updated, False otherwise
     */
    public static Boolean SaveMovies(Movie movie) {
        FileWriter writer = null;
        try {
            writer = new FileWriter(getLocation("Movie"), true);
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            writer.append(String.valueOf(movie.getId()));
            writer.append(",");
            writer.append(movie.getName());
            writer.append(",");
            writer.append(movie.getLanguage());
            writer.append(",");
            writer.append(Joiner.on('.').join(movie.getRating()));
            writer.append(",");
            writer.append(movie.getRunTime());
            writer.append(",");
            writer.append(String.join(".", movie.getCast()));
            writer.append(",");
            writer.append(movie.getSynopsis());
            writer.append(",");
            writer.append(movie.getDirector());
            writer.append(",");
            writer.append(Joiner.on('.').join(movie.getReviews()));
            writer.append(",");
            writer.append(movie.getStatus());
            writer.append("\n");
            writer.flush();
            writer.close();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;


    }


    // This function is for further enhancement

    /**
     * Loads and return details of all Cineplexes from database
     * @return ArrayList of Cineplex objects
     */
    public static ArrayList<Cineplex> LoadCineplex() {
        BufferedReader reader = null;
        ArrayList<Cineplex> cineplexArrayList = new ArrayList<>();
        try {
            reader = new BufferedReader(new FileReader(getLocation("Cineplex")));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        String line = "";
        try {
            reader.readLine();
            while ((line = reader.readLine()) != null) {
                String[] tokens = line.split(",");
                Cineplex cineplex = new Cineplex(Integer.parseInt(tokens[0]), tokens[1], tokens[2], Integer.parseInt(tokens[3]));
                cineplexArrayList.add(cineplex);
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();

        }
        return cineplexArrayList;
    }


    /**
     * Loads and return details of all showtimes from database corresponding to input movie ID
     * @param movieID movie ID
     * @return ArrayList of Cinema objects
     */
    public static List<Cinema> LoadShowTimes(int movieID) {

        String ID=String.valueOf(movieID);

        if(movieID==-1)
            ID="";

        BufferedReader reader = null;
        ArrayList<Cinema> cinemaArrayList = new ArrayList<>();
        try {
            reader = new BufferedReader(new FileReader(getLocation("Cinema")));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        String line = "";
        try {
            reader.readLine();
            while ((line = reader.readLine()) != null) {
                String[] tokens = line.split(",");
                if (tokens[2].contains(ID)) {
                    List<Integer> items = new ArrayList<>();
                    if(!tokens[5].equals("")){
                    List<String> str = Arrays.asList(tokens[5].split("\\."));
                    for (String s : str) items.add(Integer.valueOf(s));}
                    Cinema cinema = new Cinema(Integer.parseInt(tokens[0]), Integer.parseInt(tokens[1]), Integer.parseInt(tokens[2]), tokens[3], tokens[4],items,tokens[6]);
                    cinemaArrayList.add(cinema);
                }
            }
            reader.close();

        } catch (IOException e) {
            e.printStackTrace();

        }
        return cinemaArrayList;
    }

    /**
     * Saves input Cinema object details into database
     * @param cinema Cinema object
     * @return True if database is successfully updated, False otherwise
     */
    public static Boolean AddShowTimes(Cinema cinema) {
        FileWriter writer = null;
        try {
            writer = new FileWriter(getLocation("Cinema"), true);
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            writer.append(String.valueOf(cinema.getCinplexID()));
            writer.append(",");
            writer.append(String.valueOf(cinema.getCinemaID()));
            writer.append(",");
            writer.append(String.valueOf(cinema.getMovieID()));
            writer.append(",");
            writer.append(String.valueOf(cinema.getTime()));
            writer.append(",");
            writer.append(String.valueOf(cinema.getCinemaClass()));
            writer.append(",");
            writer.append(Joiner.on('.').join(cinema.getSeats()));
            writer.append(",");
            writer.append(String.valueOf(cinema.getMovieType()));
            writer.append("\n");
            writer.flush();
            writer.close();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     *
     * @param cinema
     * @return Ture if Showtime removed successfully
     */
    public static Boolean RemoveShowTime(Cinema cinema) {


        File inputFile = new File(getLocation("Cinema"));
        File tempFile = new File(getLocation("Temp"));

        BufferedReader reader = null;
        BufferedWriter writer = null;
        try {
            reader = new BufferedReader(new FileReader(inputFile));
            writer = new BufferedWriter(new FileWriter(tempFile));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


        try {
            writer.append("Cineplex_ID");
            writer.append(",");
            writer.append("Cinema_ID");
            writer.append(",");
            writer.append("Movie ID");
            writer.append(",");
            writer.append("ShowTime");
            writer.append(",");
            writer.append("Class");
            writer.append(",");
            writer.append("Seats");
            writer.append(",");
            writer.append("MovieType");
            writer.append("\n");


        } catch (IOException e) {
            e.printStackTrace();
        }
        Boolean Found = false;
        String line;
        try {
            reader.readLine();
            while ((line = reader.readLine()) != null) {
                String[] tokens = line.split(",");

                if ((tokens[0].equals(String.valueOf(cinema.getCinplexID()))) && (tokens[1].equals(String.valueOf(cinema.getCinemaID()))) && (tokens[2].equals(String.valueOf(cinema.getMovieID()))) && (tokens[3].contains(String.valueOf(cinema.getTime())))) {
                    Found = true;
                } else {
                    writer.append(tokens[0]);
                    writer.append(",");
                    writer.append(tokens[1]);
                    writer.append(",");
                    writer.append(tokens[2]);
                    writer.append(",");
                    writer.append(tokens[3]);
                    writer.append(",");
                    writer.append(tokens[4]);
                    writer.append(",");
                    writer.append(tokens[5]);
                    writer.append(",");
                    writer.append(tokens[6]);
                    writer.append("\n");
                }

                if (Found)
                    Found = false;



            }


        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            //
            writer.close();
            reader.close();


            Files.delete(Paths.get(getLocation("Cinema")));

        } catch (IOException e) {
            e.printStackTrace();
        }

        Boolean success = tempFile.renameTo(new File(getLocation("Cinema")));
        return success;


    }

    /**
     *Updates the cinema details in the database, accepts two cinema objects, one as a key and another for details to be updated
     * @param cinema1 Cinema object to be used as a key to determine which entry line in database to be updated
     * @param cinema2 Cinema object to be updated in the database
     * @return True if showTime updated successfully updated, False otherwise
     */
    public static Boolean UpdateShowTime(Cinema cinema1, Cinema cinema2) {

        File inputFile = new File(getLocation("Cinema"));
        File tempFile = new File(getLocation("Temp"));

        BufferedReader reader = null;
        BufferedWriter writer = null;
        try {
            reader = new BufferedReader(new FileReader(inputFile));
            writer = new BufferedWriter(new FileWriter(tempFile));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


        try {
            writer.append("Cineplex_ID");
            writer.append(",");
            writer.append("Cinema_ID");
            writer.append(",");
            writer.append("Movie ID");
            writer.append(",");
            writer.append("ShowTime");
            writer.append(",");
            writer.append("Class");
            writer.append(",");
            writer.append("Seats");
            writer.append(",");
            writer.append("MovieType");
            writer.append("\n");


        } catch (IOException e) {
            e.printStackTrace();
        }
        Boolean Found = false;
        String line;
        try {
            reader.readLine();
            while ((line = reader.readLine()) != null) {
                String[] tokens = line.split(",");

                if ((tokens[0].equals(String.valueOf(cinema1.getCinplexID()))) && (tokens[1].equals(String.valueOf(cinema1.getCinemaID()))) && (tokens[2].equals(String.valueOf(cinema1.getMovieID()))) && (tokens[3].contains(String.valueOf(cinema1.getTime())))) {
                    Found = true;
                } else {
                    writer.append(tokens[0]);
                    writer.append(",");
                    writer.append(tokens[1]);
                    writer.append(",");
                    writer.append(tokens[2]);
                    writer.append(",");
                    writer.append(tokens[3]);
                    writer.append(",");
                    writer.append(tokens[4]);
                    writer.append(",");
                    writer.append(tokens[5]);
                    writer.append(",");
                    writer.append(tokens[6]);
                    writer.append("\n");
                }

                if (Found) {
                    writer.append(String.valueOf(cinema2.getCinplexID()));
                    writer.append(",");
                    writer.append(String.valueOf(cinema2.getCinemaID()));
                    writer.append(",");
                    writer.append(String.valueOf(cinema2.getMovieID()));
                    writer.append(",");
                    writer.append(String.valueOf(cinema2.getTime()));
                    writer.append(",");
                    writer.append(cinema2.getCinemaClass());
                    writer.append(",");
                    writer.append(Joiner.on('.').join(cinema2.getSeats()));
                    writer.append(",");
                    writer.append(cinema2.getMovieType());
                    writer.append("\n");
                    Found = false;

                }

            }


        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            //
            writer.close();
            reader.close();


            Files.delete(Paths.get(getLocation("Cinema")));

        } catch (IOException e) {
            e.printStackTrace();
        }

        Boolean success = tempFile.renameTo(new File(getLocation("Cinema")));
        return success;


    }

    /**
     * Loads and return details of all bookings from database
     * @return ArrayList of Booking objects
     */
    public static ArrayList<Booking> LoadBookings() {
        BufferedReader reader = null;
        ArrayList<Booking> bookingArrayList = new ArrayList<>();
        try {
            reader = new BufferedReader(new FileReader(getLocation("Booking")));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        String line = "";
        try {
            reader.readLine();
            while ((line = reader.readLine()) != null) {
                String[] tokens = line.split(",");


                String bookingID = tokens[0];
                int cinplexID = Integer.parseInt(tokens[1]);
                int cinemaID = Integer.parseInt(tokens[2]);
                int movieID = Integer.parseInt(tokens[3]);
                String showTime = tokens[4];
                String cinemaClass = tokens[5];
                String movieType = tokens[6];
                String customerName = tokens[7];
                String mobileNumber = tokens[8];
                String email = tokens[9];
                String customerType = tokens[10];
                int seatNO = Integer.parseInt(tokens[11]);
                String bookingTime = tokens[12];
                double price = Double.parseDouble(tokens[13]);


                Booking booking = new Booking(bookingID, cinplexID, cinemaID, movieID, showTime, cinemaClass, movieType, customerName, mobileNumber, email, customerType, seatNO, bookingTime, price);
                bookingArrayList.add(booking);

            }
            reader.close();

        } catch (IOException e) {
            e.printStackTrace();

        }
        return bookingArrayList;
    }

    /**
     * Saves input Booking object details into database
     * @param booking Booking object
     * @return True if database is successfully updated, False otherwise
     */
    public static void AddBooking(Booking booking) {
        FileWriter writer = null;
        try {
            writer = new FileWriter(getLocation("Booking"), true);
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            writer.append(String.valueOf(booking.getBookingID()));
            writer.append(",");
            writer.append(String.valueOf(booking.getCinplexID()));
            writer.append(",");
            writer.append(String.valueOf(booking.getCinemaID()));
            writer.append(",");
            writer.append(String.valueOf(booking.getMovieID()));
            writer.append(",");
            writer.append(booking.getShowTime());
            writer.append(",");
            writer.append(booking.getCinemaClass());
            writer.append(",");
            writer.append(booking.getMovieType());
            writer.append(",");
            writer.append(booking.getCustomerName());
            writer.append(",");
            writer.append(String.valueOf(booking.getMobileNumber()));
            writer.append(",");
            writer.append(booking.getEmail());
            writer.append(",");
            writer.append(booking.getCustomerType());
            writer.append(",");
            writer.append(String.valueOf(booking.getSeatNO()));
            writer.append(",");
            writer.append(booking.getBookingTime());
            writer.append(",");
            writer.append(String.valueOf(booking.getPrice()));
            writer.append("\n");
            writer.flush();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Loads and return details of every user from database
     * @return ArrayList of User objects
     */
    public static ArrayList<User> LoadUser() {

        BufferedReader reader = null;
        ArrayList<User> userArrayList = new ArrayList<>();
        try {
            reader = new BufferedReader(new FileReader(getLocation("User")));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        String line = "";
        try {
            reader.readLine();
            while ((line = reader.readLine()) != null) {
                String[] tokens = line.split(",");
                User user = new User(tokens[0], Integer.parseInt(tokens[1]), tokens[2], tokens[3]);
                userArrayList.add(user);

            }
            reader.close();

        } catch (IOException e) {
            e.printStackTrace();

        }
        return userArrayList;


    }

    /**
     *Manage users in the database
     * @param user User object to be added, updated or deleted
     * @param add True to add or edit user details in database, False to delete user details in database
     * @return True if User details are updated or a new user is added or user details are successfully deleted, False otherwise
     */
    public static Boolean ManageUser(User user,Boolean add) {


        File inputFile = new File(getLocation("User"));
        File tempFile = new File(getLocation("Temp"));


        BufferedReader reader = null;
        BufferedWriter writer = null;
        try {
            reader = new BufferedReader(new FileReader(inputFile));
            writer = new BufferedWriter(new FileWriter(tempFile));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


        try {
            writer.append("Name");
            writer.append(",");
            writer.append("Age");
            writer.append(",");
            writer.append("Mobile");
            writer.append(",");
            writer.append("Email");


            writer.append("\n");


        } catch (IOException e) {
            e.printStackTrace();
        }


        Boolean Found = false;
        String line;

        try {
            reader.readLine();
            while ((line = reader.readLine()) != null) {
                String[] tokens = line.split(",");

                if (tokens[0].equals(user.getName()) && tokens[2].equals(user.getmobileNumber())) {
                    Found = true;
                }else {
                    writer.append(tokens[0]);
                    writer.append(",");
                    writer.append(tokens[1]);
                    writer.append(",");
                    writer.append(tokens[2]);
                    writer.append(",");
                    writer.append(tokens[3]);
                    writer.append(",");
                    writer.append("\n");
                }
                if(Found || add){
                    writer.append(user.getName());
                    writer.append(",");
                    writer.append(String.valueOf(user.getAge()));
                    writer.append(",");
                    writer.append(user.getmobileNumber());
                    writer.append(",");
                    writer.append(user.getEmail());
                    writer.append("\n");
                    add=false;
                    Found=false;
                }
            }
            writer.close();
            reader.close();
            Files.delete(Paths.get(getLocation("User")));
            } catch (IOException e) {
            e.printStackTrace();}
        Boolean success = tempFile.renameTo(new File(getLocation("User")));
        return success;
        }

    /**
     * Load and return all settings details from database
     * @return Settings object
     */
    public static Settings LoadSettings(){

        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(getLocation("settings")));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        String line = "";
        try {
            reader.readLine();
            while ((line = reader.readLine()) != null) {
                String[] tokens = line.split(",");

                List<String> dates = Arrays.asList(tokens[8].split("\\."));
                Settings settings = new Settings(Double.parseDouble(tokens[0]),Double.parseDouble(tokens[1]),Double.parseDouble(tokens[2]),Double.parseDouble(tokens[3]),Double.parseDouble(tokens[4]),Double.parseDouble(tokens[5]),Double.parseDouble(tokens[6]),Double.parseDouble(tokens[7]),dates);
                reader.close();
                return settings;

            }

        } catch (IOException e) {
            e.printStackTrace();

        }
        return null;
    }

    /**
     * Save new settings from input Settings object into database
     * @param settings input Settings object's details to be saved in database
     * @return True if database is successfully updated, False otherwise
     */
    public static Boolean manageSettings(Settings settings){

        File tempFile = new File(getLocation("Temp"));

        BufferedWriter writer = null;
        try {
            writer = new BufferedWriter(new FileWriter(tempFile));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


        try {
            writer.append("BasePrice");
            writer.append(",");
            writer.append("ChildPrice");
            writer.append(",");
            writer.append("SeniorPrice");
            writer.append(",");
            writer.append("HolidayPrice");
            writer.append(",");
            writer.append("silverPrice");
            writer.append(",");
            writer.append("GoldPrice");
            writer.append(",");
            writer.append("platinumPrice");
            writer.append(",");
            writer.append("price3D");
            writer.append(",");
            writer.append("Holiday");
            writer.append("\n");


            writer.append(String.valueOf(settings.getBasePrice()));
            writer.append(",");
            writer.append(String.valueOf(settings.getChildPrice()));
            writer.append(",");
            writer.append(String.valueOf(settings.getSeniorPrice()));
            writer.append(",");
            writer.append(String.valueOf(settings.getHolidayPrice()));
            writer.append(",");
            writer.append(String.valueOf(settings.getSilverPrice()));
            writer.append(",");
            writer.append(String.valueOf(settings.getGoldPrice()));
            writer.append(",");
            writer.append(String.valueOf(settings.getPlatinumPrice()));
            writer.append(",");
            writer.append(String.valueOf(settings.getPrice3D()));
            writer.append(",");
            writer.append(Joiner.on('.').join(settings.getHolidays()));
            writer.flush();
            writer.append("\n");




        } catch (IOException e) {
            e.printStackTrace();
        }


        try {
            //
            writer.close();

            Files.delete(Paths.get(getLocation("settings")));

        } catch (IOException e) {
            e.printStackTrace();
        }
        Boolean success = tempFile.renameTo(new File(getLocation("settings")));
        return success;

    }

    /**
     * Load and return all staff details from database
     * @return ArrayList of Staff objects
     */
    public static ArrayList<Staff> Loadstaff(){
        BufferedReader reader = null;
        ArrayList<Staff> staffArrayList=new ArrayList<>();
        try {
            reader = new BufferedReader(new FileReader(getLocation("Staff")));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        String line = "";
        try {
            reader.readLine();
            while ((line = reader.readLine()) != null) {
                String[] tokens = line.split(",");


                Staff staff=new Staff(tokens[0],tokens[1]);
                staffArrayList.add(staff);
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();

        }
        return staffArrayList;
    }

}