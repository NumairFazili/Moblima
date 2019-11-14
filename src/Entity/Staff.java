package Entity;

public class Staff{
    String password;
    String name;

    public Staff(String name, String password){
        this.password = password;
        this.name=name;
    }

    public Staff() {

    }

    public void setPassword(String password){
        this.password = password;
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









}