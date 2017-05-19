package MaterialList;

import Carport.Roof;
import Database.DataAccessObject;
import java.util.HashMap;
import java.util.Map;

public class MatRoof {

    private static double m2;
    private DataAccessObject dao;
    private Map<String, Part> roofMap = new HashMap<>();
    private Part p;
    private static int mountingBands = 2;
    
    private double getPrice(String name) throws Exception
    {
        DatabaseBack DBB = new DatabaseBack();
        return DBB.getDouble(name);
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

    private int flatRoof() {
        return (int) Math.ceil(m2);
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
        return rbs;
    }

    private int roofBoardBrackets(int roofBoards) {
        int rbb=roofBoards * 2;
        return rbb;
    }

    private int mountingBandScrews(int roofBoards, int mountingBands) {
        return roofBoards * mountingBands;
    }

    private int gabledRoof(int length, int depth) {
        double a = (double) length / 2;
        double a2 = a * a;
        double b = Math.log(depth);
        double b2 = b*b;
        double size = Math.sqrt(a2+b2)*length;
        System.out.println(size);
        return (int)size;
    }

    private int gabledBoards(int length, int depth) {
        double a = (double) length / 2;
        double a2 = a * a;
        double b = Math.log(depth);
        double b2 = b * b;
        double amount = Math.ceil(Math.sqrt(a2 + b2) / 35);
        return (int) amount;
    }
}
