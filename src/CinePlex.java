import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collection;

public class CinePlex {
    ArrayList<Cinema> cinemas;
    String name;
    String location;
    int id;
    public CinePlex(String name, String location, int id, ArrayList<Cinema> cinemas){
        this.name = name;
        this.location = location;
        this.id = id;
        this.cinemas = cinemas;
    }
    public ArrayList<Cinema> getCinemas(){return this.cinemas};

    public String getName(){return this.name};

    public String getLocation(){return this.location};
    public int getId(){return this.id};

    public void setName(String name) {
        this.name = name;
    }

    public void setLocation(String location){
        this.location = location;
    }

    public void AddCinema(Cinema c){
        this.cinemas.add(c);
    }
    public void AddCinema(ArrayList<Cinema> c){
        this.cinemas.addAll(c);
    }
}
