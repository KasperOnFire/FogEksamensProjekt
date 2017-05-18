package MaterialList;

class Part
{
    private int amount;
    private int size;
    private double price;

    public Part(int amount, double price)
    {
        this.amount = amount;
        this.price = price;
    }
    
    public Part(int amount, double price, int size)
    {
        this.amount = amount;
        this.price = price;
        this.size = size;
    }

    public int getSize()
    {
        return size;
    }

    public double getPrice()
    {
        return price;
    }
    
    public int getAmount()
    {
        return amount;
    }
    
}
