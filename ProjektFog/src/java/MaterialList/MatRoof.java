/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MaterialList;

import Carport.Roof;
import Database.DataAccessObject;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Kristian
 */
public class MatRoof
{
    private static double m2;
    private DataAccessObject dao;
    private static Map<String, Part> roofMap = new HashMap<>();
    private Part p;
    private static int mountingBands = 2;
    
    public Map<String, Part> calcRoof(int length, int depth, Roof r)
    {
        m2 = (double) (length / 100) * (double) (depth / 100);
        if(r.isGable())
        {
            p = new Part(gabledRoof(length, depth),dao.getDouble("price", "material", "name", "Tagpap"));
            roofMap.put("Tagpap", p);
            
            p = new Part(gabledBoards(length, depth),dao.getDouble("price", "material", "name", "Spærtræ"));
            roofMap.put("Tagspær", p);
            
            p = new Part(roofBoardScrews(roofBoards(length)),dao.getDouble("price", "material", "name", "Spærskrue"));
            roofMap.put("Spærskrue", p);
            
            p = new Part(1,dao.getDouble("price", "material", "name", "Danspær"));
            roofMap.put("Danspær", p);
        }
        else
        {
            p = new Part(flatRoof(length, depth),dao.getDouble("price", "material", "name", "Plastmo Trapez"));
            roofMap.put("Plastmo Trapez", p);
            
            p = new Part(roofScrews(flatRoof(length,depth)),dao.getDouble("price", "material", "name", "Plastmo Tagskrue"));
            roofMap.put("Plastmo Tagskrue", p);
            
            p = new Part(2,dao.getDouble("price", "material", "name", "Hulbånd"));
            roofMap.put("Hulbånd", p);
            
            p = new Part(roofBoards(length),dao.getDouble("price", "material", "name", "Spærtræ"));
            roofMap.put("Tagspær", p);
            
            p = new Part(roofBoardScrews(roofBoards(length)),dao.getDouble("price", "material", "name", "Spærskrue"));
            roofMap.put("Spærskrue", p);
            
            p = new Part(roofBoardBrackets(roofBoards(length)),dao.getDouble("price", "material", "name", "Spærbeslag"));
            roofMap.put("Spærbeslag", p);
            
            p = new Part(mountingBandScrews(roofBoards(length),mountingBands),dao.getDouble("price", "material", "name", "Spærskrue"));
            roofMap.put("Hulbåndsskrue", p);
            
            p = new Part(2,dao.getDouble("price", "material", "name", "Sternbræt"),depth);
            roofMap.put("Understernbræt, side", p);
            
            p = new Part(2,dao.getDouble("price", "material", "name", "Sternbræt"),depth);
            roofMap.put("Oversternbræt, side", p);
            
            p = new Part(1,dao.getDouble("price", "material", "name", "Sternbræt"),length);
            roofMap.put("Understernbræt, forende", p);
            
            p = new Part(1,dao.getDouble("price", "material", "name", "Sternbræt"),length);
            roofMap.put("Oversternbræt, forende", p);
        }
        return roofMap;
    }    
    
    
    
    private int flatRoof(int length, int width) {
        return (int) Math.ceil(m2) + 1;
    }

    private int roofScrews(double m2) {
        return (int) m2 * 12;
    }

    private int roofBoards(int length) {
        return (length / 60) + 1;
    }

    private int roofBoardScrews(int roofBoards) {
        return roofBoards * 18;
    }

    private int roofBoardBrackets(int roofBoards) {
        return roofBoards * 2;
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
        return (int)size;
    }
    
    private int gabledBoards(int length, int depth)
    {
        double a = (double)length/2;
        double a2 = a*a;
        double b = Math.log(depth);
        double b2 = b*b;
        double amount = Math.ceil(Math.sqrt(a2+b2)/35);
        return (int)amount;
    }
}
