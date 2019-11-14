package Entity;

public class Staff extends Person {
    String password;
    String name;

    public Staff(String name, String password){
        //this.setName_Staff(name);         //using base class method
        this.password = password;
        this.name=name;
        //ID should be generated automatically
    }

    public Staff() {

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









}