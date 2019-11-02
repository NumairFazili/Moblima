package Entity;

public class User {
    private String name,email;
    private long mobileNumber;


    public User(String name, long mobileNumber, String email) {
        this.name = name;
        this.email = email;
        this.mobileNumber = mobileNumber;
    }



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public long getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(long mobileNumber) {
        this.mobileNumber = mobileNumber;
    }



}
