package Entity;

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
        if (password == this.password){
            return true;
        }
        return false;
    }


    public static void main(String args[]){
        Staff s = new Staff("Cindy", "passwordhere");
        System.out.println(s.name);
        System.out.println(s.password);
        System.out.println(s.checkPassword("passwordhere"));
    }

}