package Entity;

public abstract class Person {
    String name;

    // basic methods
    public Person(){
    }
    void setName(String userinput){
        name = userinput;
    }
    String getName(){
        return name;
    }
    public String toString() {
        return name;
    }
    // end of basic methods

    void save(){}
}
