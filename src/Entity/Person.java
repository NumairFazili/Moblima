package Entity;

public abstract class Person {
    long ID;
    String name;

    // basic methods
    public Person(){
        ID = generateID();
        name = "Person"+Long.toString(ID);
    }
    long getId(){
        return ID;
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

    long generateID(){
        // to generate distinct user id using based on their time of starting the application
        long result = 0;
        String datetime = java.time.LocalDateTime.now().toString();
        int temp;
        for(int i = 0; i < 19; i++) {
            if(datetime.charAt(i) >=48 && datetime.charAt(i) < 58){
                temp = datetime.charAt(i);
                result += (temp-48);
                result *= 10;
            }
        }
        return result;
    }

    void save(){}
}
