
public class Booking {

    MovieShow movieshow;
    double price;
    Customer customer;

    public double Calcprice(){

        return price * movieshow.discounts * this.get_user_discount();

    }

    public double get_user_discount(){
        if(customer.age > 65){
            return 0.95;
        }
        if(customer.age < 14){
            return 0.95;
        }
        return 1.0;
    }
}
