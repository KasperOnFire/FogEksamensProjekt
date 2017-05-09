package User;

public class Order {

    private int ono;
    private int cno;
    private double length;
    private double width;
    private double price;
    private String rooftype;
    private int status;

    public Order(int ono, int cno, double length, double width, double price, String rooftype, int status) {
        this.ono = ono;
        this.cno = cno;
        this.length = length;
        this.width = width;
        this.price = price;
        this.rooftype = rooftype;
        this.status = status;
    }

    public int getOno() {
        return ono;
    }

    public int getCno() {
        return cno;
    }

    public double getLength() {
        return length;
    }

    public double getWidth() {
        return width;
    }

    public double getPrice() {
        return price;
    }

    public String getRooftype() {
        return rooftype;
    }

    public int getStatus() {
        return status;
    }
}
