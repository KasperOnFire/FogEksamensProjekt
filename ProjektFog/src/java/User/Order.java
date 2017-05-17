package User;

/**
 *
 * This class contains the datastructure of an order from the database.
 */
public class Order {

    private int ono;
    private int uid;
    private int ostatus;
    private String carport;
    private double price;

    public Order(int ono, int uid, int ostatus, String carport, double price) {
        this.ono = ono;
        this.uid = uid;
        this.ostatus = ostatus;
        this.carport = carport;
        this.price = price;
    }

    public int getOno() {
        return ono;
    }

    public int getUid() {
        return uid;
    }

    public int getOstatus() {
        return ostatus;
    }

    public String getCarport() {
        return carport;
    }

    public double getPrice() {
        return price;
    }
}
