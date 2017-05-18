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
<<<<<<< HEAD
    
    public Map<String, Part> calcRoof(int length, int depth, Roof r)
    {
        System.out.println("start");
        m2 = (double) (length / 100) * (double) (depth / 100);
        System.out.println(r.isGable());
        if(r.isGable())
        {
            p = new Part(gabledRoof(length, depth),dao.getDouble("price", "material", "name", "Tagpap"));
            roofMap.put("Tagpap", p);
            System.out.println("tagpap");
            
            p = new Part(gabledBoards(length, depth),dao.getDouble("price", "material", "name", "Spærtræ"));
            roofMap.put("Tagspær", p);
            System.out.println("tagspær");
            
            p = new Part(roofBoardScrews(roofBoards(length)),dao.getDouble("price", "material", "name", "Spærskrue"));
            roofMap.put("Spærskrue", p);
            System.out.println("spærskrue");
            
            p = new Part(1,dao.getDouble("price", "material", "name", "Danspær"));
            roofMap.put("Danspær", p);
            System.out.println("danspær");
        }
        else
        {
            p = new Part(flatRoof(length, depth),dao.getDouble("price", "material", "name", "Plastmo Trapez"));
=======

    public Map<String, Part> calcRoof(int length, int depth, Roof r) {
        m2 = (double) (length / 100) * (double) (depth / 100);
        if (r.isGable()) {
            p = new Part(gabledRoof(length, depth), dao.getDouble("price", "material", "name", "Tagpap"));
            roofMap.put("Tagpap", p);

            p = new Part(gabledBoards(length, depth), dao.getDouble("price", "material", "name", "Spærtræ"));
            roofMap.put("Tagspær", p);

            p = new Part(roofBoardScrews(roofBoards(length)), dao.getDouble("price", "material", "name", "Spærskrue"));
            roofMap.put("Spærskrue", p);

            p = new Part(1, dao.getDouble("price", "material", "name", "Danspær"));
            roofMap.put("Danspær", p);
        } else {
            p = new Part(flatRoof(), dao.getDouble("price", "material", "name", "Plastmo Trapez"));
>>>>>>> 3cad4ef6c887df733df5c9e2d17252c3604f5174
            roofMap.put("Plastmo Trapez", p);

            p = new Part(roofScrews(flatRoof()), dao.getDouble("price", "material", "name", "Plastmo Tagskrue"));
            roofMap.put("Plastmo Tagskrue", p);

            p = new Part(2, dao.getDouble("price", "material", "name", "Hulbånd"));
            roofMap.put("Hulbånd", p);

            p = new Part(roofBoards(length), dao.getDouble("price", "material", "name", "Spærtræ"));
            roofMap.put("Tagspær", p);

            p = new Part(roofBoardScrews(roofBoards(length)), dao.getDouble("price", "material", "name", "Spærskrue"));
            roofMap.put("Spærskrue", p);
<<<<<<< HEAD
            
            p = new Part(roofBoardBrackets(roofBoards(length)),dao.getDouble("price", "material", "name", "Tagspærbeslag"));
            roofMap.put("Spærbeslag", p);
            
            p = new Part(mountingBandScrews(roofBoards(length),mountingBands),dao.getDouble("price", "material", "name", "Spærskrue"));
=======

            p = new Part(roofBoardBrackets(roofBoards(length)), dao.getDouble("price", "material", "name", "Tagspærbeslag"));
            roofMap.put("Tagspærbeslag", p);

            p = new Part(mountingBandScrews(roofBoards(length), mountingBands), dao.getDouble("price", "material", "name", "Spærskrue"));
>>>>>>> 3cad4ef6c887df733df5c9e2d17252c3604f5174
            roofMap.put("Hulbåndsskrue", p);

            p = new Part(2, dao.getDouble("price", "material", "name", "Sternbræt"), depth);
            roofMap.put("Understernbræt, side", p);

            p = new Part(2, dao.getDouble("price", "material", "name", "Sternbræt"), depth);
            roofMap.put("Oversternbræt, side", p);

            p = new Part(1, dao.getDouble("price", "material", "name", "Sternbræt"), length);
            roofMap.put("Understernbræt, forende", p);

            p = new Part(1, dao.getDouble("price", "material", "name", "Sternbræt"), length);
            roofMap.put("Oversternbræt, forende", p);
        }
        return roofMap;
<<<<<<< HEAD
    }    
    
    
    
    private int flatRoof(int length, int width) {
        int fr=(int) Math.ceil(m2) + 1;
        System.out.println(fr);
        return fr;
=======
    }

    private int flatRoof() {
        return (int) Math.ceil(m2);
>>>>>>> 3cad4ef6c887df733df5c9e2d17252c3604f5174
    }

    private int roofScrews(double m2) {
        int rs=(int) m2 * 12;
        return rs;
    }

    private int roofBoards(int length) {
<<<<<<< HEAD
        int rb=(length / 60) + 1;
        return rb;
=======
        return (int) Math.ceil(length / 60);
>>>>>>> 3cad4ef6c887df733df5c9e2d17252c3604f5174
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
<<<<<<< HEAD
        double b2 = b*b;
        double size = Math.sqrt(a2+b2)*length;
        System.out.println(size);
        return (int)size;
=======
        double b2 = b * b;
        double size = Math.sqrt(a2 + b2) * length;
        return (int) size;
>>>>>>> 3cad4ef6c887df733df5c9e2d17252c3604f5174
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
