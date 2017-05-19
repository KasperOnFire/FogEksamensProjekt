package MaterialList;

public class Material {

    private int mno;
    private String type;
    private int price;
    private String name;
    private int qoh;
    private int size;

    public Material(int mno, String type, int price, String name, int qoh, int size) {
        this.mno = mno;
        this.type = type;
        this.price = price;
        this.name = name;
        this.qoh = qoh;
        this.size = size;
    }

    public int getMno() {
        return mno;
    }

    public String getType() {
        return type;
    }

    public int getPrice() {
        return price;
    }

    public String getName() {
        return name;
    }

    public int getQoh() {
        return qoh;
    }

    public int getSize() {
        return size;
    }
    
}
