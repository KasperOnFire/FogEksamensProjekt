package Backend;

import Database.DataAccessObject;
import java.util.HashMap;
import java.util.Map;

public class MaterialList
{
    private static double m2;
    private int mountingBands = 2;
    private static Map<String,Part> matList = new HashMap<>();
    private DataAccessObject dao;
    private Part p;
    public static void main(String[] args)
    {
        MaterialList materialList = new MaterialList();
        materialList.calcMaterialList(500, 500);
        double totalPrice = 0;
        for (Map.Entry<String, Part> part : matList.entrySet()) {
            totalPrice += part.getValue().getAmount() * part.getValue().getPrice();
        }
        double totalAdjustedPrice = Math.ceil(totalPrice/1000)*1000;
        for (Map.Entry<String, Part> part : matList.entrySet()) {
            System.out.println("Name: " + part.getKey() + " Amount: " + part.getValue().getAmount() + " Price: " + part.getValue().getPrice());
        }
        System.out.println("Total price: " + totalAdjustedPrice);
    }
    
    public void calcMaterialList(int length, int width)
    {
        //p = new Part(roof(length,width),dao.getDouble("price", "roofing", "roofname", "Plastmo Trapez"));
        p = new Part(roof(length,width),1.0);
        matList.put("Tag", p);
        //p = new Part(roofScrews(m2),dao.getDouble("price", "screws", "partname", "Plastmo tagskrue"));
        p = new Part(roofScrews(m2),1.0);
        matList.put("Tagskruer", p);
        //p = new Part(roofBoards(length),dao.getDouble("price", "wood", "woodname", "Spærtræ"));
        p = new Part(roofBoards(length),1.0);
        matList.put("Tagspær",p);
        //p = new Part(roofBoardScrews(roofBoards(length)),dao.getDouble("price", "screws", "partname", "Spærskrue"));
        p = new Part(roofBoardScrews(roofBoards(length)),1.0);
        matList.put("Tagspærsskruer",p);
        //p = new Part(roofBoardBrackets(roofBoards(length)),dao.getDouble("price", "screws", "partname", "Beslag"));
        p = new Part(roofBoardBrackets(roofBoards(length)),1.0);
        matList.put("Tagspærsbeslag",p);
        //p = new Part(mountingBandScrews(roofBoards(length),mountingBands),dao.getDouble("price", "screws", "partname", "Spærskruer"));
        p = new Part(mountingBandScrews(roofBoards(length),mountingBands),1.0);
        matList.put("Hulbåndsskruer",p);
        p = new Part(1,1.0);
        matList.put("Tagrem", p);
        p = new Part(4,1.0);
        matList.put("Understernbrædder", p);
        p = new Part(4,1.0);
        matList.put("Oversternbrædder", p);
        //p = new Part(posts(length),dao.getDouble("price", "wood", "woodname", "Stolpe"));
        p = new Part(posts(length),1.0);
        matList.put("Stolper",p);
        //p = new Part(postBolts(posts(length)),dao.getDouble("price", "screws", "partname", "Bolt"));
        p = new Part(postBolts(posts(length)),1.0);
        matList.put("Stolpebolte",p);
    }
    
    private int roof(int length,int width)
    {
        m2 = (double)(length/100)*(double)(width/100);
        return (int)Math.ceil(m2)+1;
    }
    
    private int roofScrews(double m2)
    {
        return (int)m2*12;
    }
    
    private int roofBoards(int length)
    {
        return (length/60)+1;
    }
    
    private int roofBoardScrews(int roofBoards)
    {
        return roofBoards*18;
    }
    
    private int roofBoardBrackets(int roofBoards)
    {
        return roofBoards*2;
    }
    
    private int mountingBandScrews(int roofBoards, int mountingBands)
    {
        return roofBoards*mountingBands;
    }
    
    private int posts(int length)
    {
        return 2*(length <= 400 ? 2 : (int) Math.ceil((double) length / 200));
    }
    
    private int postScrews(int posts)
    {
        return posts*4;
    }
    
    private int postBolts(int posts)
    {
        return posts*2;
    }
    
    }
