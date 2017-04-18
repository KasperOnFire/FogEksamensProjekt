/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Backend;

/**
 *
 * @author Kristian
 */
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
