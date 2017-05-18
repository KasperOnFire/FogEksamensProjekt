package MaterialList;

import Carport.Carport;
import Carport.Roof;
import Carport.Shed;
import java.util.HashMap;
import java.util.Map;

public class MaterialList {

    private int mountingBands = 2;
    private Map<String, Part> matList = new HashMap<>();
    private Part p;
    private MatRoof matRoof;
    private MatBase matBase;
    private MatShed matShed;
    private double m2;
    private Map<String, Part> roofMap = new HashMap<>();
    private Map<String, Part> baseMap = new HashMap<>();
    private Map<String, Part> shedMap = new HashMap<>();
    
    private double getPrice(String name) throws Exception
    {
        DatabaseBack DBB = new DatabaseBack();
        return DBB.getDouble(name);
    }


    public int totalPriceRounded() {
        double totalPrice = 0;
        
        for (Map.Entry<String, Part> part : matList.entrySet()) {
            totalPrice += part.getValue().getAmount() * part.getValue().getPrice();
        }
        
        double totalAdjustedPrice = Math.ceil(totalPrice / 1000) * 1000;
        
        return (int) totalAdjustedPrice;
    }
    
    public Map<String, Part> calcMaterialList(Carport c) throws Exception {
        int length;
        
        if(c.getShed().isHasShed())
        {
            length = c.getBase().getDepth()+c.getShed().getDepth()+c.getRoof().getFront()+c.getRoof().getBack();
        }
        else
        {
            length = c.getBase().getDepth()+c.getRoof().getFront()+c.getRoof().getBack();
        }
        
        int width = c.getBase().getWidth()+c.getRoof().getSides()+c.getRoof().getSides();
        
        matList.putAll(calcRoof(length, width, c.getRoof()));
        
        matList.putAll(calcBase(length, width, c.getBase().getHeight(), c.getShed()));
        
        if(c.getShed()!=null){
            matList.putAll(calcShed(length, width));
        }
        
        return matList;
    }
    
    public Map<String, Part> calcRoof(int length, int depth, Roof r) throws Exception
    {
        System.out.println("start");
        m2 = (double) (length / 100) * (double) (depth / 100);
        System.out.println(r.isGable());
        if(r.isGable())
        {
            System.out.println("tagpap start");
            p = new Part(gabledRoof(length, depth),getPrice("Tagpap"));
            roofMap.put("Tagpap", p);
            System.out.println("tagpap ok");
            
            p = new Part(gabledBoards(length, depth),getPrice("Spærtræ"));
            roofMap.put("Tagspær", p);
            System.out.println("tagspær");
            
            p = new Part(roofBoardScrews(roofBoards(length)),getPrice("Spærskrue"));
            roofMap.put("Spærskrue", p);
            System.out.println("spærskrue");
            
            p = new Part(1,getPrice("Danspær"));
            roofMap.put("Danspær", p);
            System.out.println("danspær");
        }
        else
        {
            p = new Part(flatRoof(length, depth),getPrice("Plastmo Trapez"));
            roofMap.put("Plastmo Trapez", p);
            
            p = new Part(roofScrews(flatRoof(length,depth)),getPrice("Plastmo Tagskrue"));
            roofMap.put("Plastmo Tagskrue", p);
            
            p = new Part(2,getPrice("Hulbånd"));
            roofMap.put("Hulbånd", p);
            
            p = new Part(roofBoards(length),getPrice("Spærtræ"));
            roofMap.put("Tagspær", p);
            
            p = new Part(roofBoardScrews(roofBoards(length)),getPrice("Spærskrue"));
            roofMap.put("Spærskrue", p);
            
            p = new Part(roofBoardBrackets(roofBoards(length)),getPrice("Tagspærbeslag"));
            roofMap.put("Spærbeslag", p);
            
            p = new Part(mountingBandScrews(roofBoards(length),mountingBands),getPrice("Spærskrue"));
            roofMap.put("Hulbåndsskrue", p);
            
            p = new Part(2,getPrice("Sternbræt"),depth);
            roofMap.put("Understernbræt, side", p);
            
            p = new Part(2,getPrice("Sternbræt"),depth);
            roofMap.put("Oversternbræt, side", p);
            
            p = new Part(1,getPrice("Sternbræt"),length);
            roofMap.put("Understernbræt, forende", p);
            
            p = new Part(1,getPrice("Sternbræt"),length);
            roofMap.put("Oversternbræt, forende", p);
        }
        System.out.println("ending");
        return roofMap;
    }    
    
    
    
    private int flatRoof(int length, int width) {
        int fr=(int) Math.ceil(m2) + 1;
        System.out.println(fr);
        return fr;
    }

    private int roofScrews(double m2) {
        int rs=(int) m2 * 12;
        return rs;
    }

    private int roofBoards(int length) {
        int rb=(length / 60) + 1;
        return rb;
    }

    private int roofBoardScrews(int roofBoards) {
        int rbs=roofBoards * 18;
        System.out.println(rbs);
        return rbs;
    }

    private int roofBoardBrackets(int roofBoards) {
        int rbb=roofBoards * 2;
        return rbb;
    }

    private int mountingBandScrews(int roofBoards, int mountingBands) {
        return roofBoards * mountingBands;
    }
    
    private int gabledRoof(int length, int depth)
    {
        double a = (double)length/2;
        double a2 = a*a;
        double b = Math.log(depth);
        double b2 = b*b;
        double size = Math.sqrt(a2+b2)*length;
        System.out.println(size);
        return (int)size;
    }
    
    private int gabledBoards(int length, int depth)
    {
        double a = (double)length/2;
        double a2 = a*a;
        double b = Math.log(depth);
        double b2 = b*b;
        double amount = Math.ceil(Math.sqrt(a2+b2)/35);
        System.out.println(amount);
        return (int)amount;
    }
    
    public Map<String, Part> calcBase(int length, int depth, int height, Shed s) throws Exception
    {
        p = new Part(posts(length,depth,s),getPrice("Stolpe"),height+90);
        baseMap.put("Stolper", p);
        
        p = new Part(postScrews(posts(length,depth,s)),getPrice("Stolpeskrue"));
        baseMap.put("Stolper", p);
        
        p = new Part(postBolts(posts(length,depth,s)),getPrice("Stolpebolt"));
        baseMap.put("Stolper", p);
        
        p = new Part(2,getPrice("Tagrem"),depth);
        baseMap.put("Tagrem", p);
        
        return baseMap;
    }
    
    private int posts(int length, int width, Shed s) {
        double m2 = (double)(width) * (length) / 2;
        int posts = (int)Math.ceil(m2 / 4) * 2;
        if (posts <= 3) {
            posts = 4;
        }
        if(s.isHasShed())
        {
            posts = posts + 1;
        }
        return posts;
    }

    private int postScrews(int posts) {
        return posts * 4;
    }

    private int postBolts(int posts) {
        return posts * 2;
    }
    
    public Map<String, Part> calcShed(int length, int depth) throws Exception
    {
        p = new Part(shedBoards(length, depth),getPrice("Skurbræt"));
        shedMap.put("Skurbræt", p);
        
        p = new Part(shedNails(shedBoards(length, depth)),getPrice("Skursøm"));
        shedMap.put("Skursøm", p);
        
        p = new Part(4,getPrice("Løsholte"),length);
        shedMap.put("Løsholte, side", p);
        
        p = new Part(6,getPrice("Løsholte"),depth);
        shedMap.put("Løsholte, gavl", p);
        
        return shedMap;
    }
    
    private int shedBoards(int length, int depth)
    {
        return (((length*2+depth*2)-40)/6)+1;
    }
    
    private int shedNails(int shedBoards)
    {
        return (int)Math.ceil(shedBoards/2)*9;
    }
    
}