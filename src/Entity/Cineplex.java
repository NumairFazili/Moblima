package Entity;

/**
 Represents a Cineplex
 A Cineplex can have multiple Cinemas
 @author CZ2002 Group 1
 @version 1.0
 @since 15-11-2019
 */
public class Cineplex {
    /**
     * ID of the cineplex
     */
    private int id;

    /**
     * Number of cinemas the cineplex has
     */
    private int size;

    /**
     * Name and address of cineplex
     */
    private String name,address;

    /**
     * Creates the cineplex with the following parameters
     *@param id This cineplex's ID
     *@param size = Number of cinemas this cineplex contains
     *@param name = This cineplex's name
     *@param address = This cineplex's name address
     */
    public Cineplex(int id, String name, String address, int size) {
        this.id = id;
        this.size = size;
        this.name = name;
        this.address = address;
    }

    /**
     * Get the ID of cineplex
     * @return ID of cineplex
     */
    public int getId() {
        return id;
    }

    /**
     * Change the ID of cineplex
     * @param id ID of cineplex
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Get the size of cineplex
     * @return size of cineplex
     */
    public int getSize() {
        return size;
    }

    /**
     * Change the size of cineplex
     * @param size size of cineplex
     */
    public void setSize(int size) {
        this.size = size;
    }

    /**
     * Get the name of cineplex
     * @return name of cineplex
     */
    public String getName() {
        return name;
    }

    /**
     * Change the name of cineplex
     * @param name name of cineplex
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Get the address of cineplex
     * @return address of cineplex
     */
    public String getAddress() {
        return address;
    }

    /**
     * Change the address of cineplex
     * @param address address of cineplex
     */
    public void setAddress(String address) {
        this.address = address;
    }




}
