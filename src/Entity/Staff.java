package Entity;

/**
 Represents the Admin using the MOBLIMA movie booking system, a staff can create, update and delete movie listings and cinema showtimes. A staff can also configure price settings
 @author CZ2002 Group 1
 @version 1.0
 @since 15-11-2019
 */
public class Staff{
    /**
     * Password for staff
     */
    String password;
    /**
     * Username for staff
     */
    String name;

    /**
     * Creates a new Staff object with the following parameters
     * @param name username for staff
     * @param password password for staff
     */
    public Staff(String name, String password){
        this.password = password;
        this.name=name;
    }

    /**
     * Creates a new Staff object
     */
    public Staff() {

    }

    /**
     * Change the password of Staff object
     * @param password password for staff
     */
    public void setPassword(String password){
        this.password = password;
    }

    /**
     * Get name of Staff
     * @return name of Staff
     */
    public String getName(){
        return name;
    }

    /**
     * Check if input password String is correct
     * @param password input password String to be checked
     * @return True if password is correct, False otherwise
     */
    public boolean checkPassword(String password){
        if (password.equals(this.password)){
            return true;
        }
        return false;
    }









}